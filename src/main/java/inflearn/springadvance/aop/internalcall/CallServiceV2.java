package inflearn.springadvance.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
/**
 * 스프링 컨테이너를 직접 주입받아 프록시 빈을 꺼내 사용하기
 */
public class CallServiceV2 {

    private final ApplicationContext context;
    // 우리는 하나의 빈만 찾는 기능만 필요한데, context 전체를 주입 받는 것은 너무 오버헤드가 크다.

    // 이를 Provider를 이용해 해결하자.
    private final ObjectProvider<CallServiceV2> provider;


    public CallServiceV2(ApplicationContext context) {
        this.context = context;
    }

    public void external() {
        log.info("call external");
        CallServiceV2 callServiceV2 = context.getBean("callServiceV2", CallServiceV2.class);
        callServiceV2.internal();
    }

    public void internal() {
        log.info("call internal");
    }
}
