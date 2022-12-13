package chapter3LambdaExpression;

import chapter3LambdaExpression.util.TriFunction;

public class Section4FunctionalInterface {
    public static void main(String[] args) {
        TriFunction<Integer, Integer, Integer, Integer> add = (x, y, z) -> x + y + z;

        Integer result = add.apply(3,5,7);
        System.out.println(result);
    }
}
