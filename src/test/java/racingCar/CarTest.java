package racingCar;

import static org.assertj.core.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarTest {
	private Car car;

	@BeforeEach
	void setUp() {
		car = new Car();
	}

	@Test
	@DisplayName("자동차 이름 5자 초과하면 예외발생")
	void carNameOverFive() {
		assertThatThrownBy(() -> car.createName("abcdef"))
			.isInstanceOf(RuntimeException.class);
	}

	@Test
	@DisplayName("자동차 이름 5자 이하면 통과")
	void carNameBelowFive() {
		car.createName("abcde");
		assertThat(car.getName()).isEqualTo("abcde");
	}

	@Test
	@DisplayName("라운드 당 랜덤값 상태 확인")
	void carStatusOfRound() {
		Random random = new Random();
		car.movingOfRound(random.nextInt(10));
		assertThat(car.getStatus().size()).isEqualTo(1);
	}
}
