package chapter10함수형프로그래밍을이용한디자인패턴;

import chapter10함수형프로그래밍을이용한디자인패턴.model.User;
import chapter10함수형프로그래밍을이용한디자인패턴.service.EmailProvider;
import chapter10함수형프로그래밍을이용한디자인패턴.service.EmailSender;
import chapter10함수형프로그래밍을이용한디자인패턴.service.MakeMoreFriendsEmailProvider;
import chapter10함수형프로그래밍을이용한디자인패턴.service.VerifyYourEmailAddressEmailProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Section4StrategyPattern {
    /*
    * 대표적인 행동 패턴
    * 런타임에 어떤 전략(알고리즘)을 사용할 지 선택할 수 있게 해준다
    * 전략들을 캡슐화 하여 간단하게 교체할 수 있게 해준
    * */

    public static void main(String[] args) {
        User user1 = User.builder(101, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@naver.com";
                    builder.isVerified = false;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
                }).build();
        User user2 = User.builder(102, "Bob")
                .with(builder -> {
                    builder.emailAddress = "bob@naver.com";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(212, 213, 214);
                }).build();
        User user3 = User.builder(103, "Charlie")
                .with(builder -> {
                    builder.emailAddress = "charlie@naver.com";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212);
                }).build();
        List<User> users = Arrays.asList(user1, user2, user3);

        EmailSender emailSender = new EmailSender();
        EmailProvider verifyYourEmailAddressEmailProvider = new VerifyYourEmailAddressEmailProvider();
        EmailProvider makeMoreFriendsEmailProvider = new MakeMoreFriendsEmailProvider();

        emailSender.setEmailProvider(verifyYourEmailAddressEmailProvider);
        users.stream()
                .filter(Predicate.not(User::isVerified))
                .forEach(emailSender::sendEmail);

        emailSender.setEmailProvider(makeMoreFriendsEmailProvider);
        users.stream()
                .filter(User::isVerified)
                .filter(user -> user.getFriendUserIds().size() <= 5)
                .forEach(emailSender::sendEmail);

        emailSender.setEmailProvider(user -> "'Play With Friends' email for " + user.getName());
        users.stream()
                .filter(User::isVerified)
                .filter(user -> user.getFriendUserIds().size() > 5)
                .forEach(emailSender::sendEmail);


    }
}
