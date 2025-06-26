package inflearn.springadvance.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    @Autowired // Setter로 순환 생성자 문제 해결(스프링 빈은 빈 생성 -> 의존성 주입 -> 초기화 단계를 거침)
    public void setCallServiceV1(@Lazy  CallServiceV1 callServiceV1) {
        log.info("callServiceV1 setter={}", callServiceV1.getClass()); // 프록시 확인 로그
        this.callServiceV1 = callServiceV1; // 프록시 객체가 주입
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal(); // this.internal이 아니라, 프록시.internal() 외부 메서드 호출처럼 동작함
    }

    public void internal() {
        log.info("call internal");
    }
}
