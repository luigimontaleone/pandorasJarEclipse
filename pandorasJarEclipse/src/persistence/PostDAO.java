package persistence;

import model.Comment;
import model.Game;
import model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostDAO {
    private PreparedStatement statement;

    public ArrayList<Post> getAllPosts(){
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.post";
        try {
            statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            ArrayList<Post> posts = new ArrayList<Post>();
            UserDAO userDAO = DAOFactory.getInstance().makeUserDAO();
            while(result.next()) {
                Post post = new Post();
                post.setId(result.getInt("id"));
                int authorId = result.getInt("idauthor");
                String author = userDAO.getUserByIdUserWithoutFriends(authorId).getUsername();
                post.setAuthor(author);
                post.setAuthorId(authorId);
                post.setTitle(result.getString("title"));
                post.setDescription(result.getString("description"));
                post.setImage(result.getString("image"));
                post.setNumLike(result.getInt("numlike"));
                post.setNumDislike(result.getInt("numdislike"));
                post.setComments(getAllCommentsByIdPost(result.getInt("id")));
                posts.add(post);
            }

            return posts;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

    private ArrayList<Comment> getAllCommentsByIdPost(int id) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.comment WHERE idpost = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            ArrayList<Comment> comments = new ArrayList<Comment>();
            UserDAO userDAO = DAOFactory.getInstance().makeUserDAO();
            while(result.next()) {
                Comment comment = new Comment();
                comment.setId(result.getInt("id"));
                int authorId = result.getInt("idauthor");
                String author = userDAO.getUserByIdUserWithoutFriends(authorId).getUsername();
                comment.setAuthor(author);
                comment.setAuthorId(authorId);
                comment.setComment(result.getString("comment"));
                comments.add(comment);
            }

            return comments;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }
}
