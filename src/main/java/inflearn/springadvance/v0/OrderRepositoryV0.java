package inflearn.springadvance.v0;

import static java.lang.Thread.sleep;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String itemId) {
        //save logic
        if(itemId.equals("ex")){
            throw new IllegalStateException("Exception Thrown");
        }
        sleep(1000);
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
