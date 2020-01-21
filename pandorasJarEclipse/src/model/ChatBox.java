package model;

public class ChatBox implements Comparable
{
    int idMessage;
    int userId;
    String messaggio;
    String date;
    boolean logged;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    @Override
    public String toString() {
        return "ChatBox{" +
                "idMessage=" + idMessage +
                ", messaggio='" + messaggio + '\'' +
                ", date='" + date + '\'' +
                ", logged=" + logged +
                '}';
    }

    @Override
    public int compareTo(Object o)
    {
        if(o instanceof ChatBox)
        {
            int idM = ((ChatBox) o).getIdMessage();
            return this.idMessage-idM;
        }
        return 0;
    }
}
