package kakao_Chat.design.mini_profile.chat;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;

import java.awt.*;
import java.io.IOException;

import static kakao_Chat.design.pictureEdit.PictureRound.setImageRound;


public class TwoPeople extends MiniProfileDesign_Chat{
	private String[] imgNames;
	public TwoPeople(String[] imgNames){
		this.imgNames = imgNames;
	}
	
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) throws IOException {
		ImageIcon i0 = setImageRound(imgNames[0],56);
		JButton profileButton = new RoundedButton("",i0);
		profileButton.setBounds(10, 8, 55, 56);
		chattingPanel.add(profileButton);
	}

}
