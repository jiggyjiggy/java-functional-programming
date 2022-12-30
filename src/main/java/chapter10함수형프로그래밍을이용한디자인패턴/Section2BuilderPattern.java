package chapter10함수형프로그래밍을이용한디자인패턴;

import chapter10함수형프로그래밍을이용한디자인패턴.model.User;

public class Section2BuilderPattern {
    /*
    * 대표적인 생성 패턴
    * 객체의 생성에 대한 로직과 표현에 대한 로직을 분리해준다
    * 객체의 생성과정을 유연하게 해준다
    * 객체의 생성 과정을 정의하고 싶거나 필드가 많아 constructor가 복잡해질 때 유용하다
    * */

    public static void main(String[] args) {
//        User user = User.builder(101, "Alice")
//                .withEmailAddress("alice@naver.com")
//                .withVerified(true)
//                .build();
//        System.out.println(user);

        User user = User.builder(101, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@naver.com";
                    builder.isVerified = true;
                }).build();
        System.out.println(user);
    }
}
