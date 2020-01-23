package controller.library;

import model.Game;
import model.Review;
import model.Score;
import model.User;
import persistence.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/library", name = "library")
public class Library extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        if(req.getSession().getAttribute("logged") == null || !(Boolean) req.getSession().getAttribute("logged")){
            rd = req.getRequestDispatcher("notLogged.jsp");
            rd.forward(req, resp);
            return;
        }
        else{
            Integer gameId = null;
            try{
                gameId = Integer.parseInt(req.getParameter("id"));
            }catch (NumberFormatException e)
            {}
            Integer userId = (Integer) req.getSession().getAttribute("userId");
            ArrayList<Game> library = DAOFactory.getInstance().makeUserDAO().getLibrary(userId);
            if(!library.isEmpty()) {
                DAOFactory factory = DAOFactory.getInstance();
                Game game = library.get(0);
                if(gameId != null) {
                    game = factory.makeGameDAO().getGameFromIdWithPreviews(gameId);
                    game.setReviews(DAOFactory.getInstance().makeReviewDAO().getReviewsFromIdGame(gameId, false));
                }
                req.getSession().setAttribute("gameLibrary", game);
                ArrayList<Integer> totalSize = new ArrayList<Integer>();
                for(int i = 0; i < game.getPreviewsVID().size()+game.getPreviewsIMG().size();i++)
                    totalSize.add(i);
                req.setAttribute("totalSize", totalSize);
                String usernameDeveloper = factory.makeUserDAO().getUserByIdUser(game.getIdDeveloper()).getUsername();
                req.setAttribute("developer", usernameDeveloper);
                ArrayList<Review> reviews = factory.makeReviewDAO().getReviewsFromIdGame(game.getId(), false);
                req.setAttribute("reviews", reviews);
                ArrayList<Score> scores = factory.makeScoreDAO().getScoresFromIdGame(game.getId());
                req.setAttribute("ranking", scores);
            }
            else{
                rd = req.getRequestDispatcher("zeroGames.jsp");
                rd.forward(req, resp);
                return;
            }
            req.getSession().setAttribute("library", library);
            resp.setCharacterEncoding("UTF-8");
            rd = req.getRequestDispatcher("library.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(401);
    }

}
