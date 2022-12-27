package chapter9함수형프로그래밍의응용;

import chapter9함수형프로그래밍의응용.model.Order;
import chapter9함수형프로그래밍의응용.model.OrderLine;
import chapter9함수형프로그래밍의응용.priceprocessor.OrderLineAggregationPriceProcessor;
import chapter9함수형프로그래밍의응용.priceprocessor.TaxPriceProcessor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Section3FunctionComposition {
    public static void main(String[] args) {
        Function<Integer, Integer> multiplyByTwo = x -> 2 * x;
        Function<Integer, Integer> addTen = x -> x + 10;

        Function<Integer, Integer> composedFunction = multiplyByTwo.andThen(addTen);
        System.out.println(composedFunction);


        Order unprocessedOrder = new Order()
                .setId(1001L)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))
                ));
        List<Function<Order, Order>> priceProcessors = getPriceProcessors(unprocessedOrder);
        Function<Order, Order> mergedPriceProcessors = priceProcessors.stream()
//                .reduce(Function.identity(), (priceProcessor1, priceProcessor2) -> priceProcessor1.andThen(priceProcessor2))
                .reduce(Function.identity(), Function::andThen);

        Order processedOrder = mergedPriceProcessors.apply(unprocessedOrder);
        System.out.println(processedOrder);
    }

    public static List<Function<Order, Order>> getPriceProcessors(Order order) {
        return Arrays.asList(new OrderLineAggregationPriceProcessor(), new TaxPriceProcessor(new BigDecimal("9.375")));
    }
}
