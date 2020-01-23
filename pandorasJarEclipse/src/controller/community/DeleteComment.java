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

@WebServlet(value = "/DeleteComment")
public class DeleteComment extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        DAOFactory.getInstance().makePostDAO().deleteComment(id);
        ArrayList<Comment> comments = (ArrayList<Comment>) req.getSession().getAttribute("comments");
        for(Comment c: comments)
        {
            if(c.getId() == id)
            {
                comments.remove(c);
                req.getSession().setAttribute("comments", comments);
                String json = gson.toJson(comments);
                resp.getWriter().println(json);
                resp.getWriter().flush();
                break;
            }
        }

    }
}
