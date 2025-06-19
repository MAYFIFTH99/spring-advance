package inflearn.springadvance.proxy.jdkdynamic;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        //공통 로직1 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);
        //공통 로직1 종료

        //공통 로직2 시작
        log.info("start");
        String result2 = target.callB();
        log.info("result={}", result2);

        //공통 로직2 종료
    }

    @Test
    void reflection1() throws Exception {
        //클래스 정보
        Class classHello = Class.forName(
                "inflearn.springadvance.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        //callA 메서드 정보
        Method callA = classHello.getMethod("callA");
        Object result1 = callA.invoke(target);
        log.info("result1={}", result1);

        //callB 메서드 정보
        Method callB = classHello.getMethod("callB");
        Object result2 = callB.invoke(target);
        log.info("result2={}", result2);

    }

    @Test
    void reflection2() throws Exception {
        //클래스 정보
        Class classHello = Class.forName(
                "inflearn.springadvance.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method callA = classHello.getMethod("callA");
        Method callB = classHello.getMethod("callB");

        dynamic(callA, target);
        dynamic(callB, target);

    }

    private Object dynamic(Method method, Object target)
            throws Exception {

        //공통 로직1 시작
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
        //공통 로직1 종료
        return result;
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB(){
            log.info("callB");
            return "B";
        }
    }


}
