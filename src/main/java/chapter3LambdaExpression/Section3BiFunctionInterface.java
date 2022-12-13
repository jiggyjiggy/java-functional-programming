package chapter3LambdaExpression;

import java.util.function.BiFunction;

public class Section3BiFunctionInterface {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
//        BiFunction<Integer, Integer, Integer> adder = Integer::sum;
        Integer result = add.apply(3,5);
        System.out.println(result);
    }
}
