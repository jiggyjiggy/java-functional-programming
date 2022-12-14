package chapter8AdvancedStream;

import chapter8AdvancedStream.model.Order;
import chapter8AdvancedStream.model.OrderLine;
import chapter8AdvancedStream.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Section4Reduce {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 4, -2, -5, 3);
        int sum = numbers.stream()
                .reduce((x, y) -> x + y)
                .get();
        System.out.println(sum);

        int min = numbers.stream()
                .reduce((x, y) ->  x > y ? x : y)
                .get();
        System.out.println(min);

        int product = numbers.stream()
                .reduce(1, (x, y) -> x * y);
        System.out.println(product);

        List<String> numberStrList = Arrays.asList("3" , "2", "5", "-4");
        int sumOfNumberStrList = numberStrList.stream()
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
        System.out.println(sumOfNumberStrList);
        int sumOfNumberStrList2 = numberStrList.stream()
                .reduce(0, (number, str) -> number + Integer.parseInt(str), Integer::sum);
        System.out.println(sumOfNumberStrList2);


        User user1 = new User()
                .setId(101)
                .setName("Paul")
                .setVerified(true)
                .setEmailAddress("alice@naver.com")
                .setFriendUserIds(Arrays.asList(201, 202, 203,204));
        User user2 = new User()
                .setId(102)
                .setName("David")
                .setVerified(false)
                .setEmailAddress("bob@naver.com")
                .setFriendUserIds(Arrays.asList(204, 205, 206));
        User user3 = new User()
                .setId(103)
                .setName("John")
                .setVerified(false)
                .setEmailAddress("charlie@naver.com")
                .setFriendUserIds(Arrays.asList(204, 205, 207));
        List<User> users = Arrays.asList(user1, user2, user3);
        int sumOfNumberOfFriends =users.stream()
                .map(User::getFriendUserIds)
                .map(List::size)
                .reduce(0, (x, y) -> x + y);
        System.out.println(sumOfNumberOfFriends);


        // TODO: find the sum of amounts
        Order order1 = new Order()
                .setId(1001L)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))
                ));
        Order order2 = new Order()
                .setId(1002L)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(2000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(3000))
                ));
        Order order3 = new Order()
                .setId(1003L)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))
                ));
        List<Order> orders = Arrays.asList(order1, order2, order3);

        BigDecimal sumOfAmounts = orders.stream()
                .map(Order::getOrderLines)
                .flatMap(List::stream)
                .map(OrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sumOfAmounts);

    }
}
