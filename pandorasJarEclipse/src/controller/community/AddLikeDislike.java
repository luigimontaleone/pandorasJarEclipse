package controller.community;

import com.google.gson.Gson;
import model.Post;
import persistence.DAOFactory;
import persistence.PostDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@WebServlet(value = "/addLikeDislike")
public class AddLikeDislike extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer like = null;
        Integer idPost = null;
        try{
            like =  Integer.parseInt(req.getParameter("like"));
            idPost = Integer.parseInt(req.getParameter("id"));
        }catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        ArrayList<Post> posts = (ArrayList<Post>) req.getSession().getAttribute("posts");
        HashSet<Integer> postAlreadyLiked = (HashSet<Integer>) req.getSession().getAttribute("postAlreadyLiked");
        HashSet<Integer> postAlreadyDisliked = (HashSet<Integer>) req.getSession().getAttribute("postAlreadyDisliked");
        if(like == 1 && postAlreadyLiked.contains(idPost)){
            send(posts, resp);
            return;
        }
        else
            postAlreadyLiked.add(idPost);

        if(like == 0 && postAlreadyDisliked.contains(idPost)){
            send(posts, resp);
            return;
        }
        else
            postAlreadyDisliked.add(idPost);

        req.getSession().setAttribute("postAlreadyLiked", postAlreadyLiked);
        req.getSession().setAttribute("postAlreadyDisliked", postAlreadyDisliked);

        PostDAO postDao = DAOFactory.getInstance().makePostDAO();
        postDao.addLikeDislike(idPost, like);
        for(Post post : posts)
        {
            if(post.getId() == idPost)
            {
                if(like == 1)
                    post.setNumLike(post.getNumLike()+1);
                else
                    post.setNumDislike(post.getNumDislike()+1);
                break;
            }
        }
        send(posts, resp);
    }

    private void send(ArrayList<Post> posts, HttpServletResponse resp) throws IOException {
        String json = gson.toJson(posts);
        resp.getWriter().println(json);
        resp.getWriter().flush();
    }
}
