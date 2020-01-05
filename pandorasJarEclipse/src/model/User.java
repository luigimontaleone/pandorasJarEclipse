package model;

import java.util.ArrayList;

public class User {
	static private int contId = 0;
	private int id;
	private String username;
	private String password;
	private String description;
	private ArrayList<User> friends;
	private ArrayList<Game> library;
	private String image;
	private String email;

	public User(String username, String password, String description, ArrayList<User> friends, String email, ArrayList<Game> library) {
		id = contId++;
		this.username = username;
		this.password = password;
		this.description = description;
		this.friends = friends;
		this.email = email;
		image = null;
		this.library = library;
	}
	public ArrayList<Game> getLibrary() {
		return library;
	}
	public static int getContId() {
		return contId;
	}
	public static void setContId(int contId) {
		User.contId = contId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<User> getFriends() {
		return friends;
	}
	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setLibrary(ArrayList<Game> library) {
		this.library = library;
	}

	public void addFriend(User u)
	{
		for(User user: friends)
		{
			if(user.getId() == u.getId())
				return;
		}
		this.friends.add(u);
	}
	
	

}
