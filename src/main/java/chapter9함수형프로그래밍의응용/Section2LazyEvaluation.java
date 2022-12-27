package chapter9함수형프로그래밍의응용;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Section2LazyEvaluation {
    /*
    * Lambda의 계산은 그 결과값이 필요할 때가 되어서야 계산된다
    * 이를 이용하여 불필요한 계산을 줄이거나 해당 코드의 실행 순서를 의도적으로 미룰 수 있다
    * */
    public static void main(String[] args) {
        if (returnTrue() || returnFalse()) {
            System.out.println("true");
        }

        if (or(returnTrue(), returnFalse())) {
            System.out.println("true");
        } // 최적화가 안됌 <- or에서 두 값을 알아야 연산 가능하기 때문에.

        if (lazyOr(() -> returnTrue(), () ->returnFalse())) {
            System.out.println("true");
        }

        Stream<Integer> integerStream = Stream.of(3, -2, 5, 8, -3, 10)
                .filter(x -> x > 0)
                .peek(x -> System.out.println("Peeking " + x))
                .filter(x -> x % 2 == 0);
        System.out.println("Before collect");

        List<Integer> integers = integerStream.collect(Collectors.toList());
        System.out.println("After collect: " + integers);
    }

    public static boolean or(boolean x, boolean y) {
        return x || y;
    }

    public static boolean lazyOr(Supplier<Boolean> x, Supplier<Boolean> y) {
        return x.get() || y.get();
    }
    public static boolean returnTrue() {
        System.out.println("Returning true");
        return true;
    }
    public static boolean returnFalse() {
        System.out.println("Returning false");
        return false;
    }
}
