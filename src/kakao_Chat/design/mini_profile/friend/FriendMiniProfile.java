package kakao_Chat.design.mini_profile.friend;


import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;


public class FriendMiniProfile extends MiniProfileDesign_Friend{
	private String profile_img;

	public FriendMiniProfile(String profile_img){
		this.profile_img = profile_img;
	}

	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		JButton profileButton = new RoundedButton("",new ImageIcon(profile_img));
		profileButton.setBounds(18, 3, 46, 46);
		chattingPanel.add(profileButton);
	}

	@Override
	protected String ProfileDesign(int size, ArrayList member, JPanel chattingPanel, int chattingListHeight,
			int chattingListIndex) {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

}
