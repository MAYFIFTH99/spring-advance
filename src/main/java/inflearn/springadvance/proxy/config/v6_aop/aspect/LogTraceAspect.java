package inflearn.springadvance.proxy.config.v6_aop.aspect;

import inflearn.springadvance.proxy.trace.TraceStatus;
import inflearn.springadvance.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    /**
     * 직접 어드바이저 생성할 필요 없이,
     * Around 어노테이션에 Pointcut 조건 넣고
     * 부가 로직 작성하면 끝
     */
    @Around("execution(* inflearn.springadvance.proxy.app..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        //LogTraceAdvice 에 있던 로직 수정
        TraceStatus status = null;

        try{
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            //실제 로직 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;

        }catch(Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
    // @Around가 포인트컷 역할을, 메소드 내부 로직이 어드바이스 역할을 한다.
}
