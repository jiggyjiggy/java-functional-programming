package chapter6Stream;

import chapter6Stream.model.Order;
import chapter6Stream.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Stream의 구성요소
 * source (소스) ; 컬렉션, 배열 등
 * intermediate operations (중간 처리) ; 0개 이상의 filter, map 등의 중간 처리
 * terminal operation (종결 처리) ; Collect, reduce 등
 * */
public class Section4Stream의구성요소 {
    public static void main(String[] args) {
        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setEmailAddress("alice@naver.com");
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setEmailAddress("bob@naver.com");
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setEmailAddress("charlie@naver.com");

        List<User> users = Arrays.asList(user1, user2, user3);

        List<String> emails = new ArrayList<>();
        for (User user :
                users) {
            if (!user.isVerified()) {
                String email = user.getEmailAddress();
                emails.add(email);
            }
        }
        System.out.println(emails);

        // what to do 에 대해 집중할 수 있다.
        List<String> emails2 = users.stream()
                .filter(Predicate.not(User::isVerified))
                .map(User::getEmailAddress)
                .collect(Collectors.toList());
        System.out.println(emails2);


        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = new Order()
                .setId(1001)
                .setStatus(Order.OrderStatus.CREATED)
                .setCreatedByUserId(101)
                .setCreatedAt(now.minusHours(4));
        Order order2 = new Order()
                .setId(1002)
                .setStatus(Order.OrderStatus.ERROR)
                .setCreatedByUserId(103)
                .setCreatedAt(now.minusHours(1));
        Order order3 = new Order()
                .setId(1003)
                .setStatus(Order.OrderStatus.PROCESSED)
                .setCreatedByUserId(102)
                .setCreatedAt(now.minusHours(36));
        Order order4 = new Order()
                .setId(1004)
                .setStatus(Order.OrderStatus.ERROR)
                .setCreatedByUserId(104)
                .setCreatedAt(now.minusHours(15));
        Order order5 = new Order()
                .setId(1005)
                .setStatus(Order.OrderStatus.IN_PROGRESS)
                .setCreatedByUserId(101)
                .setCreatedAt(now.minusHours(10));
        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        // TODO: Find orders in Error status, and extract createdByUserIds as a list
        List<Long> filteredUserIds = orders.stream()
                .filter(order -> order.getStatus().equals(Order.OrderStatus.ERROR))
                .map(Order::getCreatedByUserId)
                .collect(Collectors.toList());
        System.out.println(filteredUserIds);

        // TODO: Find orders in Error status and created within 24 hours
        List<Order> ordersInErrorStatusIn24hrs = orders.stream()
                .filter(order -> order.getStatus().equals(Order.OrderStatus.ERROR))
                .filter(order -> order.getCreatedAt().isAfter(now.minusHours(24)))
                .collect(Collectors.toList());
        System.out.println(ordersInErrorStatusIn24hrs);
    }


}
