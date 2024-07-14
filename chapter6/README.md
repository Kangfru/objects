## Chapter 6. 메시지와 인터페이스
### 1. 협력과 메시지
#### 클라이언트-서버 모델
- 협력은 어떤 객체가 다른 객체에게 무언가를 요청할 때 시작된다. 메시지는 객체사이의 협력을 가능하게 하는 매개체다.
- 두 객체 사이의 협력 관게를 설명하기 위해 사용하는 전통적인 메타포는 클라이언트-서버 모델이다.
- 대부분의 객체는 협력에 참여하는 동안 클라이언트와 서버 역할을 동시에 수행한다.
- 적합한 설계를 위해서는 서버로서 수신 받는 메시지뿐만 아니라 외부에 전송하는 메시지의 집합도 함께 고려해야 한다.

#### 메시지와 메시지 전송
- 메시지는 객체들이 협력하기 위해 사용할 수 있는 유일한 의사소통 수단이다.
- 메시지 전송은 한 객체가 다른 객체에게 도움을 요청하는 것을 뜻하며 메시지 패싱이라고도 한다.
- 메시지는 오퍼레이션 명과 인자로 구성되며 메시지 전송은 여기에 미시지 수신자를 추가한 것이다.
- 따라서 메시지 전송은 메시지 수신자, 오퍼레이션명, 인자의 조합이다.

#### 메시지와 메서드
- 메시지를 수신했을 때 실제로 어떤 코드가 실행되는지는 메시지 수신자의 실제 타입에 달려있다.
- 앞 장의 예로 DiscountCondition이라는 인터페이스로 정의된 condition이 PeriodCondition의 인스턴스라면 PeriodCondition의 구현이 실행될 것이고, SequenceCondition의 인스턴스라면 SequenceCondition의 구현이 실행된다.
- 이처럼 메시지를 수신했을 때 실제로 실행되는 함수 또는 프로시저를 **메서드**라 부른다.
- 전통적인 방식에서 개발자는 어떤 코드가 실행될 지 정확하게 알고 있는 상황에서 함수 호출을 한다. 다시 말해 컴파일 시점과 실행 시점에 코드의 의미가 동일하다.
- 하지만 객체의 새계에선 메시지와 메서드는 실행 시점에 연결되기 때문에 컴파일 시점과 실행 시점의 의미가 달라질 수 있다.
- 메시지와 메서드의 구분은 메시지 전송자와 메시지 수신자가 느슨하게 결합될 수 있게 한다.
- 메시지 전송자는 어떤 메시지를 전송해야하는 지만 알면 되고, 메시지 수신자 역시 누가 메시지를 전송하는 지 알 필요가 없다.
- 실행 시점에 메시지와 메서드를 바인딩하는 메커니즘은 두 객체 사이의 결합도를 낮춤으로써 유연하고 확장 가능한 코드를 작성할 수 있게 한다.

#### 퍼블릭 인터페이스와 오퍼레이션
- 외부의 객체는 오직 객체가 공개하는 메시지를 통해서만 객체와 상호작용할 수 있는데, 이 메시지의 집합을 퍼블릭 인터페이스라고 한다.
- 퍼블릭 인터페이스에 포함된 메시지를 오퍼레이션이라고 부른다. 오퍼레이션은 수행 가능한 어떤 행동에 대한 추상화이다.
- 오퍼레이션과 메서드의 차이점은 메서드는 실제로 실행되는 코드를 뜻한다. 이 메서드는 오퍼레이션의 여러 가능한 구현 중 하나다.

#### 시그니처
- 오퍼레이션의 이름과 파라미터 목록을 합쳐 시그니처라고 부른다.

### 2. 인터페이스와 설계 품질
좋은 인터페이스는 최소한의 인터페이스와 추상적인 인터페이스라는 조건을 만족해야 한다.
이를 가능하게 하는 것이 책임주도 설계인데 메시지를 먼저 선택함으로써 협력과는 무관한 오퍼레이션이 인터페이스에 스며드는 것을 방지한다.
이런 설계 지침이 좋은 인터페이스로 이끌어줄 수 있지만 훌륭한 인터페이스의 공통적인 특징이 설계에 대한 지름길을 제공한다.

#### 디미터 법칙
```java
public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Movie movie = screening.getMovie();
        
        boolean discountable = false;
        for (DiscountCondition condition : movie.getDiscountConditions) {
            if (condition.getType() == DiscountConditionType.PERIOD) {
                discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek())
                        && condition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0
                        && condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
            } else {
                discountable = condition.getSequence() == screening.getSequence();
            }
            if (discountable) {
                break;
            }
        }
    }
}
```
- 위의 설계는 Screening과의 결합도가 너무 높기 때문에 screening 내부의 구현이 바뀌면 ReservationAgency 도 모두 변경된다.
- 이는 Movie와 DiscountCondition도 마찬가지로 ReservationAgency가 직접 접근하기에 동일한 문제가 발생된다.
- 이와 같은 협력하는 객체의 내부 구조에 대한 협력으로 발생하는 설계 문제를 해결하기 위해 제안된 원칙이 바로 **디미터 법칙**이다.
- 디미터 법칙은 간단하게 객체의 내부 구조에 강하게 결합되지 않도록 협력 경로를 제한하라는 것이다.
```java
public Reservation reservation(Screening screening, Customer customer, int audienceCount) {
    Money fee = screening.calculateFee(audienceCount);
    return new Reservation(customer, screening, fee, audienceCount);
}
```
- this 객체, 메서드의 매개변수, this의 속성, this의 속성인 컬렉션의 요소, 메서드 내에서 생성된 지역 객체
- 위의 조건을 만족하는 인스턴스에만 메시지를 전송하도록 프로그램해야한다.

