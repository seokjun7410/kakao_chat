package kakao_Chat.design.mini_profile.chat;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;

import java.awt.*;


public class TwoPeople extends MiniProfileDesign_Chat{
	private String[] imgNames;
	public TwoPeople(String[] imgNames){
		this.imgNames = imgNames;
	}
	
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		Image i0 = new ImageIcon(imgNames[0]).getImage().getScaledInstance(56, 56, Image.SCALE_DEFAULT);

		JButton profileButton = new RoundedButton("",new ImageIcon(i0));
		profileButton.setBounds(10, 8, 55, 56);
		chattingPanel.add(profileButton);
	}

}
