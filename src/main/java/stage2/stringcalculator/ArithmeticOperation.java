package stage2.stringcalculator;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum ArithmeticOperation {
    ADDITION("+", Integer::sum),
    SUBTRACTION("-", (a, b) -> a - b),
    MULTIPLICATION("*", (a, b) -> a * b),
    DIVISION("/", (a, b) -> a / b);

    private final String label;
    private final BiFunction<Integer, Integer, Integer> biFunction;

    ArithmeticOperation(String label, BiFunction<Integer, Integer, Integer> biFunction) {
        this.label = label;
        this.biFunction = biFunction;
    }

    static public ArithmeticOperation arithmetic(String label) {

        return Arrays.stream(values())
                .filter(operation -> operation.label.equals(label))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int calculation(int before, int after) {
        System.out.println("before " + before);
        System.out.println("산술연산자 " + label);
        System.out.println("after " + after);
        return biFunction.apply(before, after);
    }
}