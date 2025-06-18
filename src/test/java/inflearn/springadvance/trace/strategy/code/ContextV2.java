package inflearn.springadvance.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {

    /**
     * 전략(Strategy)를 필드로 갖지 않고, 메서드의 매개변수로 전달받도록,
     * 더 유연하게 사용하는 코드로 리팩토링
     */
    public void execute(Strategy strategy){
        long startTime = System.currentTimeMillis();

        //비즈니스 로직 실행
        strategy.call();
        //비즈니스 로직 종료

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
