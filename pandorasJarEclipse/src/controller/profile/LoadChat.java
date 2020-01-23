package controller.profile;

import com.google.gson.Gson;
import model.ChatBox;
import persistence.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(value="/loadChat")
public class LoadChat extends HttpServlet
{
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(401);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int userId = (int) req.getSession().getAttribute("userId");
        int other = Integer.parseInt(req.getParameter("id"));
        ArrayList<ChatBox> chatBoxesMittente = DAOFactory.getInstance().makeUserDAO().getChat(userId, other, true);
        ArrayList<ChatBox> chatBoxesDestinatario = DAOFactory.getInstance().makeUserDAO().getChat(other, userId, false);
        ArrayList<ChatBox> chatBoxes = new ArrayList<ChatBox>();
        for(ChatBox c : chatBoxesMittente)
        {
            chatBoxes.add(c);
        }
        for(ChatBox c : chatBoxesDestinatario)
        {
            chatBoxes.add(c);
        }
        Collections.sort(chatBoxes);
        String json = gson.toJson(chatBoxes);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(json);
        resp.getWriter().flush();
    }
}
