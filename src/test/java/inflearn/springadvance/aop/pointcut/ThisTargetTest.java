package inflearn.springadvance.aop.pointcut;

import inflearn.springadvance.aop.member.MemberService;
import inflearn.springadvance.aop.pointcut.ThisTargetTest.ThisTargetAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // true=CGLIB, false= JDK
@Import(ThisTargetAspect.class)
public class ThisTargetTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Aspect
    @Slf4j
    static class ThisTargetAspect {

        //부모 타입 허용
        @Around("this(inflearn.springadvance.aop.member.MemberService)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        //부모 타입 허용
        @Around("target(inflearn.springadvance.aop.member.MemberService)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        //this: 스프링 AOP 프록시 객체 대상
        // JDK 동적 프록시는 인터페이스를 기반으로 생성되므로 구현 클래스를 알 수 없음
        // CGLIB 동적 프록시는 구현 클래스를 기반으로 생성되므로 구현 클래스를 알 수 있음
        @Around("this(inflearn.springadvance.aop.member.MemberServiceImpl)")
        public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
            /**
             * 즉, this 를 이용해 포인트컷을 걸때, 구현체로 걸면
             *  JDK 동적 프록시는 이를 AOP 로 보지 못한다.
             *
             *  따라서 이 AOP는 적용되지 않는 로그를 볼 수 있다.
             *  this를 사용했는데 포인트컷으로 구현체를 달아놨으니, 프록시는 Impl 을 모름
             */
        }


        // target: 실제 타겟 객체 대상
        @Around("target(inflearn.springadvance.aop.member.MemberServiceImpl)")
        public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
