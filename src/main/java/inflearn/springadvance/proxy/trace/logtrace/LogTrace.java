package inflearn.springadvance.proxy.trace.logtrace;

import inflearn.springadvance.proxy.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
