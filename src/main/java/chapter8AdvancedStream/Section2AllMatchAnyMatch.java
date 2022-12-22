package chapter8AdvancedStream;

import chapter8AdvancedStream.model.Order;
import chapter8AdvancedStream.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Section2AllMatchAnyMatch {
    /*
    * allMatch - Stream 안의 모든 데이터가 predicate을 만족하면 true
    * anyMatch - Stream 안의 데이터 중 하나라도 predicate을 만족하면 true
    * */
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, -4, 2, 7, 9);
        boolean allPositive = numbers.stream()
                .allMatch(number -> number > 0);
        System.out.println("Are all numbers positive: " + allPositive);

        boolean anyNegative = numbers.stream()
                .anyMatch(number -> number < 0);
        System.out.println("Is any number negative: " + anyNegative);


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
        boolean areAllUserVerified = users.stream()
                .allMatch(User::isVerified);
        System.out.println(areAllUserVerified);


        // TODO: check if any of orders is in ERROR status
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
        boolean isAnyOrderInErrorStatus = orders.stream()
                .anyMatch(order -> order.getStatus().equals(Order.OrderStatus.ERROR));
        System.out.println(isAnyOrderInErrorStatus);

    }
}
