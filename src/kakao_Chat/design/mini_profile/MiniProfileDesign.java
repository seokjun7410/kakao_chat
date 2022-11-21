package kakao_Chat.design.mini_profile;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public interface MiniProfileDesign {
	
	
<<<<<<< Updated upstream
	default void ProfileDesign(String User_name, JPanel chattingPanel,int chattingListHeight, int chattingListIndex) {
		//String chattingName = "User"+(chattingListIndex+2);
		//JLabel userNameLabel = new JLabel(chattingName);
		
		JLabel userNameLabel = new JLabel(User_name);
		userNameLabel.setBounds(86, 16, 93, 15);
		chattingPanel.add(userNameLabel);
		
		JLabel lastMassageLabel = new JLabel("last message left");
		lastMassageLabel.setBounds(86, 42, 199, 15);
		chattingPanel.add(lastMassageLabel);
		
		miniProfileMakeByNumber(chattingPanel);
		
		//return chattingName;
	}
=======
	protected abstract void ProfileDesign(String User_name, JPanel chattingPanel,int chattingListHeight, int chattingListIndex);
>>>>>>> Stashed changes
	
	abstract void miniProfileMakeByNumber(JPanel chattingPanel);
}
