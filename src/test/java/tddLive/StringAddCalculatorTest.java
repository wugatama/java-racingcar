package tddLive;

import static org.assertj.core.api.Assertions.*;
import static tddLive.StringAddCalculator.*;

import org.junit.jupiter.api.Test;

public class StringAddCalculatorTest {
	@Test
	void null_또는_space() {
		assertThat(splitAndSum("")).isEqualTo(0);
		assertThat(splitAndSum(null)).isEqualTo(0);
	}

	@Test
	void 문자열_하나() {
		assertThat(splitAndSum("1")).isEqualTo(1);
	}

	@Test
	void 문자열_쉼표_구분자() {
		assertThat(splitAndSum("1,2")).isEqualTo(3);
	}

	@Test
	void 문자열_콜론_구분자() {
		assertThat(splitAndSum("1:2:3")).isEqualTo(6);
	}

	@Test
	void 숫자_음수() {
		assertThatThrownBy(() -> splitAndSum("1:2:-3"))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("음수 값이 들어올 수 없습니다.");
	}
}
