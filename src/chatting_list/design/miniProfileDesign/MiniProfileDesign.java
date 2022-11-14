package chatting_list.design.miniProfileDesign;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public interface MiniProfileDesign {
	
	
	default String ProfileDesign(JPanel chattingPanel,int chattingListHeight, int chattingListIndex) {
		String chattingName = "User"+(chattingListIndex+2);
		JLabel userNameLabel = new JLabel(chattingName);
		userNameLabel.setBounds(86, 16, 93, 15);
		chattingPanel.add(userNameLabel);
		
		JLabel lastMassageLabel = new JLabel("last message left");
		lastMassageLabel.setBounds(86, 42, 199, 15);
		chattingPanel.add(lastMassageLabel);
		
		miniProfileMakeByNumber(chattingPanel);
		
		return chattingName;
	}
	
	abstract void miniProfileMakeByNumber(JPanel chattingPanel);
}
