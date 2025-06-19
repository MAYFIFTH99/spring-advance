package inflearn.springadvance.proxy.config.v1_proxy.concrete_poxy;

import inflearn.springadvance.proxy.app.v2.OrderRepositoryV2;
import inflearn.springadvance.proxy.trace.TraceStatus;
import inflearn.springadvance.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {

    private final OrderRepositoryV2 orderRepository;
    private final LogTrace trace;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;

        try{
            status = trace.begin("OrderRepository.save()");
            orderRepository.save(itemId);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
