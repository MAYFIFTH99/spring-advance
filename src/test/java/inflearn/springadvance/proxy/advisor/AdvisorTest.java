package inflearn.springadvance.proxy.advisor;

import inflearn.springadvance.proxy.common.advice.TimeAdvice;
import inflearn.springadvance.proxy.common.service.ServiceImpl;
import inflearn.springadvance.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

@Slf4j
public class AdvisorTest {

    @Test
    void adviserTest1() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE,
                new TimeAdvice());

        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.find();
        proxy.save();
    }
}
