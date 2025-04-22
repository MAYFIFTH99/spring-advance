package inflearn.springadvance.hellotrace;

import static org.junit.jupiter.api.Assertions.*;

import inflearn.springadvance.trace.TraceId;
import inflearn.springadvance.trace.TraceStatus;
import net.bytebuddy.pool.TypePool.Resolution.Illegal;
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