package inflearn.springadvance;

import inflearn.springadvance.proxy.config.v3_proxyfactory.ProxyFactoryConfigV2;
import inflearn.springadvance.proxy.trace.logtrace.LogTrace;
import inflearn.springadvance.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import({AppV1Config.class, AppV2Config.class})
//@Import(InterfaceProxyConfig.class)
//@Import(ConcreteProxyConfig.class)
//@Import(DynamicProxyConfig.class)
//@Import(DynamicProxyFilterConfig.class)
//@Import(ProxyFactoryConfigV1.class)
@Import(ProxyFactoryConfigV2.class)
@ComponentScan(basePackages = "inflearn.springadvance.proxy.app")
public class SpringAdvanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAdvanceApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

}
