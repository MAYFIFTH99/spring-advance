package inflearn.springadvance.trace.strategy;

import inflearn.springadvance.trace.strategy.code.ContextV2;
import inflearn.springadvance.trace.strategy.code.StrategyLogic1;
import inflearn.springadvance.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ContextV2Test {

    @Test
    void strategyV1(){
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(StrategyLogic1::new);
        contextV2.execute(new StrategyLogic2());
    }
}