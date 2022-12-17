package chapter5MethodReference;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <h1>method reference</h1>
 * <li>case 1</li>
 * ClassName::staticMethodName ; 클래스의 static method를 지정할때
 * <li>case 2</li>
 * objectName::instanceMethodName ; 선언 된 객체의 instance method를 지정할 때
 * <li>case 3</li>
 * ClassName::instanceMethodName ; 객체의 instance method를 지정할 때
 * <h1>constructor reference</h1>
 * <li>case 1</li>
 * ClassName::new ; 클래스의 constructor를 지정할 때
 * */
public class Section1MethodReference {
    private static void case1() {
        Function<String, Integer> str2int = Integer::parseInt;
        int five = str2int.apply("5");
    }

    private static void case2() {
        String str = "hello";
        Predicate<String> equalsToHello = str::equals;
        boolean helloEqualsWorld = equalsToHello.test("world");
    }

    public static void main(String[] args) {
        System.out.println(calculate(8, 2, (x,y)-> x + y));
        System.out.println(calculate(8, 2, Section1MethodReference::multiply));

        System.out.println(calculate(8, 2, new Section1MethodReference()::subtract));
    }

    private static int calculate(int x, int y, BiFunction<Integer, Integer, Integer> operator) {
        return operator.apply(x, y);
    }

    private static int multiply(int x, int y) {
        return x*y;
    }

    private int subtract(int x, int y) {
        return x - y;
    }

    private void myMethod() {
        System.out.println(calculate(10, 3, this::subtract));
    }
}
