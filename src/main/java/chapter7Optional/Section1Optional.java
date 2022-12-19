package chapter7Optional;

import chapter7Optional.model.User;

import java.util.Optional;

public class Section1Optional {
    public static void main(String[] args) {
        User user1 = new User()
                .setId(1001)
                .setName("Alice")
                .setVerified(false);
        User user2 = new User()
                .setId(1001)
                .setName("Alice")
                .setEmailAddress("alice@naver.com")
                .setVerified(false);

        System.out.println("Same? :" + userEquals(user2, user1));   // Same? :false
//        System.out.println("Same? :" + userEquals(user1, user2));   // NPE


        // Optional 적용
        String someEmail = "someEmail@naver.com";
        String nullEmail = null;

        Optional<String> maybeEmail = Optional.of(someEmail);
        Optional<String> maybeEmail2 = Optional.empty();
        Optional<String> maybeEmail3 = Optional.ofNullable(someEmail);
        Optional<String> maybeEmail4 = Optional.ofNullable(nullEmail);

        String email = maybeEmail.get();
        System.out.println(email);
//        String email2 = maybeEmail2.get();  // NoSuchElementException
        if (maybeEmail2.isPresent()) {
            System.out.println(maybeEmail2.get());
        }

        String defaultEmail = "defaultEmail@naver.com";
        String email3 = maybeEmail2.orElse(defaultEmail);
        System.out.println(email3); // defaultEmail@naver.com
        String email4 = maybeEmail.orElse(defaultEmail);
        System.out.println(email4); // someEmail@naver.com

        String email5 = maybeEmail2.orElseGet(() -> defaultEmail);
        System.out.println(email5); // defaultEmail@naver.com

        String email6 = maybeEmail2.orElseThrow(() -> new RuntimeException("email not present"));
        System.out.println(email6);
    }

    public static boolean userEquals(User u1, User u2) {
        return u1.getId() == u2.getId()
                && u1.getName().equals(u2.getName())
                && u1.getEmailAddress().equals(u2.getEmailAddress())
                && u1.isVerified() == u2.isVerified();
    }

    /*
     * Optional 만드는 법
     * of - Null이 아닌 오브젝트를 이용해 Optional을 만들때
     * Emptu - 빈 Optional을 만들때
     * ofNullable - Null인지 아닌지 알 지 못하는 오브젝트로 Optional을 만들 때
     * */

    /*
    * Optional 안에 있는 값을 확인하고 꺼내는 법
    * isPresent - 안의 오브젝트가 null인지 아닌지 체크. Null이 아닐 시 true
    * get - Optional 안의 값을 추출. Null이라면 에러
    * orElse - Optional이 null이 아니라면 Optional 안의 값을, null이라면 other로 공급된 값을 리턴
    * orElseGet - Optional이 null이 아니라면 Optional 안의 값을, null이라면 supplier로 공급되는 값을 리턴
    * orElseThrow - Optional이 null이 아니라면 Optional 안의 값을, null이라면 exceptionSupplier로 공급되는 exception을 던짐
    * */
}
