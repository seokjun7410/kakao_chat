package chatting_list.design.miniProfileDesign;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public interface MiniProfileDesign {
	
	
	default void ProfileDesign(JPanel chattingPanel,int chattingListHeight, int chattingListIndex) {
		JLabel userNameLabel = new JLabel("User"+(chattingListIndex+2));
		userNameLabel.setBounds(86, 16, 93, 15);
		chattingPanel.add(userNameLabel);
		
		JLabel lastMassageLabel = new JLabel("last message left");
		lastMassageLabel.setBounds(86, 42, 199, 15);
		chattingPanel.add(lastMassageLabel);
		
		miniProfileMakeByNumber(chattingPanel);
	}
	
	abstract void miniProfileMakeByNumber(JPanel chattingPanel);
}
