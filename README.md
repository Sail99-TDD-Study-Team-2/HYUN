# Sail99-TDD-Study - 공부기록 What I've Learned

---

## (W01)Chap02 TDD 시작

- TDD : Test-driven Development 테스트 주도 개발.
- 테스트부터 시작 -> 코드 구현 -> 테스트 성공시키기
- 테스트 작성시 고민 : 클래스이름, 메서드이름, 파라미터 개수, 리턴 타입, 객체/정적메서드 여부(구현방식)

<br/>

- <span style='background-color:#ffdce0;color:#808080'>레드(Red)</span>-<span style='background-color:#dcffe4;color:#808080;'>그린(Green)</span>-<span style='background-color:#fff5b1;color:#808080'>리팩터</span> : 테스트 실패 > 테스트 성공 > 코드 리팩토링(가독성 개선 등)

<br/>

> p58  
> 테스트 코드가 있으면 리팩토링을 보다 과감하게 진행할 수 있다. 잘 동작하는 코드를 수정하는 것은 심리적으로 불안감을 주기 때문에 코드 수정을 꺼리게 만든다. 하지만 해당 기능이 온전하게 동작한다는 것을 검증해주는 테스트가 있으면 코드 수정에 대한 심리적 불안감을 줄여준다.
> 
> _리팩토링을 통한 개선을 원활하게 할 수 있게 도와준다._

  
업무를 할 때 테스트 코드가 없는 환경에서 일하다 보니 나의 작은 수정이 큰 사이드 이펙트를 만드는 경우가 있었다.  
몇 번 그런 경험을 한 이후 수동으로 테스트를 열심히 해보긴 했지만 한계가 있다는 생각을 했다.  
TDD가 나의 가려운 부분을 잘 긁어주지 않을까. 열심히해보장.


---

## (W02)Chap03 테스트 코드 작성 순서

### 💡 작성순서
#### 1. 쉬운 경우에서 어려운 경우로 진행 (쉬운 -> 어려운)
> p63  
> 한 번에 완벽한 코드를 만들면 좋겠지만, 모두가 수퍼 개발자인 것은 아니다.  
> 보통의 개발자는 한 번에 많은 코드를 만들다 보면 나도 모르게 버그를 만들고 나중에 버그를 잡기 위해 많은 시간을 허비하게 된다.  
> (...)  
> 그뿐만 아니라 코드 작성 시간이 길어지면 집중력도 떨어져서 흐름이 자주 끊기게 된다.  

*그러니까 쉬운 테스트부터 시작하기*  
점진적으로 구현을 완성해 나간다.

#### 2. 예외적인 경우에서 정상인 경우로 진행 (예외 -> 정상)
> p65  
> 예외 상황을 전혀 고려하지 않은 코드에 예외 상황을 반영하려면 코드의 구조를 뒤집거나 코드 중간에 예외 처리위한 조건문 중복 추가하는 경우 증가  
> -> 코드 복잡도 증가, 버그 발생 가능성 증가  

*예외상황 먼저 테스트하면 이런 가능성 감소함* 

<br/>

### 💡 완급조절
1. **정해진 값**을 리턴
2. **값 비교**를 이용해서 정해진 값을 리턴
3. 다양한 테스트를 추가하면서 구현을 **일반화**

<br/>

### 지속적인 리팩토링
> p68  
> 일단 동작하는 코드를 만드는 것은 중요. 하지만 소프트웨어의 생존 시간이 길어질수록 소프트웨어의 지속적인 개선이 요구됨. > 코드 변경 필요  
> 코드를 잘 변경하려면 리팩토링을 통해 코드변경이 쉬운 구조를 만든다.

> **테스트 대상 코드의 리팩토링 시점**  
> 작은 리팩토링(상수->변수, 변수이름 변경 등)은 발견즉시  
> 메서드 구조에 영향을 주는 리팩토링(메서드 추출 등)은 구현 흐름 파악 후 진행

<br/>

### 테스트할 목록 정리하기

- TDD시작 시 테스트할 목록을 미리 정리
- 그 중 어떤 테스트가 쉬울지 검토하며 구현 순서 조정
- 새로운 테스트 사례 발견시 목록에 추가하기
- 목록의 테스트를 한번에 다 작성하려고 하지 말자. 
  - 수정할 코드가 많을수록 리팩토링에 대한 심리적 저항이 생김.
