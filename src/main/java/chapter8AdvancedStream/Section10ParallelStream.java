package chapter8AdvancedStream;

import chapter8AdvancedStream.model.User;
import chapter8AdvancedStream.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Section10ParallelStream {
    /*
    * Sequential vs. Parallel
    * 여러개의 스레드를 이용하여 stream의 처리 과정을 병렬화 (parallelize)
    * 중간 과정은 병렬 처리 되지만 순서가 있는 Stream의 경우 종결 처리 했을때의 결과물이 기존의 순차적 처리와 일치하도록 종결 처리과정에서 조정된다. 즉 List로 collect한다면 순서가 항상 올바르게 나온다는 것.
    *
    * 장점:
    *   굉장히 간단하게 병렬 처리를 사용할 수 있게 해준다
    *   속도가 비약적으로 빨라질 수 있다
    * 단점:
    *   항상 속도가 빨라지는 것은 아니다
    *   공통으로 사용하는 리소스가 있을 경우 잘못된 결과가 나오거나 아예 오류가 날 수도 있다 (deadlock)
    *   이를 막기 위해 mutex, semaphore등 병렬 처리 기술을 이용하면 순차처리보다 느려질 수도 있다
    * */

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
        User user4 = new User()
                .setId(104)
                .setName("Paul")
                .setVerified(true)
                .setEmailAddress("paul@naver.com");
        User user5 = new User()
                .setId(105)
                .setName("David")
                .setVerified(false)
                .setEmailAddress("david@naver.com");
        User user6 = new User()
                .setId(106)
                .setName("John")
                .setVerified(false)
                .setEmailAddress("john@naver.com");
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6);

        long startTime = System.currentTimeMillis();
        EmailService emailService = new EmailService();
        users.stream()
                .filter(Predicate.not(User::isVerified))
                .forEach(emailService::sendVerifyYourEmailEmail);
        long endTime = System.currentTimeMillis();
        System.out.println("Sequential: " + (endTime - startTime) + "ms");


        startTime = System.currentTimeMillis();
        users.stream().parallel()
                .filter(Predicate.not(User::isVerified))
                .forEach(emailService::sendVerifyYourEmailEmail);
        endTime = System.currentTimeMillis();
        System.out.println("Parallel: " + (endTime - startTime) + "ms");
        List<User> processedUsers = users.parallelStream()
                .map(user -> {
                    System.out.println("Capitalize user name for user " + user.getId());
                    user.setName(user.getName().toUpperCase());
                    return user;
                })
                .map(user -> {
                    System.out.println("Set 'isVerified' to true for user " + user.getId());
                    user.setVerified(true);
                    return user;
                })
                .collect(Collectors.toList());
        System.out.println(processedUsers);
    }


}
