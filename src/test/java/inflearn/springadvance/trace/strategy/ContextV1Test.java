package inflearn.springadvance.trace.strategy;


import inflearn.springadvance.trace.strategy.code.ContextV1;
import inflearn.springadvance.trace.strategy.code.Strategy;
import inflearn.springadvance.trace.strategy.code.StrategyLogic1;
import inflearn.springadvance.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV1(){
        Strategy strategy = new StrategyLogic1();
        ContextV1 v1 = new ContextV1(strategy);
        v1.execute();

        strategy = new StrategyLogic2();
        ContextV1 v2 = new ContextV1(strategy);
        v2.execute();
    }

    @Test
    void strategyV2() {
        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        };

        ContextV1 v1 = new ContextV1(strategyLogic1);
        v1.execute();

        Strategy strategyLogic2 = () -> log.info("비즈니스 로직2 실행");

        ContextV1 v2 = new ContextV1(strategyLogic2);
        v2.execute();
    }

    @Test
    void strategyV3() {
        ContextV1 v1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
        v1.execute();

        ContextV1 v2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
        v2.execute();
    }
}
