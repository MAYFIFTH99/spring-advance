package inflearn.springadvance.proxy.config.v1_proxy.interface_proxy;

import inflearn.springadvance.proxy.app.v1.OrderRepositoryV1;
import inflearn.springadvance.proxy.app.v1.OrderRepositoryV1Impl;
import inflearn.springadvance.proxy.trace.TraceStatus;
import inflearn.springadvance.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

    private final OrderRepositoryV1Impl target;
    private final LogTrace trace;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderRepository.request()");
            target.save(itemId);
            trace.end(status);

        } catch (Exception e) {
            trace.exception(status,e);
            throw e;
        }
    }
}
