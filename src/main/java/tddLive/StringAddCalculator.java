package tddLive;

public class StringAddCalculator {

	public static final String SEPARATOR = ",|:";

	public static int splitAndSum(String text) {
		if (isBlank(text)) {
			return 0;
		}
		return sum(toInts(split(text)));
	}

	private static boolean isBlank(String text) {
		return text == null || text.isBlank();
	}

	private static String[] split(String text) {
		return text.split(SEPARATOR);
	}

	private static int sum(int[] numbers) {
		int result = 0;
		for (int number : numbers) {
			result += number;
		}
		return result;
	}

	private static int sum(Positive[] numbers) {
		int result = 0;
		for (Positive number : numbers) {
			result += number.getNumber();
		}
		return result;
	}

	private static int[] toInts(String[] values) {
		int[] numbers = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			numbers[i] = toPositive(values[i]);
		}
		return numbers;
	}

	private static int toPositive(String value) {
		int number = Integer.parseInt(value);
		if (number < 0) {
			throw new RuntimeException("음수 값이 들어올 수 없습니다.");
		}
		return number;
	}
}
