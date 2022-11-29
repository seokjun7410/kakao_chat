package kakao_Chat.design.mini_profile.friend;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kakao_Chat.design.mini_profile.MiniProfileDesign;

public class MiniProfileDesign_Friend extends MiniProfileDesign{
	
	protected String ProfileDesign(JPanel chattingPanel,int chattingListHeight, int chattingListIndex,String id) {
//		String chattingName = "User"+(chattingListIndex+2);
		String chattingName = id;
		JLabel userNameLabel = new JLabel(chattingName);
		userNameLabel.setBounds(86, 14, 93, 15);
		chattingPanel.add(userNameLabel);
		
//		JLabel lastMassageLabel = new JLabel("state massage");
//		lastMassageLabel.setBounds(86, 31, 199, 15);
//		chattingPanel.add(lastMassageLabel);
		
		miniProfileMakeByNumber(chattingPanel);
		
		return chattingName;
	}
	
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		// TODO Auto-generated method stub
		
	}

}
