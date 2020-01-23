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

@WebServlet(value = "/DeletePost")
public class DeletePost  extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        DAOFactory.getInstance().makePostDAO().deletePost(id);
        ArrayList<Post> posts = (ArrayList<Post>) req.getSession().getAttribute("posts");
        for(Post p: posts)
        {
            if(p.getId() == id)
            {
                posts.remove(p);
                req.getSession().setAttribute("posts", posts);
                String json = gson.toJson(posts);
                resp.getWriter().println(json);
                resp.getWriter().flush();
                break;
            }
        }
    }
}
