package inflearn.springadvance.aop.internalcall;

import static org.junit.jupiter.api.Assertions.*;

import inflearn.springadvance.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(CallLogAspect.class)
class CallServiceV1Test {

    @Autowired
    CallServiceV1 callServiceV1;

    @Test
    void external() {
        log.info("callService.getClass() ={}", callServiceV1.getClass());
        callServiceV1.external();
    }
}