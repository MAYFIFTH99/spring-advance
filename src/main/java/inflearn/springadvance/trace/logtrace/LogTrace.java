package inflearn.springadvance.trace.logtrace;

import inflearn.springadvance.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
