package controller.community;

import com.google.gson.Gson;
import model.Comment;
import model.Post;
import persistence.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/AddNewComment")
public class AddNewComment extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer postId = Integer.parseInt(req.getParameter("id"));
        String json = req.getParameter("data");
        Comment comment = gson.fromJson(json, Comment.class);
        ArrayList<Post> posts = (ArrayList<Post>) req.getSession().getAttribute("posts");
        ArrayList<Comment> comments = null;
        for(Post p: posts)
        {
            if(p.getId() == postId)
            {
                comments = p.getComments();
                break;
            }
        }
        DAOFactory.getInstance().makePostDAO().addComment(comment, postId);
        comments.add(0,comment);
        json = gson.toJson(comments);
        resp.getWriter().println(json);
        resp.getWriter().flush();
    }
}