- 리팩토링 범위가 크다면
  - 리팩토링보다는 테스트 통과에 집중
  - 리팩토링 진행 전 코드를 커밋하여 원복 쉽도록하기

---

## (W02)Chap04 TDD/기능 명세/설계

### 기능 명세

- 다양한 형태 
  - ex) 파워포인트 등 스토리보드, 이메일 등 간단한 문장, 지라 이슈, 회의 중 구두전달
- 핵심: **입력** 과 **결과**
  - 입력: 보통 파라미터로 전달
  - 결과: 리턴값 / 예외처리(Exception) / 변경
  - 변경은 실행 후 변경 대상에 접근해서 결과를 확인해야함.

<br/>

### 설계 과정을 지원하는 TDD

테스트 코드를 먼저 만들기 위해 필요한 것

- 테스트할 기능을 실행
  - 테스트 대상이 되는 클래스와 메서드의 이름, 메서드 실행 시 사용할 인자 등을 결정해야함
- 실행 결과를 검증
  - 결과 검증 방식 고민해야함.
- TDD자체가 설계는 아니지만, 테스트 코드를 작성하는 가정에서 일부 설계를 진행하게 된다.

<br/>

### 필요한 만큼 설계하기

> p105  
> TDD로 개발을 진행하면 현시점에서 테스트를 통과시키는데 필요한 만큼의 코드만 만들게된다.  
> 물론 모든 코드에 대해 테스트를 먼저 작성할 수는 없겠지만 TDD로 개발하는 코드 비율이 높아질수록 지금 시점에서 필요한 설계만 코드에 반영할 가능성이 커진다.  
> 유연한 설계는 필요한 시점에 추가한다. 이를 통해 설계가 불필요하게 복잡해지는 것을 방지할 수 있다.

*나는 뭔가 구현할때 항상 과하게 이런 저런 케이스를 생각하면서 구현할때가 많은 것 같다. 한 번에 완벽한 결과물을 내고 싶어서 그런데, 작게 작게 쪼개어서 필요한 만큼만 충족되게 만드는 습관도 필요한 것 같다.*

<br/>

### 기능 명세 구체화

> p106  
> 테스트 코드를 작성하려면 입력과 결과가 명확해야 하므로 애매한 점을 발견하면 기획자나 실무 담당자와 얘기해서 상황에 따라 기능이 어떻게 동작해야 하는지 구체적으로 정리해야 한다.

*커뮤니케이션이 참 중요하다*

---

## (W03)Chap05 JUnit 5 기초

### JUnit5 모듈 구성

- JUnit 플랫폼
  - 테스팅 프레임워크를 구동하기 위한 런처와 테스트 엔진을 위한 API제공
- JUnit 주피터(Jupiter)
  - JUnit5를 위한 테스트 API와 실행 엔진 제공
- JUnit 빈티지(Vintage)
  - JUnit3,4로 작성된 테스트를 JUnit5 플랫폼에서 실행하기 위한 모듈 제공

<br/>

```groovy
dependencies {
    testImplementation('org.junit.jupiter:junit-jupiter:5.5.0')
}

test {
    useJUnitPlatform()
}
```

<br/>

### @Test애노테이션과 테스트 메서드

- JUnit의 기본구조 - 테스트로 사용할 클래스 작성 후 `@Test` 애노테이션을 메서드에 붙이기
- `@Test`애노테이션을 붙인 메서드는 private이면 안됨
- JUnit의 Assertions 클래스 : 값을 검증하기 위한 다양한 정적 메서드 제공

<br/>

### 주요 단언 메서드

|메서드| 설명                               |
|---|----------------------------------|
|assertEquals(exptected, actual)| 실제 값actual이 기대값expected과 일치검사    |
|assertNotEquals(unexpected,actual)| 실제 값actual이 특정값unexpected의 불일치검사|
|assertSame(Object expected, Object actual)|두 객체가 동일한 객체인지 검사|
|assertNotSam(Object unexpected, Object actual)|두 객체 부동일객체인지 검사|
|assertTrue(boolean condition)|값condition이 true인지 검사|
|assertFalse(boolean condition)|값condition이 false인지 검사|
|assertNull(Object actual)|값이 null인지 검사|
|assertNotNull(Object actual)|값이 null이 아닌지 검사|
|fail()|테스트 실패처리|

