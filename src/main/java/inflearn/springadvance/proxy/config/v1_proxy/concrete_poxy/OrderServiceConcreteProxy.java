package inflearn.springadvance.proxy.config.v1_proxy.concrete_poxy;

import inflearn.springadvance.proxy.app.v2.OrderServiceV2;
import inflearn.springadvance.proxy.trace.TraceStatus;
import inflearn.springadvance.proxy.trace.logtrace.LogTrace;


public class OrderServiceConcreteProxy extends OrderServiceV2 {

    private final LogTrace trace;
    private final OrderServiceV2 target;

    public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace logTrace) {
        super(null); // 인터페이스 기반이 아니기 때문에 super를 꼭 작성해야 하는 단점 존재. 여기서는 프록시만 사용하므로 null을 보낸다.
        this.target = target;
        this.trace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;

        try{
            status = trace.begin("OrderService.orderItem()");
            target.orderItem(itemId);
            trace.end(status);

        }catch(Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
