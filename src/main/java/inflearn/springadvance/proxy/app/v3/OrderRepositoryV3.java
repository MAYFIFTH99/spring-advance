package inflearn.springadvance.proxy.app.v3;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV3 {

    public void save(String itemId)  {
        //저장 로직
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);
    }

    private void sleep(int millis)  {
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
