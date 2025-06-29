package inflearn.springadvance.hellotrace;

import inflearn.springadvance.templatecallback.hellotrace.HelloTraceV2;
import inflearn.springadvance.templatecallback.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    void begin_end(){
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_ex() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("ex hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "ex hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

}