package kakao_Chat.design.mini_profile.friend;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.pictureEdit.PictureRound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static kakao_Chat.FriendsListGUI.ProfileEntered;
import static kakao_Chat.Login_Frame.userName;


public class FriendMiniProfile extends MiniProfileDesign_Friend implements ActionListener {
	private String profile_img;
	private String id;
	public FriendMiniProfile(String profile_img, String id){
		this.profile_img = profile_img;
		this.id = id;
	}

	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		try {
			ImageIcon pi = PictureRound.setImageRound(profile_img,60);
			JButton profileButton = new RoundedButton("",pi);
			profileButton.setBorderPainted(false);
			profileButton.setBounds(13, 8, 60, 60);
			profileButton.addActionListener(this);
			chattingPanel.add(profileButton);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(profile_img)){
			if(!ProfileEntered.contains(id)){
				ProfileEntered.add(id);
			}

		}
	}
}
