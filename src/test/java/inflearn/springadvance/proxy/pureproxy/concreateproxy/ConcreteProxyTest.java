package inflearn.springadvance.proxy.pureproxy.concreateproxy;

import inflearn.springadvance.proxy.pureproxy.concreateproxy.code.ConcreteClient;
import inflearn.springadvance.proxy.pureproxy.concreateproxy.code.ConcreteLogic;
import inflearn.springadvance.proxy.pureproxy.concreateproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {


    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);

        client.execute();
    }

    @Test
    void addProxy(){
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy);

        client.execute();

    }

}
