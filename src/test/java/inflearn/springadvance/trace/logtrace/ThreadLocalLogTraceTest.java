package inflearn.springadvance.trace.logtrace;

import inflearn.springadvance.templatecallback.trace.TraceStatus;
import inflearn.springadvance.templatecallback.trace.logtrace.ThreadLocalLogTrace;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {

    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    void begin_end_level2() {

        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_ex_level2() throws Exception {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalArgumentException("예외 발생!"));
        trace.end(status1);
    }
}