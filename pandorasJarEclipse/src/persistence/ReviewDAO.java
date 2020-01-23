package persistence;


import model.Game;
import model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewDAO {
    private PreparedStatement statement;
    private final int limit = 3;
    public void addCommentForGame(int id, int stars, String comment, int author, String username){
        Connection connection = DataSource.getInstance().getConnection();
        int idRev = getReviewNextId(connection);
        String query = "INSERT INTO public.review VALUES ('" + idRev + "',?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, stars);
            statement.setString(2, comment);
            statement.setInt(3, author);
            statement.setInt(4, id);
            statement.setString(5, username);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }

    private int getReviewNextId(Connection conn)
    {
        String query = "SELECT nextval('review_idreview_seq') AS id";
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
    public ArrayList<Review> getReviewsFromIdGame(int id, boolean more)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.review WHERE game = ?::integer LIMIT " + limit;
        if(more)
        {
            query = "SELECT * FROM public.review WHERE game = ?::integer AND idreview > " + limit;
        }
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            ArrayList<Review> reviews = new ArrayList<Review>();
            while(result.next()) {
                Review review = new Review();
                review.setIdGame(id);
                review.setUsername(result.getString("username"));
                review.setAuthor(result.getInt("author"));
                review.setComment(result.getString("comment"));
                review.setStars(result.getInt("stars"));
                reviews.add(review);
            }

            return reviews;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

    public String getValutazioneMediaGioco(int id) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT review.stars FROM public.review WHERE game = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            int totaleStars = 0;
            int cont = 0;
            while(result.next()) {
                totaleStars += result.getInt("stars");
                cont++;
            }
            if(cont > 0)
                totaleStars /= cont;

            return Integer.toString(totaleStars);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
