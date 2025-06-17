package inflearn.springadvance.trace.threadlocal;

import inflearn.springadvance.trace.threadlocal.code.ThreadLocalService;
import org.junit.jupiter.api.Test;

class ThreadLocalServiceTest {

    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    void thread_local() throws Exception {

        System.out.println("main start");

        Runnable userA = () -> {
            threadLocalService.logic("userA");
        };

        Runnable userB = () -> {
            threadLocalService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        threadB.start();

        // join을 사용하여 메인 스레드가 종료되지 않도록 함
        threadA.join();
        threadB.join();

        System.out.println("main exit");
    }
}