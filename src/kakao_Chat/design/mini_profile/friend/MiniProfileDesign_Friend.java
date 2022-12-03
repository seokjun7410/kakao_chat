package kakao_Chat.design.mini_profile.friend;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kakao_Chat.design.mini_profile.MiniProfileDesign;

public abstract class MiniProfileDesign_Friend extends MiniProfileDesign{
	
	protected String ProfileDesign(int size, ArrayList<String> member,JPanel chattingPanel,int chattingListHeight, int chattingListIndex, String lastMsg) {
//		String chattingName = "User"+(chattingListIndex+2);
		String chattingName = member.get(0);
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
