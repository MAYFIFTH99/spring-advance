package inflearn.springadvance.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class BasicPostProcessorTest {

    @Test
    void basicProcessorConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                BeanPostProcessorConfig.class);

        B b = applicationContext.getBean("beanA", B.class);
        b.helloB();
    }

    @Slf4j
    static class AtoBPostProcessor implements BeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName)
                throws BeansException {
            log.info("beanName={} bean={}", beanName, bean);
            if (bean instanceof A) {
                return new B();
            }
            return bean;
        }
    }

    @Slf4j
    @Configuration
    static class BeanPostProcessorConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }

        @Bean
        public AtoBPostProcessor atoBPostProcessor() {
            return new AtoBPostProcessor();
        }

    }

    @Slf4j
    static class A {
        public void helloA(){
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }
    }
}
