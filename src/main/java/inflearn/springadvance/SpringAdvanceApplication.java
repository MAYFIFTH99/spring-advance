package inflearn.springadvance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "inflearn.springadvance.proxy")
public class SpringAdvanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAdvanceApplication.class, args);
    }

}
