package controller.community;

import com.google.gson.Gson;
import model.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/OpenComments")
public class OpenComments extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idPost = Integer.parseInt(req.getParameter("id"));
        ArrayList<Post> posts = (ArrayList<Post>) req.getSession().getAttribute("posts");
        for(Post p: posts)
            if(p.getId() == idPost)
            {
                String json = gson.toJson(p.getComments());
                resp.getWriter().println(json);
                resp.getWriter().flush();
                break;
            }
    }
}
