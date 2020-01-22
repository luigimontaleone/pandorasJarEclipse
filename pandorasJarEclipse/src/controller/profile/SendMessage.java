package controller.profile;

import com.google.gson.Gson;
import model.ChatBox;
import model.SentMessage;
import persistence.DAOFactory;
import utility.Acquisto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet(value="/sendMessage")
public class SendMessage extends HttpServlet
{
    Gson gson = new Gson();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String json = req.getParameter("data");
        SentMessage message = gson.fromJson(json, SentMessage.class);
        int userId = (int) req.getSession().getAttribute("userId");
        DAOFactory.getInstance().makeUserDAO().insertMessage(userId, message);
        String pattern = "MMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);

        String date = simpleDateFormat.format(new Date());
        resp.getWriter().println(date);
    }
}
