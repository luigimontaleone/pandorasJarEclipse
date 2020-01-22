package persistence;

import model.ChatBox;
import model.Game;
import model.User;
import model.UserBox;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class FriendRequestsDAO {
    private PreparedStatement statement;

    public ArrayList<User> getReceivedSentRequests(int id, boolean received) {
        Connection connection = DataSource.getInstance().getConnection();
        String query;
        if(received)
            query = "SELECT * FROM public.requestfriend WHERE receiver = ?;";
        else
            query = "SELECT * FROM public.requestfriend WHERE sender = ?;";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            ArrayList<User> requests = new ArrayList<User>();
            UserDAO userDao = DAOFactory.getInstance().makeUserDAO();
            while(result.next())
            {
                if(received)
                    requests.add(userDao.getUserByIdUserWithoutFriends(result.getInt("sender")));
                else
                    requests.add(userDao.getUserByIdUserWithoutFriends(result.getInt("receiver")));
            }
            return requests;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

    public void deleteRequestFriend(int userId, int id) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "DELETE FROM requestfriend WHERE sender = ? and receiver = ?;";
        try{
            statement = connection.prepareStatement(query);
            statement.setInt(1,userId);
            statement.setInt(2,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }

    public void addRequestUserFriend(int idFriend, int idMain) {
        Connection connection = DataSource.getInstance().getConnection();
        int nextId = getRequestFriendNextId(connection);
        String query = "INSERT INTO requestfriend(id,sender,receiver) values(?,?,?);";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,nextId);
            statement.setInt(2,idMain);
            statement.setInt(3,idFriend);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }
    private int getRequestFriendNextId(Connection conn)
    {
        String query = "SELECT nextval('request_friend_sequence') AS id";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            ResultSet set = stmt.executeQuery();
            set.next();
            return set.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteUserFriend(int userId, int id) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "DELETE FROM user_friend WHERE iduser1 = ? and iduser2 = ?;";
        try{
            statement = connection.prepareStatement(query);
            statement.setInt(1,userId);
            statement.setInt(2,id);
            statement.executeUpdate();

            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.setInt(2,userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }

    public void addUserFriend(int friend, int main) {
        deleteRequestFriend(friend, main);
        Connection connection = DataSource.getInstance().getConnection();
        int nextId = getFriendNextId(connection);
        String query = "INSERT INTO user_friend(iduser1,iduser2,id) values(?::integer,?::integer,?::integer)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(main));
            statement.setString(2,Integer.toString(friend));
            statement.setString(3,Integer.toString(nextId));
            statement.executeUpdate();

            nextId = getFriendNextId(connection);
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(friend));
            statement.setString(2,Integer.toString(main));
            statement.setString(3,Integer.toString(nextId));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }

    private int getFriendNextId(Connection conn)
    {
        String query = "SELECT nextval('user_friend_sequence') AS id";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            ResultSet set = stmt.executeQuery();
            set.next();
            return set.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
