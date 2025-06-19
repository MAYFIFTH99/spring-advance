package inflearn.springadvance.proxy.jdkdynamic;

import inflearn.springadvance.proxy.jdkdynamic.code.AImpl;
import inflearn.springadvance.proxy.jdkdynamic.code.AInterface;
import inflearn.springadvance.proxy.jdkdynamic.code.BImpl;
import inflearn.springadvance.proxy.jdkdynamic.code.BInterface;
import inflearn.springadvance.proxy.jdkdynamic.code.TimeInvocationHandler;
import java.lang.reflect.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        // A 구현체 정의
        AInterface target = new AImpl();

        // 메서드 호출 시간을 측정하여 로깅하는 핸들러 정의(InvocationHandler 구현체, invoke() 메서드 구현)
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        // 프록시 객체 정의
        /**
         * 매개변수;
         * 프록시 클래스가 JVM 어디에 로드될지 알려주는 클래스 로드,
         * 해당 프록시가 어떤 인터페이스를 구현하는지,
         * 프록시가 호출될 때 실제로 어떤 동작을 할지 정의하는 핸들러
         * 전달
         */
        AInterface proxy = (AInterface)Proxy.newProxyInstance(AInterface.class.getClassLoader(),
                new Class[]{AInterface.class},
                handler);

        // 프록시를 통해 call() 메서드 호출 (InvocationHandler의 invoke()가 실행됨)
        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface)Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class[]{BInterface.class}, handler);

        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }
}
