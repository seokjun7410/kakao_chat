package kakao_Chat.design.mini_profile.chat;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;

import java.awt.*;


public class MoreThanFivePeople extends MiniProfileDesign_Chat{
	private String[] imgNames;
	public MoreThanFivePeople(String[] imgNames){
		this.imgNames = imgNames;
	}

	//"img/defaultProfile40.png")
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		Image i0 = new ImageIcon(imgNames[0]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		Image i1 = new ImageIcon(imgNames[1]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		Image i2 = new ImageIcon(imgNames[2]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		Image i3 = new ImageIcon(imgNames[3]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);

		JButton profileButton_1 = new RoundedButton("",new ImageIcon(i0));
		profileButton_1.setBounds(10, 6, 30, 30);
		chattingPanel.add(profileButton_1);
		
		JButton profileButton_1_1 = new RoundedButton("",new ImageIcon(i1));
		profileButton_1_1.setBounds(39, 6, 30, 30);
		chattingPanel.add(profileButton_1_1);
		
		JButton profileButton_1_2 = new RoundedButton("",new ImageIcon(i2));
		profileButton_1_2.setBounds(10, 34, 30, 30);
		chattingPanel.add(profileButton_1_2);
		
		JButton profileButton_1_3 = new RoundedButton("",new ImageIcon(i3));
		profileButton_1_3.setBounds(39, 34, 30, 30);
		chattingPanel.add(profileButton_1_3);
	}

}
