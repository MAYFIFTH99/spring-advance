package inflearn.springadvance.aop.internalcall;

import inflearn.springadvance.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Slf4j
@Import(CallLogAspect.class)
class CallServiceV3Test {

    @Autowired
    CallServiceV3 callServiceV3;

    @Test
    void external() {
        callServiceV3.external();
    }
}