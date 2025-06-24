package inflearn.springadvance.proxy.config.v5_autoproxy;

import inflearn.springadvance.proxy.config.AppV1Config;
import inflearn.springadvance.proxy.config.AppV2Config;
import inflearn.springadvance.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import inflearn.springadvance.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Slf4j
@Import({AppV1Config.class, AppV2Config.class})

public class AutoProxyConfig {

    @Bean
    public Advisor advisor1(LogTrace logTrace) {
        //pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}

