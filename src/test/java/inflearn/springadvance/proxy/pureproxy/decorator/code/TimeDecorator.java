package inflearn.springadvance.proxy.pureproxy.decorator.code;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component{

    private final Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("Time Decorator 실행");
        String operation = component.operation();
        String decoOperation = LocalDateTime.now().toString() + operation;
        log.info("Time Deco 전 = {}, 후 = {}", operation, decoOperation);
        return decoOperation;
    }
}
