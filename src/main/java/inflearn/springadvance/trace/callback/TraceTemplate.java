package inflearn.springadvance.trace.callback;

import inflearn.springadvance.trace.TraceStatus;
import inflearn.springadvance.trace.logtrace.LogTrace;
import org.springframework.stereotype.Component;

@Component
public class TraceTemplate {

    private final LogTrace trace;

    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T execute(String message, TraceCallback<T> callback) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            // 비즈니스 로직 호출
            T result = callback.call();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

}
