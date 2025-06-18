package inflearn.springadvance.templatecallback.v5;

import inflearn.springadvance.templatecallback.trace.callback.TraceTemplate;
import inflearn.springadvance.templatecallback.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId) {
        template.execute(this.getClass().getName() + " " + "request()", () -> {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("Exception Thrown");
            }
            sleep(1000);
            return "OK";
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
