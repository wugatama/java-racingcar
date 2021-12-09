1. 각 자동차에 이름을 부여할 수 있다. 자동차 이름은 5자를 초과할 수 없다.
> 실패시 IllegalArgumentException 를 발생시킴
2. 전진하는 자동차를 출력할 때 자동차 이름을 같이 출력한다.
> RacingCar 객체들을 반환, 여기서 거리와 이름을 가져옴
3. 자동차 이름은 쉼표(,)를 기준으로 구분한다.
>  split으로 출력시킨다.
4. 자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한명 이상일 수 있다.
> 최대 거리를 구한 뒤 이에 해당하는 객체 리스트를 구한다.
> 

[리펙토링 로그]
1. RacingCar에 의존성이 있는 RandomCondition을 제거하고 Object Graph를 상위로 이동시켰다.

이후 RandomCondition을 랜덤값에 따라 테스트 하도록 수정하였는데, int가 아닌 객체로 변경하고자 하였다.

이때, 랜덤 숫자의 범위(10)과 전진 조건(4)를 어느 객체에서 가지고 있어야 할지 고민하였는데 

RandomValue가 Random에 의존성이 있고 10 이하의 랜덤한 수를 생성하고 RamdomCondition이 이 값에 따른 전진 조건 비교를 통해 
RacingCar가 전진한다고 판단하는 경우 테스트하기 어렵다고 판단하였다. 

매 회차 마다 자동자 들이 생성이 되고 이때 랜덤 수에 따라 전진 여부가 결정되는데 자동차가 범위를 이에 따른 RandomValue를 생성하고 
조건을 가지고 있는 RancomCondition이 조건 만족 여부를 판단하도록 변경하였다. 

그렇게 되면 RandomValue > getValue 와 조건을 비교해야 하는데 메세지를 보낼 수 있는 방법이 없을까?

2.
RacingCarGame : RamdomCondition 속성으로 가지며 게임 룰을 제어, racingCar들이 하나의 Random 객체를 사용할 수 있도록 함


RacingCar :  RandomValue를 생성 

-> 이럴 경우 전진하는 것을 테스트 불가, 리펙토링 필요 

=> RamdomCondition을 파라미터로 받도록 수정

RandomCondition : 전진 조건을 결정

RandomValue : 조건에 따른 적합 여부를 결정 

3. RacingCar의 속성인 참조 변수들을 다 불변 객체로 변경하고 속성 변경시 새로운 객체를 반환하도록 수정하였다.

그리고 List<RacingCar>를 RacingCars로 포장하였는데 RacingGame에서 RandomCondition을 가지는 경우

다수의 RacingCar 이동을 위한 RandomCondition을 생성하여 RacingCars에게 주는건 올바르지 않다고 판단하였다.

이에, RandomCondition을 RacingCars로 이동한다. 

4. RacingCarGame > equals를 테스트시 RacingCars에서 다른 이름을 가진 RacingCar로 객체 생성시 true로 결과가 나옴

RacingCars내 list 비교가 잘못되었다고 생각했지만 결과로 RacingCar의 equals가 올바르지 않았다.

테스트 코드를 모두 작성해 보자.

5. RacingCars 생성시 자동차 개수, 이름 목록을 통해 생성하도록 변경을 하니 이후 우승자 찾기시 테스트가 어려웠다.

생성자로 자동차 목록을 받도록 개선하여 테스트가 가능하도록 하였다. 



