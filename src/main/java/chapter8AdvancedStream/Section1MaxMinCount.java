package chapter8AdvancedStream;



import chapter8AdvancedStream.model.Order;
import chapter8AdvancedStream.model.OrderLine;
import chapter8AdvancedStream.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Section1MaxMinCount {
    public static void main(String[] args) {
        Optional<Integer> max = Stream.of(5, 3, 6, 2, 1)
//                .max((x, y) -> x - y);
//                .max(Comparator.comparingInt(x -> x));
                .max(Integer::compareTo);
        System.out.println(max.get());


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

        User firstUser = users.stream()
//                .min((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .min(Comparator.comparing(User::getName))
                .get();
        System.out.println(firstUser);


        long positiveIntegerCount = Stream.of(1, -4, 5, -3, 6)
                .filter(x -> x > 0)
                .count();
        System.out.println("Positive integers: " + positiveIntegerCount);


        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        user1.setCreatedAt(now.minusDays(2));
        user2.setCreatedAt(now.minusHours(10));
        user3.setCreatedAt(now.minusHours(1));
        User user4 = new User()
                .setId(104)
                .setName("David")
                .setVerified(true)
                .setEmailAddress("david@naver.com")
                .setCreatedAt(now.minusHours(27));
        users = Arrays.asList(user1, user2, user3, user4);

        long unverifiedUsersIn24Hrs = users.stream()
                .filter(user -> user.getCreatedAt().isAfter(now.minusDays(1)))
                .filter(user -> !user.isVerified())
                .count();
        System.out.println(unverifiedUsersIn24Hrs);


        // TODO: find order with highest amount an in ERROR status
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
        Order erroredOrderWithMaxAmount = orders.stream()
                .filter(order -> order.getStatus().equals(Order.OrderStatus.ERROR))
//                .max((o1, o2) -> o1.getAmount().compareTo(o2.getAmount()))
                .max(Comparator.comparing(Order::getAmount))
                .get();
        System.out.println(erroredOrderWithMaxAmount);

        BigDecimal maxErroredAmount = orders.stream()
                .filter(order -> order.getStatus() == Order.OrderStatus.ERROR)
                .map(Order::getAmount)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        System.out.println(maxErroredAmount);
    }
}
