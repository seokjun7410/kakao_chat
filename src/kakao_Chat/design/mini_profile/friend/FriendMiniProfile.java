package kakao_Chat.design.mini_profile.friend;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.pictureEdit.PictureRound;

import java.io.IOException;


public class FriendMiniProfile extends MiniProfileDesign_Friend{
	private String profile_img;

	public FriendMiniProfile(String profile_img){
		this.profile_img = profile_img;
	}

	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		try {
			ImageIcon pi = PictureRound.setImageRound(profile_img,60);
			JButton profileButton = new RoundedButton("",pi);
			profileButton.setBounds(13, 8, 60, 60);
			chattingPanel.add(profileButton);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
