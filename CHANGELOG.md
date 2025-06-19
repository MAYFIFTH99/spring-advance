# **동적 프록시**

로그 추적기 적용을 위해 가장 간단한 방법인
1. 각 클래스마다 로그 추적기 주입
2. 동시성 문제를 위한 스레드로컬 도입
3. 템플릿 콜백 패턴과 전략 패턴 활용
4. 프록시 패턴과 데코레이터 패턴 적용

을 순서대로 구현해봤다.

프록시 패턴을 이용해
- 접근 제어
- 부가 기능 추가

기능을 사용할 수 있게 되었지만, 결국 프록시 클래스를 필요한 만큼 추가해주어야 하는 문제점이 남았다.

이를 `동적 프록시`를 이용해 해결하는 방법을 해당 브랜치에서 구현한다.


### 리플렉션

> 자바 리플렉션 활용

- reflection0() : 공통 로직 사이에 개별 로직 존재
- reflection1() : 리플렉션 예제
- reflection2() : 리플렉션을 이용해 추상화 + 리팩토링(로직 통일)

**주의**

리플렉션을 사용하면 클래스와 메서드의 메타정보를 사용해 애플리케이션을 동적으로 `유연`하게 만들 수 있다.

하지만, 런타임에 동작하기 때문에 컴파일 시점에 오류를 잡을 수 없다는 치명적 문제(문자열 하드코딩)와, 성능 저하 문제가 있기 때문에 주의해서 사용해야 한다.

1. 성능 저하 (JVM 최적화, JIT 컴파일) 
2. 타입 안정성 부족(Type Safety X)
3. 접근 제한 우회
4. 예외 처리 복잡
---

# JDK 동적 프록시

- 동적 프록시 예제 세팅

`TimeInvocationHadler`
- TimeInvocationHandler는 InvocationHandelr를 구현
- 이렇게 해야 JDK 동적 프록시에 적용할 공통 로직을 개발 가능
- Object target : 동적 프록시가 호출할 대상
- method.invoke(target, args) : 리플렉션을 사용해서 `target` 인스턴스의 메서드를 실행
    - `args`는 메서드 호출시 넘겨줄 인수

### TimeInvocationHandler 동적 프록시 동작 과정
1. 클라이언트는 JDK 동적 프록시의 `call()` 을 실행
2. JDK 동적 프록시는 `InvocationHandler.invoke()`를 호출 (동적 프록시가 call() 호출을 가로챔)
   - `TimeInvocationHandler`가 구현체로 있기 때문에 `TimeInvocationHandler.invoke()`가 실제로 호출
3. `TimeInvotaionHandler`가 내부 로직을 수행하고, `method.invoke(target, args)`를 호출해서 `target`인 실제 객체 `AImpl(BImpl)`를 호출
4. AImpl(BImpl) 인스턴스의 `call()` 이 실행
5. `call()`의 실행이 끝나면 `TimeInvocationHandler`로 응답이 돌아온다.
   - 시간 로그를 출력하고 결과를 반환

> 정리하면, AImpl과 BImpl의 프록시 객체를 각각 생성하지 않고, 동적으로 만들어 공통으로 사용할 수 있게 되었다.
---

### 실제 애플리케이션 로직에 적용
#### `LogTraceHandler`

- `InvocationHandler` 인터페이스를 구현해 JDK 동적 프록시에서 사용됨
- private final Object target : 프록시 대상
- String message : `LogTrace`에 사용할 메시지