package model;

public class SentMessage
{
    int sender;
    int receiver;
    String message;
    String date;
    int idmessage;

    public SentMessage(int receiver, String message, String date) {
        this.receiver = receiver;
        this.message = message;
        this.date = date;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdmessage() {
        return idmessage;
    }

    public void setIdmessage(int idmessage) {
        this.idmessage = idmessage;
    }

    @Override
    public String toString() {
        return "SentMessage{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                ", idmessage=" + idmessage +
                '}';
    }
}
