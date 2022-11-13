package chatting_list.design.miniProfileDesign;

import javax.swing.JButton;
import javax.swing.JPanel;


public class FourPeople implements MiniProfileDesign{
	
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		JButton profileButton_1 = new JButton("");
		profileButton_1.setBounds(15, 0, 40, 40);
		chattingPanel.add(profileButton_1);
		
		JButton profileButton_1_1_1 = new JButton("");
		profileButton_1_1_1.setBounds(34, 31, 40, 40);
		chattingPanel.add(profileButton_1_1_1);
		
		JButton profileButton_1_1 = new JButton("");
		profileButton_1_1.setBounds(0, 31, 40, 40);
		chattingPanel.add(profileButton_1_1);
	}

}
