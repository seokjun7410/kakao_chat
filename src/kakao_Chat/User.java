package kakao_Chat;

import java.awt.Image;

import javax.swing.ImageIcon;

public class User {
	String id;
	String pw;
	int userNum;
	int chattingCount;

	public void setChattingCount(int chattingCount) {
		this.chattingCount = chattingCount;
	}

	public int getChattingCount() {
		return chattingCount;
	}

	private ImageIcon image;
	
	public User(String id, String pw, int userNum) {
		this.id = id;
		this.pw = pw;
		this.userNum = userNum;
		this.image = new ImageIcon("img/UserProfile/"+id+".png");
		this.chattingCount = 0;
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
	
	public ImageIcon getImage() {
		return this.image;
	}
}
