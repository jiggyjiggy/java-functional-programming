package chapter8AdvancedStream.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class User {
    private int id;
    private String name;
    private String emailAddress;
    private boolean isVerified;
    private List<Integer> friendUserIds;
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this; // chaining을 위한 return
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Optional<String> getEmailAddress() {
        return Optional.ofNullable(emailAddress);
    }

    public User setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public User setVerified(boolean verified) {
        isVerified = verified;
        return this;
    }

    public List<Integer> getFriendUserIds() {
        return friendUserIds;

    }

    public User setFriendUserIds(List<Integer> friendUserIds) {
        this.friendUserIds = friendUserIds;
        return this;
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