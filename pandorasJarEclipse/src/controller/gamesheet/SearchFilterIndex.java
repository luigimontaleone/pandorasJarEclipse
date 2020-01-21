package controller.gamesheet;

import com.google.gson.Gson;
import model.Filter;
import model.Game;
import persistence.DAOFactory;
import utility.Acquisto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value="/SearchFilterIndex")
public class SearchFilterIndex extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Game> games = DAOFactory.getInstance().makeGameDAO().getGamesFromNameLike("");
        String categoria = req.getParameter("categoria");
        Integer prezzo = null;
        try{
            prezzo = Integer.parseInt(req.getParameter("prezzo"));
        }catch(NumberFormatException e)
        {
        }
        String valutazione = req.getParameter("valutazione");
        ArrayList<Game> newGames = new ArrayList<Game>();
        for(Game g: games)
        {
            String categoriaGioco = g.getCategory();
            double prezzoGioco = g.getPrice();
            String valutazioneGioco = DAOFactory.getInstance().makeReviewDAO().getValutazioneMediaGioco(g.getId());
            if(categoria.equals("") || categoriaGioco.equals(categoria))
            {
                if(valutazione.equals("") || valutazioneGioco.equals(valutazione))
                {
                    if(prezzo == null || (prezzoGioco >= prezzo && prezzoGioco <= prezzo+9) || (prezzo == 50 && prezzoGioco >= prezzo))
                    {
                        newGames.add(g);
                    }
                }
            }
        }
        req.setAttribute("games", newGames);
        RequestDispatcher rd = req.getRequestDispatcher("searchGame.jsp");
        rd.forward(req, resp);
    }
}
