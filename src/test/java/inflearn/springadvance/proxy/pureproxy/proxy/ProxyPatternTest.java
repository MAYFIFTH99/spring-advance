package inflearn.springadvance.proxy.pureproxy.proxy;

import inflearn.springadvance.proxy.pureproxy.proxy.code.CacheProxy;
import inflearn.springadvance.proxy.pureproxy.proxy.code.ProxyPatternClient;
import inflearn.springadvance.proxy.pureproxy.proxy.code.RealSubject;
import inflearn.springadvance.proxy.pureproxy.proxy.code.Subject;
import org.junit.jupiter.api.Test;

class ProxyPatternTest {


    @Test
    void no_proxy(){
        Subject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);

        client.execute();
        client.execute();
        client.execute();

    }
    @Test
    void proxy_cache(){
        Subject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        client.execute();
        client.execute();
        client.execute();
    }
}
