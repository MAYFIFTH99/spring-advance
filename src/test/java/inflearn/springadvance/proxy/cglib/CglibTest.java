package inflearn.springadvance.proxy.cglib;

import inflearn.springadvance.proxy.cglib.code.TimeMethodInterceptor;
import inflearn.springadvance.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass()); // 구체 클래스를 상속받은 Proxy 객체를 생성해줘!
        enhancer.setCallback(new TimeMethodInterceptor(target)); // 메서드 호출을 가로채서 프록시 로직을 수행할 인터셉터 등록 (프록시 핵심 동작 정의)

        ConcreteService proxy = (ConcreteService) enhancer.create();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }
}
