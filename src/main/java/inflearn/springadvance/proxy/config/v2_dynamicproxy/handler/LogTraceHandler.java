package inflearn.springadvance.proxy.config.v2_dynamicproxy.handler;

import inflearn.springadvance.proxy.trace.TraceStatus;
import inflearn.springadvance.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceHandler implements InvocationHandler {

    private final LogTrace trace;
    private final Object target;

    public LogTraceHandler(LogTrace trace, Object target) {
        this.trace = trace;
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
