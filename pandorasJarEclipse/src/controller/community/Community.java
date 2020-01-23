package controller.community;

import model.Post;
import persistence.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@WebServlet(value = "/community")
public class Community extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Post> posts = DAOFactory.getInstance().makePostDAO().getAllPosts();
        req.getSession().setAttribute("posts", posts);
        HashSet<Integer> postAlreadyLiked = new HashSet<Integer>();
        HashSet<Integer> postAlreadyDisliked = new HashSet<Integer>();
        req.getSession().setAttribute("postAlreadyLiked", postAlreadyLiked);
        req.getSession().setAttribute("postAlreadyDisliked", postAlreadyDisliked);
        RequestDispatcher rd = req.getRequestDispatcher("community.jsp");
        rd.forward(req, resp);
    }
}
