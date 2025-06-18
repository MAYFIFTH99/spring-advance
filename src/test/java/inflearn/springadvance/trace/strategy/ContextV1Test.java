package inflearn.springadvance.trace.strategy;


import inflearn.springadvance.trace.strategy.code.strategy.ContextV1;
import inflearn.springadvance.trace.strategy.code.strategy.Strategy;
import inflearn.springadvance.trace.strategy.code.strategy.StrategyLogic1;
import inflearn.springadvance.trace.strategy.code.strategy.StrategyLogic2;
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
}
