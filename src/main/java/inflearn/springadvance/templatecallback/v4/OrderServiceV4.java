package inflearn.springadvance.templatecallback.v4;

import inflearn.springadvance.templatecallback.trace.logtrace.LogTrace;
import inflearn.springadvance.templatecallback.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){

        AbstractTemplate<Void> template = new AbstractTemplate<>(trace){

            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };
        template.execute(this.getClass().getName() + " " + "request()");
    }
}
