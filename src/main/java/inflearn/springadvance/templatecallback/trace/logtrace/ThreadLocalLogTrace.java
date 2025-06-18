package inflearn.springadvance.templatecallback.trace.logtrace;

import inflearn.springadvance.templatecallback.trace.TraceId;
import inflearn.springadvance.templatecallback.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace{

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

//    private TraceId traceIdHolder; // traceId 동기화, 동시성 이슈 존재
    private ThreadLocal<TraceId> threadLocalTraceId = new ThreadLocal<>();

    @Override
    public TraceStatus begin(String message) {
        syncTraceId(); // traceId 동기화
        TraceId traceId = threadLocalTraceId.get(); // 현재 traceId 가져오기
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId(){
        TraceId traceId = threadLocalTraceId.get();
        if (traceId == null) {
            threadLocalTraceId.set(new TraceId());
        } else {
            threadLocalTraceId.set(traceId.createNextLevel());
        }
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX,
                    traceId.getLevel()), status.getMessage(), resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX,
                    traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }

        releaseTraceId(); // traceId 레벨 감소
    }

    private void releaseTraceId() {
        TraceId traceId = threadLocalTraceId.get();
        if( traceId.isFirstLevel()) {
            threadLocalTraceId.remove(); // 최상위 레벨로 돌아오면 traceIdHolder 초기화
        } else {
            threadLocalTraceId.set(traceId.createPreviousLevel()); // 레벨 감소
        }
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|  ");
        }
        return sb.toString();
    }
}
