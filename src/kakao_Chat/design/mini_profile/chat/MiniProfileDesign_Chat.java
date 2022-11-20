package kakao_Chat.design.mini_profile.chat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kakao_Chat.design.mini_profile.MiniProfileDesign;

public class MiniProfileDesign_Chat extends MiniProfileDesign{
	
	protected String ProfileDesign(JPanel chattingPanel,int chattingListHeight, int chattingListIndex) {
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
	
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		// TODO Auto-generated method stub
		
	}

}
