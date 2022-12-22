package chapter8AdvancedStream;

import chapter8AdvancedStream.model.Order;
import chapter8AdvancedStream.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Section6ToMap {
    /*
    * Stream 안의 데이터를 map의 형태로 반환해주는 collector
    * keyMapper - 데이터를 map의 key로 변환하는 Function
    * value - Mapper - 데이터를 map의 value로 변환하는 Function
    * */

    public static void main(String[] args) {
        Map<Integer, String> numberMap = Stream.of(3, 5, -4, 2, 6)
//                .collect(Collectors.toMap(x -> x, x -> "Number is " + x));
                .collect(Collectors.toMap(Function.identity(), x -> "Number is " + x));
        System.out.println(numberMap);

        User user1 = new User()
                .setId(101)
                .setName("Paul")
                .setVerified(true)
                .setEmailAddress("alice@naver.com");
        User user2 = new User()
                .setId(102)
                .setName("David")
                .setVerified(false)
                .setEmailAddress("bob@naver.com");
        User user3 = new User()
                .setId(103)
                .setName("John")
                .setVerified(false)
                .setEmailAddress("charlie@naver.com");
        List<User> users = Arrays.asList(user1, user2, user3);
        Map<Integer, User> userIdToUserMap = users.stream()
//                .collect(Collectors.toMap(User::getId, x -> x));
                .collect(Collectors.toMap(User::getId, Function.identity()));
        System.out.println(userIdToUserMap);


        // TODO: Create a map from order id to order status
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

        Map<Long, Order.OrderStatus> orderIdToOrderStatusMap = orders.stream()
                .collect(Collectors.toMap(Order::getId, Order::getStatus));
        System.out.println(orderIdToOrderStatusMap);
    }
}
