package model;

import java.util.Objects;

public class UserBox
{
    int userId;
    String username;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserBox{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBox userBox = (UserBox) o;
        return userId == userBox.userId &&
                Objects.equals(username, userBox.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username);
    }
}
