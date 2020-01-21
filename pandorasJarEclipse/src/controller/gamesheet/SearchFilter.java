package controller.gamesheet;

import com.google.gson.Gson;
import model.Filter;
import model.Game;
import persistence.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value="/SearchFilter")
public class SearchFilter extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("here");
        ArrayList<Game> games = (ArrayList<Game>) req.getSession().getAttribute("games");
        String json = req.getParameter("data");
        Filter filter = gson.fromJson(json, Filter.class);
        String categoria = filter.getCategoria();
        Integer prezzo = null;
        try{
            prezzo = Integer.parseInt(filter.getPrezzo());
        }catch(NumberFormatException e)
        {
        }
        String valutazione = filter.getValutazione();
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
        String jsonSend = gson.toJson(newGames);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(jsonSend);
        resp.getWriter().flush();
    }
}
