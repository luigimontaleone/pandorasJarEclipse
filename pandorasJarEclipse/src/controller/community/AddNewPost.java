package controller.community;

import com.google.gson.Gson;
import model.Post;
import persistence.DAOFactory;
import utility.Acquisto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/AddNewPost")
public class AddNewPost extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getParameter("data");
        Post post = gson.fromJson(json, Post.class);
        ArrayList<Post> posts = (ArrayList<Post>) req.getSession().getAttribute("posts");
        DAOFactory.getInstance().makePostDAO().addPost(post);
        posts.add(0,post);
        System.out.println(post.getAuthor());
        json = gson.toJson(posts);
        resp.getWriter().println(json);
        resp.getWriter().flush();
    }
}
