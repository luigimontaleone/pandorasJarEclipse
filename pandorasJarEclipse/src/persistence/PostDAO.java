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

    public void addLikeDislike(int idPost, int like) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "UPDATE public.post SET numlike = numlike + 1 WHERE id = ?";
        if(like == 0)
            query = "UPDATE public.post SET numdislike = numdislike + 1 WHERE id = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,idPost);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }

    private int getPostNextId(Connection conn)
    {
        String query = "SELECT nextval('post_sequence') AS id";
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

    public void addPost(Post post) {
        Connection connection = DataSource.getInstance().getConnection();
        int nextId = getPostNextId(connection);
        post.setId(nextId);
        String query = "INSERT INTO post(id,idauthor,title,numlike,numdislike,image,description) values(?,?,?,?,?,?,?);";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,nextId);
            statement.setInt(2,post.getAuthorId());
            String author = DAOFactory.getInstance().makeUserDAO().getUserByIdUserWithoutFriends(post.getAuthorId()).getUsername();
            post.setAuthor(author);
            statement.setString(3, post.getTitle());
            statement.setInt(4, post.getNumLike());
            statement.setInt(5, post.getNumDislike());
            statement.setString(6, post.getImage());
            statement.setString(7, post.getDescription());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }

    private int getCommentNextId(Connection conn)
    {
        String query = "SELECT nextval('comment_sequence') AS id";
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

    public void addComment(Comment comment, int idpost) {
        Connection connection = DataSource.getInstance().getConnection();
        int nextId = getPostNextId(connection);
        comment.setId(nextId);
        String query = "INSERT INTO comment(id,comment,idauthor,idpost) values(?,?,?,?);";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,nextId);
            statement.setString(2,comment.getComment());
            String author = DAOFactory.getInstance().makeUserDAO().getUserByIdUserWithoutFriends(comment.getAuthorId()).getUsername();
            comment.setAuthor(author);
            statement.setInt(3, comment.getAuthorId());
            statement.setInt(4, idpost);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }

    public void deletePost(int id) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "DELETE FROM post WHERE id = ?;";
        try{
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }

    public void deleteComment(int id) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "DELETE FROM comment WHERE id = ?;";
        try{
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }
}
