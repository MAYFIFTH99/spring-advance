package inflearn.springadvance.proxy.proxyfactory;

import inflearn.springadvance.proxy.common.advice.TimeAdvice;
import inflearn.springadvance.proxy.common.service.ServiceImpl;
import inflearn.springadvance.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    void interface_proxy_jdk() {

        ServiceInterface target = new ServiceImpl(); // 프록시 대상
        ProxyFactory proxyFactory = new ProxyFactory(target);

        TimeAdvice timeAdvice = new TimeAdvice();
        proxyFactory.addAdvice(timeAdvice);

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

    }
}
