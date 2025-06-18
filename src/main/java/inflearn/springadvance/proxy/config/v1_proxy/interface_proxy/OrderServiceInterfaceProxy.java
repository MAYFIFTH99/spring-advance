package inflearn.springadvance.proxy.config.v1_proxy.interface_proxy;

import inflearn.springadvance.proxy.app.v1.OrderServiceV1;
import inflearn.springadvance.proxy.app.v1.OrderServiceV1Impl;
import inflearn.springadvance.proxy.trace.TraceStatus;
import inflearn.springadvance.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1 {

    private final OrderServiceV1Impl target;
    private final LogTrace trace;

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
