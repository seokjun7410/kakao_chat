package kakao_Chat.design.mini_profile.chat;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;


public class ThreePeople extends MiniProfileDesign_Chat{
	
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		JButton profileButton2 = new RoundedButton("",new ImageIcon("img/defaultProfile50.png"));
		profileButton2.setBounds(25, 30, 41, 38);
		chattingPanel.add(profileButton2);
		
		JButton profileButton1 = new RoundedButton("",new ImageIcon("img/defaultProfile50.png"));
		profileButton1.setBounds(10, 10, 41, 38);
		chattingPanel.add(profileButton1);
	}

}
