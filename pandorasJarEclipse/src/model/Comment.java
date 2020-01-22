package model;

public class Comment {
    private int id;
    private int authorId;
    private String author;
    private String comment;

    public Comment(){}

    public Comment(int id, int authorId, String author, String comment) {
        this.id = id;
        this.authorId = authorId;
        this.author = author;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
