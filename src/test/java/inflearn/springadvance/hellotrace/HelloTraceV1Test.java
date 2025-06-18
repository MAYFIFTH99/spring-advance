package inflearn.springadvance.hellotrace;

import inflearn.springadvance.templatecallback.hellotrace.HelloTraceV1;
import inflearn.springadvance.templatecallback.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV1Test {

    @Test
    void begin_end(){
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_ex() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("ex hello");
        trace.exception(status, new IllegalStateException());
    }

}