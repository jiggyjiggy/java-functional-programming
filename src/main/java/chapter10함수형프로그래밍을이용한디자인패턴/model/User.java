package chapter10함수형프로그래밍을이용한디자인패턴.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class User {

    /*
    * production 상황에서는 id를 setter를 두면 위험하다
    * */
    /*
     * setter를 없애 immutable하게 만든다
     * 그렇다면 처음 생성때 constructor가 비대해진다
     * 이 때 builder pattern 을 사용하면 간단해진다
     * 또한 builder pattern 을 사용하면 default 값 지정도 가능해진다.
     * */
    private int id;
    private String name;
    private String emailAddress;
    private boolean isVerified;
    private List<Integer> friendUserIds;
    private LocalDateTime createdAt;

    public User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.emailAddress = builder.emailAddress;
        this.isVerified = builder.isVerified;
        this.createdAt = builder.createdAt;
        this.friendUserIds = builder.friendUserIds;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getEmailAddress() {
        return Optional.ofNullable(emailAddress);
    }

    public boolean isVerified() {
        return isVerified;
    }

    public List<Integer> getFriendUserIds() {
        return friendUserIds;
    }

    public static Builder builder(int id, String name) {
        return new Builder(id, name);
    }

    public static class Builder {
        private int id;
        private String name;
        public String emailAddress;
        public boolean isVerified;
        public List<Integer> friendUserIds = new ArrayList<>();
        public LocalDateTime createdAt;

        private Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }

//        public Builder withEmailAddress(String emailAddress) {
//            this.emailAddress = emailAddress;
//            return this;
//        }
//
//        public Builder withVerified(boolean isVerified) {
//            this.isVerified = isVerified;
//            return this;
//        }
//
//        public Builder withCreatedAt(LocalDateTime createdAt) {
//            this.createdAt = createdAt;
//            return this;
//        }
//
//        public Builder withFriendUserIds(List<Integer> friendUserIds) {
//            this.friendUserIds = friendUserIds;
//            return this;
//        }
        public Builder with(Consumer<Builder> consumer) {
            consumer.accept(this);
            return this;
        }

        public User build() {
            return new User(this);
        }
    }


    //    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", emailAddress='" + emailAddress + '\'' +
//                ", isVerified=" + isVerified +
//                ", friendUserIds=" + friendUserIds +
//                '}';
//    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", " + (name != null ? "name='" + name : "") + '\'' +
                ", " + (emailAddress != null ? "emailAddress='" + emailAddress : "") + '\'' +
                ", isVerified=" + isVerified +
                ", " + (friendUserIds != null ? "friendUserIds=" + friendUserIds : "") +
                '}';
    }
}
