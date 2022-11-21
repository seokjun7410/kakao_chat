package kakao_Chat.design.mini_profile.friend;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;


public class FriendMiniProfile extends MiniProfileDesign_Friend{
	
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		JButton profileButton = new RoundedButton("",new ImageIcon("img/Profile45.png"));
		profileButton.setBounds(18, 3, 46, 46);
		chattingPanel.add(profileButton);
	}

}
