package model;

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
}
