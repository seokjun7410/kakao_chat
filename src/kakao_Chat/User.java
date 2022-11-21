package kakao_Chat;

import java.awt.Image;

import javax.swing.ImageIcon;

public class User {
	String id;
	private ImageIcon image;
	
	public User(String id) {
		this.id = id;
		this.image = new ImageIcon("img/user_s.png");
	}
	public User(String id, String image) {
		this.id =id;
		this.image = new ImageIcon(image);
	}
}
