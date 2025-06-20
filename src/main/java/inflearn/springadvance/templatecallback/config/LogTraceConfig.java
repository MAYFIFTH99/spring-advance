package inflearn.springadvance.templatecallback.config;

import inflearn.springadvance.templatecallback.trace.logtrace.LogTrace;
import inflearn.springadvance.templatecallback.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}
