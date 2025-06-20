package inflearn.springadvance.proxy.proxyfactory;

import static org.assertj.core.api.Assertions.assertThat;

import inflearn.springadvance.proxy.common.advice.TimeAdvice;
import inflearn.springadvance.proxy.common.service.ConcreteService;
import inflearn.springadvance.proxy.common.service.ServiceImpl;
import inflearn.springadvance.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

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
        proxy.save();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();

    }

    @Test
    @DisplayName("구체 클래스가 있으면 CGLIB 동적 프록시 사용")
    void concrete_proxy_cglib(){

        ConcreteService target = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고, 클래스 기반 프록시를 사용")
    void proxy_target_class() {

        ServiceInterface target = new ServiceImpl(); // 프록시 대상
        ProxyFactory proxyFactory = new ProxyFactory(target);

        //
        proxyFactory.setProxyTargetClass(true); // 타겟 클래스 기반으로 프록시 생성해줘!
        //

        TimeAdvice timeAdvice = new TimeAdvice();
        proxyFactory.addAdvice(timeAdvice);

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        proxy.save();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();

    }
}
