package chapter3LambdaExpression;

import chapter3LambdaExpression.util.Adder;

import java.util.function.Function;

public class Section1FunctionInterface {
    public static void main(String[] args) {
        Function<Integer, Integer> myAdder = new Adder();
        int result = myAdder.apply(5);
        System.out.println(result);
    }
}
