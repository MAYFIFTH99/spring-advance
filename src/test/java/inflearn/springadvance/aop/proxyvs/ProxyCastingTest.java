package inflearn.springadvance.aop.proxyvs;

import inflearn.springadvance.aop.member.MemberService;
import inflearn.springadvance.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // JDK 동적 프록시

        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        Assertions.assertThrows(ClassCastException.class,() ->
        { MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;});
        // |-> 오류 발생 : 캐스팅 불가
        // why? JDK 동적 프록시는 인터페이스 기반이므로, Impl에 대한 정보도 없고, 알 수도 없다.
    }
}