- 익셉션 발생 유무가 검증 대상이라면 

|메서드|설명| 
|-|-|
|assertThrows(Class<T> expectedType,Executable executable)|executable을 실행한 결과로 지정한 타입의 익셉션이 발생하는지 검사|
|assertDoesNotThrow(Executable executable)|executable을 실행한 결과로 익셉션이 발생하지 않는지 검사|


- 일단 모든 검증을 실행하고 그 중 실패한 것 확인하고 싶다면
  - assertAll() 메서드 사용하기


<br/>

### 테스트 라이프사이클

- JUnit 테스트 메서드별 코드 실행 순서
  1. 테스트 메서드를 포함한 객체 생성
  2. (존재시) `@BeforeEach`애노테이션이 붙은 메서드 실행
  3. `@Test`애노테이션이 붙은 메서드 실행
  4. (존재시) `@AfterEach` 애노테이션이 붙은 메서드 실행
- `@BeforeEach` : 테스트 실행 준비 작업 ex. 임시파일생성, 객체생성
- `@AfterEach` : 테스트 실행 후 정리 ex. 테스트시 사용한 임시 파일 삭제
- 두 애노테이션이 붙은 메서드도 private이면 안됨.
- `@BeforeAll`, `@AfterAll` : 한 클래스의 모든 테스트 메서드가 실행되기 전 후로 특정 작업 수행.

<br/>

### 테스트 메서드 간 실행 순서 의존과 필드 공유하지 않기

- 각 테스트 메서드는 서로 독립적으로 동작해야 한다. 
- 테스트 메서드가 서로 필드를 공유한다거나 실행 순서를 가정하고 테스트를 작성하지 말아야 한다. 
- 테스트 메서드 간 의존이 생기면 이는 테스트 코드의 유지보수를 어렵게 만든다. 

<br/>

### 추가 애노테이션

- `@DisplayName` : 테스트의 표시이름 지정
- `@Disabled` : 특정 테스트 실행하지 않을때

<br/>

### 모든 테스트 실행하기

- `mvn test`(래퍼 사용시 `mvnw test`)
  - 메이븐의 라이프사이클에 따라 package 단계 실행시 test를 앞서 실행하므로 
  - `mvn package` 명령어를 실행해도 테스트 실행
- `gradle test`(래퍼 사용시 `gradlew test`)
  - 그레이들도 build 태스크 실행시 테스트 실행하므로
  - `gradle build` 명령어를 실행하면 테스트 실행


---

## (W03)Chap06 테스트 코드의 구성

### 테스트 코드의 구성 요소 : 상황(given), 실행(when), 결과 확인(then)

- **상황** 
  - 테스트할 대상에 따라 설정하는 방법이 달라짐.
    - ex. 야구게임 : 테스트 메서드 별 객체생성, `@BeforeEach`로 상황 설정
  - 상황이 없는 경우도 있음. 
    - ex. 암호강도측정
- **실행결과**
  - 가장 쉬운 방법 : **리턴값** 사용
  - 실행 결과로 익셉션 발생하는 것이 정상인 경우도 있음
- 상황-실행-결과 확인 구조에 너무 집착하지는 말자. 


<br/>

### 외부 상황과 외부 결과

상황에는 외부 요인도 있다.

- 외부 상태가 테스트 결과에 영향을 주지 않게 하기
  - 테스트는 언제 실행해도 항상 정상적으로 동작해야한다.
- 외부 상태와 테스트 어려움
  - 외부 요인 ex. 파일, DBMS, 외부 서버 등 다양
  - 테스트 대상이 아닌 외부 요인은 테스트 코드에서 다루기 힘들다. 
  - **대역**을 사용하면 테스트 작성이 쉬워진다. 

---

## (W03)Chap07 대역

대역 : 테스트 대상이 의존하는 대상의 실제 구현을 대신하는 구현. 대역을 통해 외부 상황이나 결과를 대체가능

### 대역의 필요성

> _외부 요인이 테스트에 관여하는 주요 예_  
> 테스트 대상에서 파일 시스템을 사용  
> 테스트 대상에서 DB로부터 데이터를 조회하거나 데이터를 추가  
> 테스트 대상에서 외부의 HTTP 서버와 통신  

<br/>

test double : 테스트에서 진짜 대신 사용할 대역

