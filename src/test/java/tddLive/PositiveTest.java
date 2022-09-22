package tddLive;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PositiveTest {
	@Test
	void create() {
		assertThat(new Positive("1")).isEqualTo(new Positive(1));
	}

	@Test
	void 덧셈() {
		Positive positive = new Positive("1");
		Positive result = positive.plus(1);
		assertThat(result).isEqualTo(new Positive(2));
	}

	@Test
	void 음수() {
		assertThatThrownBy(() -> {
			new Positive("-1");
		}).isInstanceOf(RuntimeException.class);
	}
}
