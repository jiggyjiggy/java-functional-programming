package chapter10함수형프로그래밍을이용한디자인패턴.service;

import chapter10함수형프로그래밍을이용한디자인패턴.model.User;

public class UserService extends AbstractUserService {
    @Override
    protected boolean validateUser(User user) {
        System.out.println("Validating user " + user.getName());
        return user.getName() != null && user.getEmailAddress().isPresent();
    }

    @Override
    protected void writeToDB(User user) {
        System.out.println("Writing user " + user.getName() + " to DB");
    }
}
