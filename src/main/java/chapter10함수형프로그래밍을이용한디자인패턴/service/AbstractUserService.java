package chapter10함수형프로그래밍을이용한디자인패턴.service;

import chapter10함수형프로그래밍을이용한디자인패턴.model.User;

public abstract class AbstractUserService {
    protected abstract boolean validateUser(User user);

    protected abstract void writeToDB(User user);

    public void createUser(User user) {
        if (validateUser(user)) {
            writeToDB(user);
        } else {
            System.out.println("Cannot create user");
        }
    }

}
