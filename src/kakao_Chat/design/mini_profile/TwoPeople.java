package kakao_Chat.design.mini_profile;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;


public class TwoPeople implements MiniProfileDesign{
	
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		JButton profileButton = new RoundedButton("",new ImageIcon("img/defaultProfile90.png"));
		profileButton.setBounds(0, 0, 74, 71);
		chattingPanel.add(profileButton);
	}

}
