package inflearn.springadvance.trace.strategy.templatecallback;

import lombok.extern.slf4j.Slf4j;

/**
 * 스프링의 JdbcTemplate, RestTemplate, RedisTemplate,
 * XxxTemplate 들은 모두 이러한 '템플릿 콜백' 방식으로 설계되어 있다.
 */
@Slf4j
public class TimeLogTemplate {

    public void execute(Callback callback){

        long startTime = System.currentTimeMillis();

        callback.call();

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

}
