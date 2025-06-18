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
        strategy.call(); // 이렇게 다른 코드의 인수로 넘겨서 실행 가능한 코드를 `콜백`이라 한다.
        /**
         * 템플릿-콜백 패턴은 GOF 패턴 중 하나가 아니고, 스프링 내부에서 이런 방식을 자주 사용하기 때문에
         * 스프링 안에서만 이렇게 부른다.
         * 정리하면, 템플릿-콜백 배턴은 `전략 패턴`에서 '템플릿'과 '콜백' 부분이 강조된 패턴이라 생각하면 된다.
         */
        //비즈니스 로직 종료

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