### 대역의 종류

- **스텁(Stub)** : 구현을 단순한 것으로 대체. 테스트에 맞게 단순히 원하는 동작을 수행.
- **가짜(Fake)** : 제품에는 적합하지 않지만, 실제 동작하는 구현을 제공. 
- **스파이(Spy)** : 호출된 내역을 기록. 기록한 내용은 테스트 결과를 검증할 때 사용.
  - 스텁이기도 함
- **모의(Mock)** : 기대한 대로 상호작용하는지 **행위**를 검증한다. 기대한 대로 동작하지 않으면 익셉션 발생.
  - 스텁이자 스파이도 된다.

> 구현하기 전에 모든 기능을 설계하는 것은 불가능하지만, 단위 기능을 구현하기에 앞서 어떤 구성 요소가 필요할지 고민하는 것은 의존 대상을 도출할 때 도움이 된다.

<br/>

### 상황과 결과 확인을 위한 협업 대상(의존) 도출과 대역 사용

제어하기 힘든 외부 **상황**이 존재할 경우
1. 제어하기 힘든 외부 상황을 별도 타입으로 분리
2. 테스트 코드는 별도로 분리한 타입의 대역을 생성
3. 생성한 대역을 테스트 대상의 생성자 등을 이용해서 전달
4. 대역을 이용해서 상황 구성

**결과 확인**을 위해서도 의존 도출 가능.
ex) 회원가입 성공 이후 이메일 발송 기능 테스트

**구현하는 데 시간이 오래 걸리는 로직**도 분리하기 좋은 후보  
당장 로직을 구현하지 않아도 관련 테스트를 통과시킬 수 있다.

<br/>

### 대역과 개발 속도

TDD 과정 중 대역을 사용하지 않는다면?  
➡️ 대기의 연속이다.

대역을 사용하면?
- 실제 구현이 없어도 다양한 상황에 대해 테스트할 수 있다.
- 실제 구현이 없어도 실행 결과를 확인할 수 있다.

➡️  의존하는 대상을 구현하지 않아도 테스트 대상을 완성할 수 있게 만들어주며 이는 대기 시간을 줄여주어 개발속도를 올리는데 도움이 된다.

<br/>

### 모의 객체를 과하게 사용하지 않기

모의 객체를 고하게 사용하면 오히려 테스트 코드가 복잡해지는 경우도 발생한다.

결과값을 확인하는 수단으로 모의 객체를 사용하기 시작하면 결과 검증 코드가 길어지고 복잡해진다.   
하나의 테스트를 위해 여러 모의 객체를 사용하기 시작하면 결과 검증 코드의 복잡도가 배로 증가한다.  

> 모의 객체는 기본적으로 **메서드 호출 여부**를 검증하는 수단    
> 결과 검증 수단으로 사용하는 것은 주의해야 한다.

---

## (W04)Chap08 테스트 가능한 설계

### 테스트 어려운 설계

- 하드코딩
  - ex. 경로, ip주소, 포트번호 등
- 정적메서드 사용
- 실행 시점에 따라 달라지는 결과 
  - ex. LocalDate.now() 사용 등
- 역할이 섞여있는 코드

<br/>

### 테스트 가능한 설계

- 하드코딩 -> **생성자, 메서드 파라미터**로 받기 
- 의존 대상을 주입 받기 (생성자, 세터를 주입수단으로 이용)
- 테스트하고 싶은 코드를 분리하기
- 시간이나 임의 값 생성 기능 분리하기
- 외부 라이브러리는 직접 사용하지 말고 감싸서 사용하기

---

## (W04)Chap09 테스트 범위와 종류

- 기능 테스트(Functional Testing) : 사용자 입장에서 시스템이 제공하는 기능이 올바르게 동작하는지 확인. 
  - **E2E테스트** (End to end)
- 통합 테스트(Integration Testing) : 시스템의 각 구성 요소가 올바르게 연동되는지 확인.
  - 소프트웨어의 **코드를 직접 테스트**
- 단위 테스트(Unit Testing) : 개별 코드나 컴포넌트가 기대한대로 동작하는지 확인.

---

## (W04)Chap10 테스트 코드와 유지보수

### 테스트 코드와 유지보수

