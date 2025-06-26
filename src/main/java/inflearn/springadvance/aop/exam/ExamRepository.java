package inflearn.springadvance.aop.exam;

import inflearn.springadvance.aop.exam.annotation.Retry;
import inflearn.springadvance.aop.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

    private static int seq = 0;

    /**
     * 5번 중 1번 실패하는 요청
     */
    @Trace
    @Retry(5)
    public String save(String itemId) {
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }
}
