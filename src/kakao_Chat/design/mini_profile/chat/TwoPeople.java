package kakao_Chat.design.mini_profile.chat;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.pictureEdit.PictureRound;

import java.awt.*;
import java.io.IOException;


public class TwoPeople extends MiniProfileDesign_Chat{
	private String[] imgNames;
	public TwoPeople(String[] imgNames){
		this.imgNames = imgNames;
	}
	
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		//Image i0 = new ImageIcon(imgNames[0]).getImage().getScaledInstance(56, 56, Image.SCALE_DEFAULT);
		try{
			ImageIcon i0 = PictureRound.setImageRound(imgNames[0],60);

		JButton profileButton = new RoundedButton("",i0);
		profileButton.setBounds(10, 8, 55, 56);
		profileButton.setToolTipText("0");
		chattingPanel.add(profileButton);
		}catch (IOException ex){

		}
	}

}
