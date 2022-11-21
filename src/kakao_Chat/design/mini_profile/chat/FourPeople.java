package kakao_Chat.design.mini_profile.chat;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import kakao_Chat.design.RoundedButton;


public class FourPeople extends MiniProfileDesign_Chat{
	//new ImageIcon("img/sampleProfile.jpg")
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		JButton profileButton_1 = new RoundedButton("",new ImageIcon("img/Profile30.png"));
		profileButton_1.setBounds(22, 5, 34, 33);
		profileButton_1.setBackground(Color.white);
		chattingPanel.add(profileButton_1);
		
		JButton profileButton_1_1_1 = new RoundedButton("",new ImageIcon("img/Profile30.png"));
		profileButton_1_1_1.setBounds(35, 28, 34, 33);
		profileButton_1_1_1.setBackground(Color.white);
		chattingPanel.add(profileButton_1_1_1);
		
		JButton profileButton_1_1 = new RoundedButton("",new ImageIcon("img/Profile30.png"));
		profileButton_1_1.setBounds(9, 28, 34, 33);
		//profileButton_1_1_1.setBackground(Color.white);
		chattingPanel.add(profileButton_1_1);
	}

}
