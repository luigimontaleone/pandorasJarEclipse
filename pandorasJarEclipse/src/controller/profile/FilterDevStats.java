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

@WebServlet(value="/filterDevStats")
public class FilterDevStats extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String anno = req.getParameter("anno");
        if(anno.equals("Anno"))
        {
            resp.sendRedirect("/devStats");
        }
        else
        {
            SoldGames games = DAOFactory.getInstance().makePurchaseDAO().getSoldGamesFromIdUserByYear(anno, (int) req.getSession().getAttribute("userId"));
            TreeMap<Integer, Integer> soldGPerMonth = games.getSoldGPerMonth();
            TreeMap<Integer, Double> earnedMoneyPerMonth = games.getEarnedMoneyPerMonth();
            double averageSoldGames = 0;
            double averageMoneyEarned = 0;
            for(int year : soldGPerMonth.keySet())
            {
                averageSoldGames += soldGPerMonth.get(year);
                averageMoneyEarned += earnedMoneyPerMonth.get(year);
            }
            averageSoldGames /= soldGPerMonth.keySet().size();
            averageMoneyEarned /= earnedMoneyPerMonth.keySet().size();

            req.getSession().setAttribute("soldGameKeys", soldGPerMonth.keySet());
            req.getSession().setAttribute("soldGameValues", soldGPerMonth.values());

            req.getSession().setAttribute("moneyEarnedKeys", earnedMoneyPerMonth.keySet());
            req.getSession().setAttribute("moneyEarnedValues", earnedMoneyPerMonth.values());

            req.getSession().setAttribute("averageSoldGames", averageSoldGames);
            req.getSession().setAttribute("averageMoneyEarned", averageMoneyEarned);

            RequestDispatcher rd = req.getRequestDispatcher("newDevStats.jsp");
            rd.forward(req, resp);
        }
    }
}
