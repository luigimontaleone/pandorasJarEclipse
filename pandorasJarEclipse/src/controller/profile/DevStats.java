package controller.profile;

import model.SoldGames;
import persistence.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeMap;

@WebServlet(value = "/devStats")
public class DevStats extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if(req.getSession().getAttribute("userId") != null) {
            int idUser = (int) req.getSession().getAttribute("userId");
            SoldGames tempSG = DAOFactory.getInstance().makePurchaseDAO().getSoldGamesFromIdUser(idUser);
            TreeMap<Integer, Integer> soldGPerYear = tempSG.getSoldGPerYear();
            TreeMap<Integer, Double> earnedMoneyPerYear = tempSG.getEarnedMoneyPerYear();
            double averageSoldGames = 0;
            double averageMoneyEarned = 0;
            for (Integer year : soldGPerYear.keySet()) {
                averageSoldGames += soldGPerYear.get(year);
                averageMoneyEarned += earnedMoneyPerYear.get(year);
            }
            averageSoldGames /= soldGPerYear.keySet().size();
            averageMoneyEarned /= earnedMoneyPerYear.keySet().size();
            req.getSession().setAttribute("soldGameKeys", soldGPerYear.keySet());
            req.getSession().setAttribute("soldGameValues", soldGPerYear.values());

            req.getSession().setAttribute("moneyEarnedKeys", earnedMoneyPerYear.keySet());
            req.getSession().setAttribute("moneyEarnedValues", earnedMoneyPerYear.values());

            req.getSession().setAttribute("averageSoldGames", averageSoldGames);
            req.getSession().setAttribute("averageMoneyEarned", averageMoneyEarned);

            RequestDispatcher rd = req.getRequestDispatcher("newDevStats.jsp");
            rd.forward(req, resp);
        }
        else{
            RequestDispatcher rd = req.getRequestDispatcher("notLogged.jsp");
            rd.forward(req, resp);
        }
    }
}
