package inflearn.springadvance.proxy.config.v3_proxyfactory;

import inflearn.springadvance.proxy.app.v2.OrderControllerV2;
import inflearn.springadvance.proxy.app.v2.OrderRepositoryV2;
import inflearn.springadvance.proxy.app.v2.OrderServiceV2;
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
public class ProxyFactoryConfigV2 {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace trace) {
        OrderControllerV2 orderController = new OrderControllerV2(orderServiceV2(trace));
        ProxyFactory proxyFactory = new ProxyFactory(orderController);
        proxyFactory.addAdvisor(getAdvisor(trace));

        return (OrderControllerV2) proxyFactory.getProxy();
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace trace) {
        OrderServiceV2 orderService = new OrderServiceV2(orderRepositoryV2(trace));
        ProxyFactory proxyFactory = new ProxyFactory(orderService);
        proxyFactory.addAdvisor(getAdvisor(trace));

        return (OrderServiceV2) proxyFactory.getProxy();
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace trace) {

        OrderRepositoryV2 orderRepository = new OrderRepositoryV2();
        ProxyFactory proxyFactory = new ProxyFactory(orderRepository);
        proxyFactory.addAdvisor(getAdvisor(trace));

        return (OrderRepositoryV2) proxyFactory.getProxy();
    }

    private Advisor getAdvisor(LogTrace trace){
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("save*", "order*", "request*");
        LogTraceAdvice advice = new LogTraceAdvice(trace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
