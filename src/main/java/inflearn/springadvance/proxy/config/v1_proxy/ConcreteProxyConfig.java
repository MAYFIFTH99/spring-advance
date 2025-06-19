package inflearn.springadvance.proxy.config.v1_proxy;

import inflearn.springadvance.proxy.app.v2.OrderControllerV2;
import inflearn.springadvance.proxy.app.v2.OrderRepositoryV2;
import inflearn.springadvance.proxy.app.v2.OrderServiceV2;
import inflearn.springadvance.proxy.config.v1_proxy.concrete_poxy.OrderControllerConcreteProxy;
import inflearn.springadvance.proxy.config.v1_proxy.concrete_poxy.OrderRepositoryConcreteProxy;
import inflearn.springadvance.proxy.config.v1_proxy.concrete_poxy.OrderServiceConcreteProxy;
import inflearn.springadvance.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace trace) {
        OrderControllerV2 controllerImpl = new OrderControllerV2(orderServiceV2(trace));
        return new OrderControllerConcreteProxy(trace, controllerImpl);
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace trace) {
        OrderServiceV2 serviceImpl = new OrderServiceV2(orderRepositoryV2(trace));
        return new OrderServiceConcreteProxy(serviceImpl, trace);
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace trace) {
        OrderRepositoryV2 repositoryImpl = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(repositoryImpl, trace);
    }
}
