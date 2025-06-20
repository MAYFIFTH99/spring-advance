package inflearn.springadvance.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {

    //private final 타겟 target 선언 필요 X

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

//        Object result = method.invoke(target, args);
        // invocation.proceed() 에서 타겟을 찾아서, 실행을 해줌
        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        long resultTIme = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTIme);
        return result;
    }
}
