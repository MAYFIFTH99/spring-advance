package inflearn.springadvance.aop.order.aop.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    /**
     * 포인트컷들을 외부에 모아서 관리하고 밖에서 참조하도록 만들려면, public으로 접근 제어를 열어두어야 한다.
     */

    //aop.order 패키지와 그 하위 패키지
    @Pointcut("execution(* inflearn.springadvance.aop.order..*(..))")
    public void allOrder() {}

    //클래스 이름이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    // allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {

    }


}
