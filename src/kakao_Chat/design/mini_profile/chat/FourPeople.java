package kakao_Chat.design.mini_profile.chat;

import java.awt.*;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.pictureEdit.PictureRound;

import static kakao_Chat.design.pictureEdit.PictureRound.setImageRound;


public class FourPeople extends MiniProfileDesign_Chat{
	private String[] imgNames;
	public FourPeople(String[] imgNames){
		this.imgNames = imgNames;
	}

	//new ImageIcon("img/sampleProfile.jpg")
	//"img/Profile30.png"
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) throws IOException {
		ImageIcon i0 = setImageRound(imgNames[0],33);
		ImageIcon i1 = setImageRound(imgNames[1],33);
		ImageIcon i2 = setImageRound(imgNames[2],33);

		JButton profileButton_1 = new RoundedButton("",i0);
		profileButton_1.setToolTipText("1");
		profileButton_1.setBounds(22, 5, 34, 33);
		profileButton_1.setBackground(Color.white);
		chattingPanel.add(profileButton_1);
		
		JButton profileButton_1_1_1 = new RoundedButton("",i1);
		profileButton_1_1_1.setBounds(35, 28, 34, 33);
		profileButton_1_1_1.setBackground(Color.white);
		profileButton_1_1_1.setToolTipText("2");
		chattingPanel.add(profileButton_1_1_1);
		
		JButton profileButton_1_1 = new RoundedButton("",i2);
		profileButton_1_1.setBounds(9, 28, 34, 33);
		profileButton_1_1.setToolTipText("0");
		//profileButton_1_1_1.setBackground(Color.white);
		chattingPanel.add(profileButton_1_1);
	}

}
