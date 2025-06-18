package inflearn.springadvance.proxy.trace.callback;

public interface TraceCallback<T> {
    T call();
}
