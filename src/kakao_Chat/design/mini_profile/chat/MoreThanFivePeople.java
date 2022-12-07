package kakao_Chat.design.mini_profile.chat;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.pictureEdit.PictureRound;

import java.awt.*;
import java.io.IOException;

import static kakao_Chat.design.pictureEdit.PictureRound.setImageRound;


public class MoreThanFivePeople extends MiniProfileDesign_Chat{
	private String[] imgNames;
	public MoreThanFivePeople(String[] imgNames){
		this.imgNames = imgNames;
	}

	//"img/defaultProfile40.png")
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) throws IOException {
		ImageIcon i0 = setImageRound(imgNames[0],30);
		ImageIcon i1 = setImageRound(imgNames[1],30);
		ImageIcon i2 = setImageRound(imgNames[2],30);
		ImageIcon i3 = setImageRound(imgNames[3],30);

		JButton profileButton1 = new RoundedButton("",i0);
		profileButton1.setBounds(10, 6, 30, 30);
		profileButton1.setToolTipText("0");
		chattingPanel.add(profileButton1);

		JButton profileButton = new RoundedButton("",i1);
		profileButton.setBounds(39, 6, 30, 30);
		profileButton.setToolTipText("1");
		chattingPanel.add(profileButton);

		JButton profileButton2 = new RoundedButton("",i2);
		profileButton2.setBounds(10, 34, 30, 30);
		profileButton2.setToolTipText("2");
		chattingPanel.add(profileButton2);

		JButton profileButton3 = new RoundedButton("",i3);
		profileButton3.setBounds(39, 34, 30, 30);
		profileButton3.setToolTipText("3");
		chattingPanel.add(profileButton3);

	}

}
