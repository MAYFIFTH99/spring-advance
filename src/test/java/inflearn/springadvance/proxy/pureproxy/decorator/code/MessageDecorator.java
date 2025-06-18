package inflearn.springadvance.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component{

    private final Component component;

    public MessageDecorator(Component component){
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("Message Decorator 실행");
        String operation = component.operation();
        String decoOperation = "****" + operation + "****";
        log.info("메시지 데코 전 = {}, 데코 후 = {}", operation, decoOperation);

        return decoOperation;
    }
}
