package inflearn.springadvance.proxy.config.v2_dynamicproxy;

import inflearn.springadvance.proxy.app.v1.OrderControllerV1;
import inflearn.springadvance.proxy.app.v1.OrderControllerV1Impl;
import inflearn.springadvance.proxy.app.v1.OrderRepositoryV1;
import inflearn.springadvance.proxy.app.v1.OrderRepositoryV1Impl;
import inflearn.springadvance.proxy.app.v1.OrderServiceV1;
import inflearn.springadvance.proxy.app.v1.OrderServiceV1Impl;
import inflearn.springadvance.proxy.config.v2_dynamicproxy.handler.LogTraceHandler;
import inflearn.springadvance.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicProxyConfig {

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace) {
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(trace));
        LogTraceHandler handler = new LogTraceHandler(trace, orderController);

        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(
                OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class}, handler);
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {
        OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(trace));
        LogTraceHandler handler = new LogTraceHandler(trace,orderService);

        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(
                OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class}, handler);
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
        LogTraceHandler handler = new LogTraceHandler(trace, orderRepository);

        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                handler);
        return proxy;
    }

}
