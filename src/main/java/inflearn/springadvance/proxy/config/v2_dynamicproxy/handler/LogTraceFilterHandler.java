package inflearn.springadvance.proxy.config.v2_dynamicproxy.handler;

import inflearn.springadvance.proxy.trace.TraceStatus;
import inflearn.springadvance.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.springframework.util.PatternMatchUtils;

public class LogTraceFilterHandler implements InvocationHandler {

    private final LogTrace trace;
    private final Object target;
    private final String[] patterns;

    public LogTraceFilterHandler(LogTrace trace, Object target, String[] patterns) {
        this.trace = trace;
        this.target = target;
        this.patterns = patterns;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //메서드 이름 필터
        String methodName = method.getName();
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, args);
        }

        TraceStatus status = null;

        try{
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = trace.begin(message);

            //실제 로직 호출
            Object result = method.invoke(target, args);

            trace.end(status);
            return result;

        }catch(Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
