package step2;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringCalculateTest {
	private StringCalculator calculator;

	@BeforeEach
	void setUp() {
		calculator = new StringCalculator();
	}
	@Test
	@DisplayName("빈 문자열이거나 null 일 때 0 처리")
	void blankOrNull() throws Exception {
		String str1 = "";
		String str2 = null;

		assertThat(calculator.calculate(str1)).isEqualTo(0);
		assertThat(calculator.calculate(str2)).isEqualTo(0);
	}

	@ParameterizedTest
	@DisplayName("기본 구분자로 split 하기")
	@ValueSource(strings = {"1,2,3", "1:2:3"})
	void splitByOtherSeparators(String input) throws Exception {
		StringCalculator calculator = new StringCalculator();

		assertThat(new String[] {"1", "2", "3"}).isEqualTo(calculator.split(input));
	}

	@Test
	@DisplayName("분리된 문자배열을 숫자배열로 반환")
	void convertToIntArray() throws Exception {
		StringCalculator calculator = new StringCalculator();
		String[] arr = {"1", "2", "3"};

		assertThat(new int[] {1, 2, 3}).isEqualTo(calculator.convertToInt(arr));
	}

	@Test
	@DisplayName("숫자배열 더하기")
	void addInt() throws Exception {
		StringCalculator calculator = new StringCalculator();
		int[] arr = {1, 2, 3};

		assertThat(6).isEqualTo(calculator.add(arr));
	}

	@ParameterizedTest
	@DisplayName("음수 또는 숫자가 아닐 때 RuntimeException throw")
	@ValueSource(strings = {"-1,2,3", "a:2:3"})
	void minusOrNotNumber(String input) throws Exception {
		StringCalculator calculator = new StringCalculator();
		String[] split = calculator.split(input);

		assertThatThrownBy(() -> calculator.convertToInt(split))
			.isInstanceOf(RuntimeException.class);
	}

	@ParameterizedTest
	@DisplayName("덧셈 테스트")
	@CsvSource(value = {
		"1,2,3;6",
		"4:5,6;15"
	}, delimiter = ';')
	void plus(String input, int expected) {
		assertThat(calculator.calculate(input)).isEqualTo(expected);
	}
}
