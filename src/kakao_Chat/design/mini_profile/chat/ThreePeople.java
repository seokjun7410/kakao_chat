package kakao_Chat.design.mini_profile.chat;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;

import java.awt.*;


public class ThreePeople extends MiniProfileDesign_Chat{

	private String[] imgNames;
	public ThreePeople(String[] imgNames){
		this.imgNames = imgNames;
	}
	//"img/defaultProfile50.png"
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		Image i0 = new ImageIcon(imgNames[0]).getImage().getScaledInstance(41, 38, Image.SCALE_DEFAULT);
		Image i1 = new ImageIcon(imgNames[1]).getImage().getScaledInstance(41, 38, Image.SCALE_DEFAULT);

		JButton profileButton2 = new RoundedButton("",new ImageIcon(i0));
		profileButton2.setBounds(25, 30, 41, 38);
		chattingPanel.add(profileButton2);
		
		JButton profileButton1 = new RoundedButton("",new ImageIcon(i1));
		profileButton1.setBounds(10, 10, 41, 38);
		chattingPanel.add(profileButton1);
	}

}
