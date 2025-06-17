package inflearn.springadvance.config;

import inflearn.springadvance.trace.logtrace.FieldLogTrace;
import inflearn.springadvance.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
        return new FieldLogTrace();
    }
}
