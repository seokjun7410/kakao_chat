package kakao_Chat.design.mini_profile.chat;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;


public class TwoPeople extends MiniProfileDesign_Chat{
	
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		JButton profileButton = new RoundedButton("",new ImageIcon("img/defaultProfile70.png"));
		profileButton.setBounds(10, 8, 55, 56);
		chattingPanel.add(profileButton);
	}

}
