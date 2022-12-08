package chapter3;

import chapter3.util.TriFunction;

public class Section4 {
    public static void main(String[] args) {
        TriFunction<Integer, Integer, Integer, Integer> add = (x, y, z) -> x + y + z;

        Integer result = add.apply(3,5,7);
        System.out.println(result);
    }
}
