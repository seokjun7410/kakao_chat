package chatting_list.design.miniProfileDesign;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import chatting_list.design.RoundedButton;


public class FourPeople implements MiniProfileDesign{
	//new ImageIcon("img/sampleProfile.jpg")
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		JButton profileButton_1 = new RoundedButton("",new ImageIcon("img/defaultProfile50.png"));
		profileButton_1.setBounds(15, 0, 40, 40);
		profileButton_1.setBackground(Color.white);
		chattingPanel.add(profileButton_1);
		
		JButton profileButton_1_1_1 = new RoundedButton("",new ImageIcon("img/defaultProfile50.png"));
		profileButton_1_1_1.setBounds(34, 31, 40, 40);
		profileButton_1_1_1.setBackground(Color.white);
		chattingPanel.add(profileButton_1_1_1);
		
		JButton profileButton_1_1 = new RoundedButton("",new ImageIcon("img/defaultProfile50.png"));
		profileButton_1_1.setBounds(0, 31, 40, 40);
		//profileButton_1_1_1.setBackground(Color.white);
		chattingPanel.add(profileButton_1_1);
	}

}
