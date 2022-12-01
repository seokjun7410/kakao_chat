package kakao_Chat.design.mini_profile.friend;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;


public class FriendMiniProfile extends MiniProfileDesign_Friend{
	private String profile_img;

	public FriendMiniProfile(String profile_img){
		this.profile_img = profile_img;
	}

	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		JButton profileButton = new RoundedButton("",new ImageIcon(profile_img));
		profileButton.setBounds(18, 3, 46, 46);
		chattingPanel.add(profileButton);
	}

}
