package inflearn.springadvance.templatecallback.v3;

import inflearn.springadvance.templatecallback.trace.TraceStatus;
import inflearn.springadvance.templatecallback.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){

        TraceStatus status = null;
        try {
            status = trace.begin(this.getClass().getName() + " " + "request()");
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 함
        }

    }

}
