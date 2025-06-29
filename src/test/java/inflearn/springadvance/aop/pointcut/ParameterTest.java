package inflearn.springadvance.aop.pointcut;

import inflearn.springadvance.aop.member.MemberService;
import inflearn.springadvance.aop.member.annotation.ClassAop;
import inflearn.springadvance.aop.member.annotation.MethodAop;
import inflearn.springadvance.aop.pointcut.ParameterTest.ParameterAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(ParameterAspect.class)
public class ParameterTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("MemberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect {

        @Pointcut("execution(* inflearn.springadvance.aop.member..*.*(..))")
        private void allMember() {
        }

        @Around("allMember()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {

            Object arg1 = joinPoint.getArgs()[0];
            log.info("[logArgs1]{}, arg={}", joinPoint.getSignature(), arg1);
            return joinPoint.proceed();
        }

        @Around("allMember() && args(arg,..)")
        public Object logArgs2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {

            log.info("[logArgs2]{}, arg={}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        @Before("allMember() && args(arg, ..)")
        public void logArgs3(String arg) {
            log.info("[logArgs3] arg={}", arg);
        }

        //this는 스프링 컨테이너에 들어있는 프록시
        @Before("allMember() && this(obj)") // this(MemberService)
        public void thisArgs(JoinPoint joinPoint, MemberService obj){
            log.info("[this]{}, obj={}", joinPoint.getSignature(), obj.getClass());
        }

        //target은 프록시가 실제로 참조하는 타겟(실제)
        @Before("allMember() && target(obj)") // target(MemberService)
        public void targetArgs(JoinPoint joinPoint, MemberService obj){
            log.info("[target]{}, obj={}", joinPoint.getSignature(), obj.getClass());
        }

        @Before("allMember() && @target(annotation)")
        public void atTarget(JoinPoint joinPoint, ClassAop annotation){
            log.info("[@target]{}, annotation={}", joinPoint.getSignature(), annotation);
        }

        @Before("allMember() && @within(annotation)")
        public void atWithin(JoinPoint joinPoint, ClassAop annotation){
            log.info("[@within]{}, annotation={}", joinPoint.getSignature(), annotation);
        }

        @Before("allMember() && @annotation(annotation)")
        public void atAnnotation(JoinPoint joinPoint, MethodAop annotation){
            log.info("[@annotation]{}, annotationValue={}", joinPoint.getSignature(), annotation.value());
        }
    }
}
