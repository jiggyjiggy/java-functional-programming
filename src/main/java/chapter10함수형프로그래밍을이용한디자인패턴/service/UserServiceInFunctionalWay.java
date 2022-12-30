package chapter10함수형프로그래밍을이용한디자인패턴.service;

import chapter10함수형프로그래밍을이용한디자인패턴.model.User;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class UserServiceInFunctionalWay {
    private final Predicate<User> validateUser;
    private final Consumer<User> writeToDB;

    public UserServiceInFunctionalWay(Predicate<User> validateUser, Consumer<User> writeToDB) {
        this.validateUser = validateUser;
        this.writeToDB = writeToDB;
    }

    public void createUser(User user) {
        if (validateUser.test(user)) {
            writeToDB.accept(user);
        } else {
            System.out.println("Cannot create user");
        }
    }
}
