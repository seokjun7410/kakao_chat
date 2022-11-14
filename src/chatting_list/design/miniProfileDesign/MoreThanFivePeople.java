package chatting_list.design.miniProfileDesign;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import chatting_list.design.RoundedButton;


public class MoreThanFivePeople implements MiniProfileDesign{
	
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		JButton profileButton_1 = new RoundedButton("",new ImageIcon("img/defaultProfile40.png"));
		profileButton_1.setBounds(10, 6, 30, 30);
		chattingPanel.add(profileButton_1);
		
		JButton profileButton_1_1 = new RoundedButton("",new ImageIcon("img/defaultProfile40.png"));
		profileButton_1_1.setBounds(39, 6, 30, 30);
		chattingPanel.add(profileButton_1_1);
		
		JButton profileButton_1_2 = new RoundedButton("",new ImageIcon("img/defaultProfile40.png"));
		profileButton_1_2.setBounds(10, 34, 30, 30);
		chattingPanel.add(profileButton_1_2);
		
		JButton profileButton_1_3 = new RoundedButton("",new ImageIcon("img/defaultProfile40.png"));
		profileButton_1_3.setBounds(39, 34, 30, 30);
		chattingPanel.add(profileButton_1_3);
	}

}
