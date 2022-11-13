package chatting_list.design.miniProfileDesign;

import javax.swing.JButton;
import javax.swing.JPanel;


public class MoreThanFivePeople implements MiniProfileDesign{
	
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		JButton profileButton_1 = new JButton("");
		profileButton_1.setBounds(1, 6, 30, 30);
		chattingPanel.add(profileButton_1);
		
		JButton profileButton_1_1 = new JButton("");
		profileButton_1_1.setBounds(29, 6, 30, 30);
		chattingPanel.add(profileButton_1_1);
		
		JButton profileButton_1_2 = new JButton("");
		profileButton_1_2.setBounds(1, 34, 30, 30);
		chattingPanel.add(profileButton_1_2);
		
		JButton profileButton_1_3 = new JButton("");
		profileButton_1_3.setBounds(29, 34, 30, 30);
		chattingPanel.add(profileButton_1_3);
	}

}
