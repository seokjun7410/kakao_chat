package chatting_list.design.miniProfileDesign;

import javax.swing.JButton;
import javax.swing.JPanel;


public class TwoPeople implements MiniProfileDesign{
	
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		JButton profileButton = new JButton("");
		profileButton.setBounds(0, 0, 74, 71);
		chattingPanel.add(profileButton);
	}

}
