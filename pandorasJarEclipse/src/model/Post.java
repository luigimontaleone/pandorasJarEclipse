package model;

import java.util.ArrayList;

public class Post {
    private int id;
    private int authorId;
    private String author;
    private int numLike;
    private int numDislike;
    private String title;
    private String image;
    private String description;
    private ArrayList<Comment> comments;

    public Post() {
        comments = new ArrayList<Comment>();
    }

    public Post(int id, int authorId, String author, String description, int numLike, int numDislike, String title, String image, ArrayList<Comment> comments) {
        this.id = id;
        this.authorId = authorId;
        this.author = author;
        this.description = description;
        this.numLike = numLike;
        this.numDislike = numDislike;
        this.title = title;
        this.image = image;
        this.comments = comments;
    }

    public Post(int authorId, int numLike, int numDislike, String title, String image, String description) {
        this.authorId = authorId;
        this.numLike = numLike;
        this.numDislike = numDislike;
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getNumLike() {
        return numLike;
    }

    public void setNumLike(int numLike) {
        this.numLike = numLike;
    }

    public int getNumDislike() {
        return numDislike;
    }

    public void setNumDislike(int numDislike) {
        this.numDislike = numDislike;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment)
    {
        comments.add(comment);
    }

    public void removeComment(Comment comment)
    {
        comments.remove(comment);
    }
}
