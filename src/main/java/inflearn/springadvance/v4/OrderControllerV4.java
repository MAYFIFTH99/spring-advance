package inflearn.springadvance.v4;

import inflearn.springadvance.trace.logtrace.LogTrace;
import inflearn.springadvance.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/v4/request")
    public String request(@RequestParam(required = false) String itemId) {

        AbstractTemplate<String> template = new AbstractTemplate<>(trace) {

            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "OK";
            }
        };

        return template.execute(this.getClass().getName() + " " + "request()");
    }

}