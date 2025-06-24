package inflearn.springadvance.proxy.config.v6_aop;

import inflearn.springadvance.proxy.config.AppV1Config;
import inflearn.springadvance.proxy.config.AppV2Config;
import inflearn.springadvance.proxy.config.v6_aop.aspect.LogTraceAspect;
import inflearn.springadvance.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AopConfig {

    @Bean
    public LogTraceAspect logTraceAspect(LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }
}
