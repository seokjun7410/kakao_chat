package kakao_Chat.design.mini_profile.chat;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.pictureEdit.PictureRound;

import java.awt.*;
import java.io.IOException;

import static kakao_Chat.design.pictureEdit.PictureRound.setImageRound;


public class ThreePeople extends MiniProfileDesign_Chat{

	private String[] imgNames;
	public ThreePeople(String[] imgNames){
		this.imgNames = imgNames;
	}
	//"img/defaultProfile50.png"
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) throws IOException {
		ImageIcon i0 = setImageRound(imgNames[0],41);
		ImageIcon i1 = setImageRound(imgNames[1],41);

		JButton profileButton1 = new RoundedButton("",i1);
		profileButton1.setToolTipText("1");
		profileButton1.setBounds(25, 30, 41, 38);
		chattingPanel.add(profileButton1);

		JButton profileButton = new RoundedButton("",i0);
		profileButton.setBounds(10, 10, 41, 38);
		profileButton.setToolTipText("0");
		chattingPanel.add(profileButton);
	}

}
