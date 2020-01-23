package persistence;

import controller.profile.Chat;
import model.*;
import org.apache.commons.io.IOUtils;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private PreparedStatement statement;

    public ArrayList<Game> refreshLibrary(User u){
        try {
            return this.getGames(DataSource.getInstance().getConnection(), u);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

    private ArrayList<Game> getGames(Connection connection, User user) throws SQLException{
        ArrayList<Game> games = new ArrayList<>();
        String query = "SELECT g.* FROM public.libreria as l, public.game as g WHERE l.idgame = g.idgame AND l.iduser = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, user.getId());
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            Game g = new Game();
            g.setId(rs.getInt("idgame"));
            g.setName(rs.getString("name"));
            g.setIdDeveloper(rs.getInt("developer"));
            g.setCategory(rs.getString("category"));
            g.setHelpEmail(rs.getString("helpemail"));
            g.setPrice(rs.getDouble("price"));
            g.setPayment(rs.getString("paymentscoord"));
            g.setDescription(rs.getString("description"));
            g.setRelease(rs.getDate("release"));
            g.setReviews(DAOFactory.getInstance().makeReviewDAO().getReviewsFromIdGame(rs.getInt("idgame"), false));
            games.add(g);
        }
        return games;
    }

    private ArrayList<User> getFriends(Connection connection, User user) throws SQLException{
        ArrayList<User> friends = new ArrayList<User>();
        String query = "SELECT u.* FROM public.user as u, public.user_friend as uf WHERE uf.iduser1 = ?::integer and u.iduser = uf.iduser2";
        statement = connection.prepareStatement(query);
        statement.setString(1,Integer.toString(user.getId()));
        ResultSet rs = statement.executeQuery();
        if(rs.isClosed())
            return null;
        while(rs.next())
        {
            User u = new User();
            u.setId(rs.getInt("iduser"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setDescription(rs.getString("description"));
            u.setPassword(rs.getString("password"));
            u.setImage(rs.getBytes("image"));
            friends.add(u);
        }
        return friends;
    }

    private User createUserWithFriends(Connection connection, ResultSet rs) throws SQLException{
        User user = new User();
        while(rs.next()) {
            user.setId(rs.getInt("iduser"));
            if (user.getId() == 0) {
                return null;
            }
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setDescription(rs.getString("description"));
            user.setPassword(rs.getString("password"));
            user.setImage(rs.getBytes("image"));
        }
        user.setFriends(this.getFriends(connection,user));
        user.setLibrary(this.getGames(connection, user));
        return user;
    }

    public void insertUser(User user){
        Connection connection = DataSource.getInstance().getConnection();
        String query = "INSERT INTO public.user (iduser,username,email,password,description) values(default,?,?,?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DataSource.getInstance().closeConnection();
        }
    }

    public void changePassword(User user, String newPassword){
        Connection connection = DataSource.getInstance().getConnection();
        String query = "UPDATE public.user SET password=? WHERE iduser=?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, newPassword);
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DataSource.getInstance().closeConnection();
        }
    }

    public User getUserByEmail(String email){
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.user WHERE email = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.isClosed())
                return null;
            User user = this.createUserWithFriends(connection, resultSet);
            return user;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DataSource.getInstance().closeConnection();
        }
        return new User();
    }

    public User getUserByIdUser(int id)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.user WHERE idUser = ?::integer";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            ResultSet result = statement.executeQuery();
           if(result.isClosed())
                return null;
            return this.createUserWithFriends(connection,result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

    public User getUserByUsernameUser(String username)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.user WHERE username = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,username);
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            return this.createUserWithFriends(connection,result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

    public void changeUserDetails(User u) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "UPDATE public.user SET username = ?, email = ?, description = ?, password = ?  WHERE iduser = ?::integer";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,u.getUsername());
            statement.setString(2,u.getEmail());
            statement.setString(3,u.getDescription());
            statement.setString(4,u.getPassword());
            statement.setString(5,Integer.toString(u.getId()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }


    public void changeProfileImageUser(int idUser, InputStream fileContent) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "UPDATE public.user SET image = ? WHERE iduser = ?::integer";
        try {
            statement = connection.prepareStatement(query);
            statement.setBytes(1, IOUtils.toByteArray(fileContent));
            statement.setString(2,Integer.toString(idUser));
            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }

    public ArrayList<UserBox> getUsersBox(int idUser)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT u.username, f.iduser2 FROM public.user_friend AS f, public.user AS u WHERE " +
                "f.iduser1 = '" + idUser + "' AND f.iduser2 = u.iduser";
        try
        {
            statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            ArrayList<UserBox> usersBox = new ArrayList<UserBox>();
            while(result.next())
            {
                UserBox user = new UserBox();
                user.setUserId(result.getInt(2));
                user.setUsername(result.getString(1));
                usersBox.add(user);
            }
            return usersBox;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            DataSource.getInstance().closeConnection();
        }
    }

    public ArrayList<ChatBox> getChat(int idUser, int other, boolean logged)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT idmessage, TO_CHAR(date, 'Mon dd, yyyy'), message FROM public.messages WHERE" +
                " sender = '" + idUser + "' AND receiver = '" + other + "' ORDER BY idmessage ASC";
        try
        {
            statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            ArrayList<ChatBox> chatBoxes = new ArrayList<ChatBox>();
            while(result.next())
            {
                ChatBox chat = new ChatBox();
                chat.setUserId(idUser);
                chat.setIdMessage(result.getInt(1));
                chat.setLogged(logged);
                chat.setDate(result.getString(2));
                chat.setMessaggio(result.getString(3));
                chatBoxes.add(chat);
            }
            return chatBoxes;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            DataSource.getInstance().closeConnection();
        }
    }

    private int getMessageNextId(Connection conn)
    {
        String query = "SELECT nextval('messages_sequence') AS id";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            ResultSet set = stmt.executeQuery();
            set.next();
            return set.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean insertMessage(int userId, SentMessage message)
    {
        Connection connection = DataSource.getInstance().getConnection();
        message.setSender(userId);
        message.setIdmessage(getMessageNextId(connection));
        if(message.getReceiver() == 0)
            return false;
        String query = "INSERT INTO public.messages values('" + userId + "','" + message.getReceiver() + "','" + message.getMessage() + "','" + message.getDate() + "','" + message.getIdmessage() + "');";
        try
        {
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            DataSource.getInstance().closeConnection();
        }
    }

    public User getUserByIdUserWithoutFriends(int id) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.user WHERE idUser = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            User u = new User();
            while(result.next())
            {
                u.setId(id);
                u.setUsername(result.getString("username"));
                u.setEmail(result.getString("email"));
            }
            return u;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }
}
