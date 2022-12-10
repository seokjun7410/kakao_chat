package kakao_Chat.design.mini_profile.chatroom.profile;


import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.mini_profile.chatroom.MiniProfileDesign_chatroom;
import kakao_Chat.design.pictureEdit.PictureRound;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class ThreePeople extends MiniProfileDesign_chatroom {

	private String[] imgNames;
	public ThreePeople(String[] imgNames){
		this.imgNames = imgNames;
	}

	private JLabel profileButton1;
	private JLabel profileButton;
	//"img/defaultProfile50.png"

	public JLabel getProfileButton1() {
		return profileButton1;
	}

	@Override
	public JLabel getProfileButton2() {
		return null;
	}

	@Override
	public JLabel getProfileButton3() {
		return null;
	}

	public JLabel getProfileButton() {
		return profileButton;
	}

	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
//		Image i0 = new ImageIcon(imgNames[0]).getImage().getScaledInstance(41, 38, Image.SCALE_DEFAULT);
//		Image i1 = new ImageIcon(imgNames[1]).getImage().getScaledInstance(41, 38, Image.SCALE_DEFAULT);
//
//
//
//		JButton profileButton2 = new RoundedButton("",new ImageIcon(i0));
//		profileButton2.setBounds(25, 30, 41, 38);
//		chattingPanel.add(profileButton2);
//
//		JButton profileButton1 = new RoundedButton("",new ImageIcon(i1));
//		profileButton1.setBounds(10, 10, 41, 38);
//		chattingPanel.add(profileButton1);
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(186,206,224));
		p.setPreferredSize(new Dimension(50,50));

		ImageIcon i0 = PictureRound.setImageRound(imgNames[0],30);
		ImageIcon i1 = PictureRound.setImageRound(imgNames[1],30);

			profileButton1 = new JLabel(i1);
			profileButton1.setBounds(15, 15, 30, 30);
			profileButton1.setToolTipText("1");
			//chattingPanel.add(profileButton1, BorderLayout.WEST);

			profileButton = new JLabel(i0);
			profileButton.setBounds(5, 0, 30, 30);
			profileButton.setToolTipText("0");

			//chattingPanel.add(profileButton, BorderLayout.WEST);


		p.add(profileButton1);;
		p.add(profileButton);

		chattingPanel.add(p, BorderLayout.WEST);
	}


}
