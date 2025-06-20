package inflearn.springadvance.templatecallback.v3;

import inflearn.springadvance.templatecallback.trace.TraceStatus;
import inflearn.springadvance.templatecallback.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * V1은 로그 추적 시 '깊이' 정보를 표현하지 못한다.
 * 이전 Trace에 대한 Context를 전달해 동기화 기능 추가
 */
@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(@RequestParam(required = false) String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin(this.getClass().getName() + " " + "request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 함
        }
    }
}