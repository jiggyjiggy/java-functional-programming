package chapter4FunctionalInterface;

import java.util.function.Supplier;

public class Section1Supplier {
    public static void main(String[] args) {
        Supplier<String> myStringSupplier = () -> "hello world!";
        System.out.println(myStringSupplier.get());

//        Supplier<Double> myRandomDoubleSupplier = () -> Math.random(); // 함수가 "1등 시민" 이기에, random 함수를 넘길수 있음
        Supplier<Double> myRandomDoubleSupplier = Math::random;
        printRandomDoubles(myRandomDoubleSupplier, 5);
    }

    private static void printRandomDoubles(Supplier<Double> randomSupplier, int count) {
        for (int index = 0; index < count; index++) {
            System.out.println(randomSupplier.get());
        }
    }
}