#### 묻지 말고 시켜라
- 메시지는 객체의 상태에 관해 묻지 말고 원하는 것을 시켜야 한다.
- 호출하는 객체는 이웃 객체가 수행하는 역할을 사용해 무엇을 원하는지 이야기하고 호출되는 객체는 어떻게 처리할 지 스스로 결정해야 한다.
- 이 법칙을 따르면서 밀접하게 연관된 정보와 행동을 함꼐 가지는 객체를 만들 수 있다.

#### 의도를 드러내는 인터페이스
- 메서드의 이름을 지을 때 어떻게 수행하느냐가 아니라 무엇을 하느냐에 초점을 맞춰야 한다
- 그렇게 해야 클라이언트의 관점에서 동일한 작업을 수행하는 메서드들을 하나의 타입 계층으로 묶을 수 있는 가능성이 커진다.
ex) isSatisfiedByPeriod, isSatisfiedBySequence X -> isSatisfiedBy O

### 3. 원칙의 함정
디미터 법칙과 묻지 말고 시켜라 스타일은 객체의 퍼블릭 인터페이스를 깔끔하고 유연하게 만들 수 있는 훌륭한 설계의 원칙이다.
하지만 절대적인 법칙은 아니다.
설계는 트레이드 오프의 산물이다. 그렇기 때문에 원칙이 현재 상황에 부적합하다면 과감하게 원칙도 무시할 줄 알아야 한다.

#### 디미터 법칙은 하나의 도트(.)를 강제하는 규칙이 아니다.
디미터 법칙은 하나의 도트만을 사용하는 것이 아니다.
```java
import java.util.stream.IntStream;

IntStream.of(1, 15, 20, 3, 9).filter(x -> x > 10).distinct().count();
```
위의 코드에서 처럼 of, filter, distinct는 모두 IntStream 인스턴스를 또 다른 IntStream 인스턴스로 변환한다.
때문에 디미터 법칙을 위반하지 않는다. 디미터 법칙은 결합도와 관련된 것이며, 이 결합도가 문제되는 것은 객체의 내부 구조가 외부로 노출되는 경우로 한정된다.
여기서 알 수 있듯 하나 이상의 도트를 사용하는 케이스가 모두 디미터 법칙을 위반하는 것이 아니다.
내부 구현에 대한 어떤 정보도 외부로 노출되지 않는다면 그것은 디미터 법칙을 준수한 것이다.

#### 결합도와 응집도의 충돌
일반적으로 어떤 객체의 상태를 물어본 후 반환된 상태로 객체를 변경하거나 작업하는 것을 묻지 말고 시켜라 스타일로 변경해야한다.
```java
public class Theater {
    public void enter(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().minusAmount(ticket.getFee());
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }
}

```
Theater는 Audience 내부에 포함된 Bag에 대해 질문한 후 반환된 결과를 이용해 Bag의 상태를 변경한다.
이 코드는 Audience의 캡슐화를 위반하기 때문에 변경을 해야한다.
```java
public class Audience {
    public Long buy(Ticket ticket) {
        if (bag.hasInvitation()) {
            bag.setTicket(ticket);
            return 0L;
        } else {
            bag.setTicket(ticket);
            bag.minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }
}
```
이렇게 Audience는 상태와 함께 상태를 조작하는 행동도 포함하기에 응집도가 높아졌다.

### 4. 명령-쿼리 분리 원칙
가끔씩은 필요에 따라 상태를 물어야하는 경우가 생긴다. 대상이 자료 구조인 경우 이에 해당한다. 자료 구조는 당연히 내부를 노출해야하므로 디미터 법칙관 무관하다.
- 루틴 : 어떤 절차를 묶어 호출가능하도록 이름을 부여한 기능 모듈을 루틴이라고 한다. 루틴은 프로시저와 함수로 나눌 수 있다.
- 프로시저 : 정해진 절차에 따라 내부의 상태를 변경하는 루틴의 한 종류.
- 함수 : 어떤 절차에 따라 필요한 값을 계산해서 반환하는 루틴의 한 종류.
- 프로시저는 부수효과를 발생 시킬 수 있지만 값을 반환할 수 없다. (명령)
- 함수는 값을 반환할 수 있지만 부수효과를 발생시킬 수 없다. (쿼리)
- 명령과 쿼리를 엄격하게 분류하면 객체의 부수효과를 제어하기가 수월해진다.
- 쿼리는 객체의 상태를 변경하지 않기 때문에 몇 번이고 반복적으로 호출하더라도 상관이 없다.
- 