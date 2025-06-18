package inflearn.springadvance.trace.template;

import inflearn.springadvance.trace.template.code.AbstractTemplate;
import inflearn.springadvance.trace.template.code.SubClassLogic1;
import inflearn.springadvance.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0() throws Exception {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);

    }

    @Test
    void logic2()  {
        long startTime = System.currentTimeMillis();

        //비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }


    @Test
    void templateMethodV1() {
        SubClassLogic1 logic1 = new SubClassLogic1();
        logic1.execute();

        SubClassLogic2 logic2 = new SubClassLogic2();
        logic2.execute();
    }

    @Test
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1");
            }
        };

        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스로직2");
            }
        };

        template2.execute();
    }
}
