package kakao_Chat;

import java.awt.Image;

import javax.swing.ImageIcon;

public class User {
	String id;
	String pw;
	int userNum;
	private ImageIcon image;
	
	public User(String id, String pw, int userNum) {
		this.id = id;
		this.pw = pw;
		this.userNum = userNum;
		this.image = new ImageIcon("img/user_s.png");
	}
	public User(String id, String pw, int userNum, String image) {
		this.id =id;
		this.pw = pw;
		this.userNum = userNum;
		this.image = new ImageIcon(image);
	}
	public String getId () {
		return this.id;
	}
	
	public String getPw () {
		return this.pw;
	}
}