- TDD를 하는 과정에서 작성한 테스트 코드는 CI/CD에서 자동화 테스트로 사용되어 버그가 배포되는 것을 막아주고 이는 소프트웨어 품질이 저하되는 것을 방지한다.
- 깨지는 테스트를 방치하는 상황이 길어지면 > **회귀 테스트**가 검증하는 범위가 줄어든다.
  - 회귀 테스트(regression test): 코드를 변경했을 때 기존 기능이 올바르게 동작하는지 확인하는 것.
  - 소프트웨어 품질이 낮아질 가능성 Up

=> 테스트 코드 자체의 유지보수성이 좋아야 지속적으로 테스트를 작성하고, 소프트웨어 품질 향상

<br/>

### 체크하자

1. 변수나 필드를 사용해서 기댓값 표현하지 않기
   - 기대하는 값을 명확하고 알기 쉽게 표현하기 위함

<br/>

2. 두 개 이상을 검증하지 않기
   - 각 검증 대상을 별도로 분리해서 테스트의 집중도를 높인다.
   - 반드시 한가지만 검증해야 하는 것은 아니지만, 검증대상이 명확하게 구분된다면 테스트 메서드도 구분하는 것이 유지보수에 유리하다.

<br/>

3. 정확하게 일치하는 값으로 모의 객체 설정하지 않기
   - 모의 객체는 가능한 범용적인 값을 사용해서 기술해야 한다. 
     - 한정된 값에 일치하도록 모의객체를 사용하면 약간의 코드 수정만으로도 테스트는 실패하게 된다.
     - `"pw"` 보다는 `Mockito.anyString()`

<br/>

4. 과도하게 구현 검증하지 않기
   - 내부 구현을 검증하는 것이 나쁜 것은 아니지만..
     - 구현을 조금만 변경해도 테스트가 깨질 가능성 up

   - 테스트 코드는 내부 구현보다 **실행 결과**를 검증해야 한다.

> 기능이 정상적으로 동작하는지 확인할 수단이 구현 검증밖에 없다면 모의 객체를 사용해서 테스트 코드를 작성해야 하지만 일단 테스트 코드를 작성한 뒤에는 점진적으로 코드를 리팩토링해서 결과를 검증할 수 있도록 시도해야 한다.

<br/>

5. 셋업을 이용해서 중복된 상황을 설정하지 않기
   - 각 테스트 메서드는 별도 프로그램으로서 검증 내용을 스스로 잘 설명할 수 있어야 한다.
     - 그러기 위해서는 상황 구성 코드가 테스트 메서드 안에 위치해야 한다.

<br/>

6. 통합 테스트에서 데이터 공유 주의하기
   - 통합 테스트 코드 작성 시 초기화 데이터 구분
     - 모든 테스트가 **같은 값**을 사용하는 데이터 ex. 코드값 데이터
       - 동일한 데이터 공유해도 됨
     - 테스트 메서드에서만 필요한 데이터 ex. 중복 ID 검사를 위한 회원 데이터
       - 데이터 공유할 필요가 없다

<br/>

7. 통합 테스트의 상황 설정을 위한 보조 클래스 사용하기

<br/>

8. 실행 환경이 다르다고 실패하지 않기
   - 실행 환경에 따라 문제가 되는 전형적인 예 - 파일 경로
   - 테스트에서 사용하는 파일은 프로젝트 폴더를 기준으로 상대 경로를 사용해야 한다.

<br/>

9. 실행 시점이 다르다고 실패하지 않기
   - 랜덤하게 실패하지 않기
   
<br/>

10. 필요하지 않은 값은 설정하기 않기
    - 단위 테스트를 위한 객체 생성 보조 클래스

<br/>

11. 조건부로 검증하지 않기

12. 통합 테스트는 필요하지 않은 범위까지 연동하지 않기

13. 더 이상 쓸모없는 테스트 코드

---

## (W04)Chap11 마치ㅁ

> **회귀테스트(Regression test)**  
> 개발하고 테스트한 소프트웨어가 이후에 코드를 수정해도 기존 코드가 올바르게 동작하는지 확인하기 위한 테스트.

### TDD전파하기

전파 이전에 먼저 내가 익숙해 져야한다. -> 개인프로젝트를 통해 연마!

#### 레거시 코드에 대한 테스트 추가
  위험성을 감수하더라도 테스트 코드를 만들 수 있게 리팩토링하는 것이 낫다.

