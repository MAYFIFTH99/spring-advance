package inflearn.springadvance.proxy.config.v3_proxyfactory;

import inflearn.springadvance.proxy.app.v1.OrderControllerV1;
import inflearn.springadvance.proxy.app.v1.OrderControllerV1Impl;
import inflearn.springadvance.proxy.app.v1.OrderRepositoryV1;
import inflearn.springadvance.proxy.app.v1.OrderRepositoryV1Impl;
import inflearn.springadvance.proxy.app.v1.OrderServiceV1;
import inflearn.springadvance.proxy.app.v1.OrderServiceV1Impl;
import inflearn.springadvance.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import inflearn.springadvance.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV1 {

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace) {
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(trace));
        ProxyFactory proxyFactory = new ProxyFactory(orderController);
        proxyFactory.addAdvisor(getAdvisor(trace));

        return (OrderControllerV1) proxyFactory.getProxy();
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {
        OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(trace));
        ProxyFactory proxyFactory = new ProxyFactory(orderService);
        proxyFactory.addAdvisor(getAdvisor(trace));

        return (OrderServiceV1) proxyFactory.getProxy();
    }

    @Bean
    OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {

        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
        ProxyFactory proxyFactory = new ProxyFactory(orderRepository);
        proxyFactory.addAdvisor(getAdvisor(trace));

        return (OrderRepositoryV1) proxyFactory.getProxy();
    }

    private Advisor getAdvisor(LogTrace trace){
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("save*", "order*", "request*");
        LogTraceAdvice advice = new LogTraceAdvice(trace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
