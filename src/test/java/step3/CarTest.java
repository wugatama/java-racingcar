package step3;

import static org.assertj.core.api.Assertions.*;

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
	@DisplayName("4이상 일때 전진 확인 테스트")
	void checkCarMoving() {
		car.move(Racing.move(4));
		assertThat(car.getStatus()).isEqualTo("-");
	}

	@Test
	@DisplayName("4이상 일때 전진 다음 정지 테스트")
	void checkCarMovingAndStopMoving() {
		car.move(Racing.move(4));
		car.move(Racing.move(3));
		assertThat(car.getStatus()).isEqualTo("-");
	}
}