package inflearn.springadvance.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Aspect
@Slf4j
public class AspectV5Order {

    @Order(2)
    @Aspect
    public static class LogAspect {
        @Around("inflearn.springadvance.aop.order.aop.pointcut.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {

            log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
            return joinPoint.proceed();
        }
    }

    @Order(1)
    @Aspect
    public static class TransactionAspect {
        //aop.order 패키지 + 그 하위 패키지 && 클래스 이름이 *Service
        @Around("inflearn.springadvance.aop.order.aop.pointcut.Pointcuts.orderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try{
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
                return result;
            }catch (Exception e) {
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;
            }finally {
                log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
            }
        }
    }
}
