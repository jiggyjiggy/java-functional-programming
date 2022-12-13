package chapter4FunctionalInterface;

import chapter4FunctionalInterface.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Section5Comparator {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        users.add(new User(3, "Alice"));
        users.add(new User(1, "Charlie"));
        users.add(new User(5, "Bob"));

        System.out.println(users);

        Comparator<User> idComparator = (user1, user2) -> user1.getId() - user2.getId();
        Collections.sort(users, idComparator);

        System.out.println(users);

        Collections.sort(users, (user1, user2) -> user1.getName().compareTo(user2.getName()));
        System.out.println(users);

    }
}
