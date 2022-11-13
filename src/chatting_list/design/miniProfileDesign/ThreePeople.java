package chatting_list.design.miniProfileDesign;

import javax.swing.JButton;
import javax.swing.JPanel;


public class ThreePeople implements MiniProfileDesign{
	
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		JButton profileButton1 = new JButton("");
		profileButton1.setBounds(0, 0, 41, 38);
		chattingPanel.add(profileButton1);
		
		JButton profileButton2 = new JButton("");
		profileButton2.setBounds(26, 33, 41, 38);
		chattingPanel.add(profileButton2);
	}

}
