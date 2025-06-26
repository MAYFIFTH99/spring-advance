package inflearn.springadvance.aop.internalcall;

import static org.junit.jupiter.api.Assertions.*;

import inflearn.springadvance.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(CallLogAspect.class)
@Slf4j
class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0;

    @Test
    void external() {
        log.info("callServiceV0.getClass() ={}", callServiceV0.getClass());
        callServiceV0.external();

        /**
         * 1. callServiceV0.external() -> 프록시 객체 -> external 호출
         * 2. external() -> this.internal() -> 실제 타겟에서 internal 메서드 호출
         * 따라서 AOP 적용이 안되는 문제 발생
         */
    }

    @Test
    void internal() {
        // internal() 메서드만 호출한 경우, 포인트컷 조건에 만족하기 때문에 AOP 적용이 됨
        callServiceV0.internal();
    }
}