package inflearn.springadvance.templatecallback.trace.logtrace;

import inflearn.springadvance.templatecallback.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
