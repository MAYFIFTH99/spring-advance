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

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB 동적 프록시

        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //CGLIB 프록시를 구현 클래스로 캐스팅 시도 -> 성공
        //why? CGLIB는 구체 클래스 기반이기 때문 Impl도 알고 있다.
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        log.info("castingMemberService.class = {} ", castingMemberService.getClass());

        // 당연히 Impl 뿐만 아니라 Impl의 인터페이스인 MemberService 로도 캐스팅 가능하다.
    }
}
