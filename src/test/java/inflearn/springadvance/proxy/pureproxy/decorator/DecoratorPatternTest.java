package inflearn.springadvance.proxy.pureproxy.decorator;

import inflearn.springadvance.proxy.pureproxy.decorator.code.Component;
import inflearn.springadvance.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import inflearn.springadvance.proxy.pureproxy.decorator.code.MessageDecorator;
import inflearn.springadvance.proxy.pureproxy.decorator.code.RealComponent;
import inflearn.springadvance.proxy.pureproxy.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class DecoratorPatternTest {

    @Test
    void no_decorator(){
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);

        client.execute();
    }

    @Test
    void decorator(){
        Component component = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(component);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);

        client.execute();
    }

    @Test
    void double_decorator(){
        Component component = new RealComponent();
        Component messageDecorator = new MessageDecorator(component);
        Component timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

        client.execute();
    }
}
