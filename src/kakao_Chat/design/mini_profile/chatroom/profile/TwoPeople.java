package kakao_Chat.design.mini_profile.chatroom.profile;


import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.mini_profile.chatroom.MiniProfileDesign_chatroom;
import kakao_Chat.design.pictureEdit.PictureRound;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class TwoPeople extends MiniProfileDesign_chatroom {
	private String[] imgNames;
	private JLabel profileLabel;

	public TwoPeople(String[] imgNames){
		this.imgNames = imgNames;
	}

	public JLabel getProfileLabel() {
		return profileLabel;
	}

	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		//Image i0 = new ImageIcon(imgNames[0]).getImage().getScaledInstance(56, 56, Image.SCALE_DEFAULT);
		ImageIcon i0 = PictureRound.setImageRound(imgNames[0],50);
		//JButton profileButton = new RoundedButton("",i0);
			profileLabel = new JLabel(i0);
			profileLabel.setToolTipText("0");
			//profileButton.setBounds(0, 0, 10, 10);
		chattingPanel.add(profileLabel, BorderLayout.WEST);
	}

	@Override
	public JLabel getProfileButton() {
		return profileLabel;
	}

	@Override
	public JLabel getProfileButton1() {
		return null;
	}

	@Override
	public JLabel getProfileButton2() {
		return null;
	}

	@Override
	public JLabel getProfileButton3() {
		return null;
	}


}
