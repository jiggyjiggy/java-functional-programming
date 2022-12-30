package chapter10함수형프로그래밍을이용한디자인패턴.service;

import chapter10함수형프로그래밍을이용한디자인패턴.model.User;

public class VerifyYourEmailAddressEmailProvider implements EmailProvider {
    @Override
    public String getEmail(User user) {
        return "'Verify Your Email Address' email for " + user.getName();
    }
}
