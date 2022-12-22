package chapter8AdvancedStream;

import chapter8AdvancedStream.model.Order;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Section7GroupingBy {
    /*
    * Stream 안의 데이터에 classifier를 적용했을 때 결과값이 같은 값끼리 List로 모아서 Map의 형태로 반환해주는 collector
    * 이 때 key는 classifier의 결과값, value는 그 결과값을 갖는 데이터들.
    * 예를 들어 stream에 {1, 2, 5, 7, 9 ,12, 13}이 있을때 classifier가 x -> x % 3 이라면 반환되는 map은 {0=[9, 12], 1=[1, 7, 13], 2=[2, 5]}.
    *
    * 두 번째 매개변수로 downstream collector를 넘기는 것도 가능
    * 그 경우 List대신 collector를 적용시킨 값으로 map의 value가 만들어짐
    * 이 때 자주 쓰이는 것이 mapping / reducing 등의 collector
    * */

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
        Map<Integer, List<Integer>> unitDigitMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10));
        System.out.println(unitDigitMap);

        Map<Integer, Set<Integer>> unitDigitSet = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10, Collectors.toSet()));
        System.out.println(unitDigitSet);

        Map<Integer, List<String>> unitDigitStrMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10,
                        Collectors.mapping(number -> "unit digit is " + number, Collectors.toList())));
        System.out.println(unitDigitStrMap);


        // TODO: create a map from order status to the list of corresponding orders
        Order order1 = new Order()
                .setId(1001L)
                .setAmount(BigDecimal.valueOf(2000))
                .setStatus(Order.OrderStatus.CREATED);
        Order order2 = new Order()
                .setId(1002L)
                .setAmount(BigDecimal.valueOf(4000))
                .setStatus(Order.OrderStatus.ERROR);
        Order order3 = new Order()
                .setId(1003L)
                .setAmount(BigDecimal.valueOf(3000))
                .setStatus(Order.OrderStatus.ERROR);
        Order order4 = new Order()
                .setId(1004L)
                .setAmount(BigDecimal.valueOf(7000))
                .setStatus(Order.OrderStatus.PROCESSED);
        List<Order> orders = Arrays.asList(order1, order2, order3, order4);

        Map<Order.OrderStatus, List<Order>> orderStatusMap = orders.stream()
//                .collect(Collectors.groupingBy(order -> order.getStatus()));
                .collect(Collectors.groupingBy(Order::getStatus));
        System.out.println(orderStatusMap);


        Map<Order.OrderStatus, BigDecimal> orderStatusToSumAmountMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus,
                        Collectors.mapping(Order::getAmount, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        System.out.println(orderStatusToSumAmountMap);
    }
}
