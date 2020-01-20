package controller.gamesheet;

import com.google.gson.Gson;
import model.Review;
import persistence.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value="/loadMoreRevs")
public class LoadMoreRevs extends HttpServlet
{
    Gson gson = new Gson();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ArrayList<Review> moreReviews = DAOFactory.getInstance().makeReviewDAO().getReviewsFromIdGame
                (Integer.valueOf(req.getParameter("gameId")), true);
        String json = gson.toJson(moreReviews);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(json);
        resp.getWriter().flush();
    }
}
