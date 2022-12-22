package chapter8AdvancedStream;

import chapter8AdvancedStream.model.User;
import chapter8AdvancedStream.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Section9ForEach {
    /*
    * 제공된 action을 Stream의 각 데이터에 적용해주는 종결 처리 메서드
    * Java의 iterable 인터페이스에도 forEach가 있기 때문에 Stream의 중간처리가 필요없다면 iterable collection(Set, List 등)에서 바로 쓰는 것도 가능
    * */

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 5, 2, 1);
        numbers.stream().forEach(number -> System.out.println("The number is " + number));
        numbers.forEach(number -> System.out.println("The number is " + number));


        User user1 = new User()
                .setId(101)
                .setName("Paul")
                .setVerified(true)
                .setEmailAddress("alice@naver.com")
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214));
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

        EmailService emailService = new EmailService();
        users.stream()
                .filter(Predicate.not(User::isVerified))
                .forEach(emailService::sendVerifyYourEmailEmail);


        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println("Do an operation on user " + user.getName() + " at index " + i);
        }
        IntStream.range(0, users.size()).forEach(i -> {
            User user = users.get(i);
            System.out.println("Do an operation on user " + user.getName() + " at index " + i);
        });
    }
}
