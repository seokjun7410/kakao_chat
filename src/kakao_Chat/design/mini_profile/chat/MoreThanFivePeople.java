package kakao_Chat.design.mini_profile.chat;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kakao_Chat.design.RoundedButton;

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

		JButton profileButton_1 = new RoundedButton("",i0);
		profileButton_1.setBounds(10, 6, 30, 30);
		chattingPanel.add(profileButton_1);
		
		JButton profileButton_1_1 = new RoundedButton("",i1);
		profileButton_1_1.setBounds(39, 6, 30, 30);
		chattingPanel.add(profileButton_1_1);
		
		JButton profileButton_1_2 = new RoundedButton("",i2);
		profileButton_1_2.setBounds(10, 34, 30, 30);
		chattingPanel.add(profileButton_1_2);
		
		JButton profileButton_1_3 = new RoundedButton("",i3);
		profileButton_1_3.setBounds(39, 34, 30, 30);
		chattingPanel.add(profileButton_1_3);
	}

}
