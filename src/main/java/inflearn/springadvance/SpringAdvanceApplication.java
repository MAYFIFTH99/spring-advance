package inflearn.springadvance;

import inflearn.springadvance.proxy.config.AppV1Config;
import inflearn.springadvance.proxy.config.AppV2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AppV1Config.class, AppV2Config.class})
@ComponentScan(basePackages = "inflearn.springadvance.proxy.app")
public class SpringAdvanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAdvanceApplication.class, args);
    }

}
