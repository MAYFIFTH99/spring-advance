package inflearn.springadvance.v5;

import inflearn.springadvance.trace.callback.TraceTemplate;
import inflearn.springadvance.trace.logtrace.LogTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    @Autowired
    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(@RequestParam(required = false) String itemId) {
        return template.execute(this.getClass().getName() + " request()",
                () -> {
                    orderService.orderItem(itemId);
                    return "OK";
                });
    }
}