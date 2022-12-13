package chapter4FunctionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Section2Consumer {
    public static void main(String[] args) {
        List<String> stringInputs = Arrays.asList("hello", "world");

        Consumer<String> myStringConsumer = System.out::println;
        process(stringInputs, myStringConsumer);

//        myStringConsumer.accept("hello");
//        myStringConsumer.accept("world!");

        List<Integer> integerInputs = Arrays.asList(4, 2, 3);
        Consumer<Integer> myIntegerProcessor = x -> System.out.println("Processing integer " + x);
        process(integerInputs, myIntegerProcessor);

        Consumer<Integer> myDifferentIntegerProcessor = x -> System.out.println("Processing integer in different way " + x);
        process(integerInputs, myDifferentIntegerProcessor);
    }
//    private static void process(List<Integer> inputs, Consumer<Integer> processor) {
//        for (Integer input :
//                inputs) {
//            processor.accept(input);
//        }
//    }
    private static <T> void process(List<T> inputs, Consumer<T> processor) {
        for (T input :
                inputs) {
            processor.accept(input);
        }
    }

}
