package inflearn.springadvance.templatecallback.v4;

import inflearn.springadvance.templatecallback.trace.logtrace.LogTrace;
import inflearn.springadvance.templatecallback.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId) {

        AbstractTemplate<String> template = new AbstractTemplate<>(trace){

            @Override
            protected String call() {
                //save logic
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("Exception Thrown");
                }
                sleep(1000);
                return "OK";
            }
        };
        template.execute(this.getClass().getName() + " " + "request()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
