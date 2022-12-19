package chapter7Optional;

import chapter7Optional.model.User;

import java.util.Optional;

public class Section2Optional응용준비 {
    /*
     * Optional 응용을 위해 알아야 할 것들
     * ifPresent - Optional이 null이 아니라면 action을 실행
     * map - Optional이 null이 아니라면 mapper를 적용
     * flatMap - mapper의 리턴 값이 또 다른 Optional이라면 한 단계의 Optional이 되도록 납작하게 해줌
     * */
    public static void main(String[] args) {
        Optional<User> maybeUser1 = Optional.ofNullable(maybeGetUser(true));
        Optional<User> maybeUser2 = Optional.ofNullable(maybeGetUser(false));
        maybeUser1.ifPresent(System.out::println);
        maybeUser2.ifPresent(System.out::println);

        Optional<Integer> maybeId1 = Optional.ofNullable(maybeGetUser(true))
                .map(User::getId);
        Optional<Integer> maybeId2 = Optional.ofNullable(maybeGetUser(false))
                .map(User::getId);
        maybeId1.ifPresent(System.out::println);
        maybeId2.ifPresent(System.out::println);

        String userName = Optional.ofNullable(maybeGetUser(true))
                .map(User::getName)
                .map(name -> "The name is " + name)
                .orElse("Name is empty");
        System.out.println(userName);
        // 더 나아가서 model의 user의 getEmailAddress의 반환값을 Optional로 변경
        //Optional<Optional<String>> maybeEmail = Optional.ofNullable(maybeGetUser(false))
        Optional<String> maybeEmail = Optional.ofNullable(maybeGetUser(true))
                .flatMap(User::getEmailAddress);
        maybeEmail.ifPresent(System.out::println);
    }

    public static User maybeGetUser(boolean returnUser) {
        if (returnUser) {
            return new User()
                    .setId(1001)
                    .setName("Alice")
                    .setEmailAddress("alice@naver.com")
                    .setVerified(false);
        }
        return null;
    }
}
