package kakao_Chat.design.mini_profile.chat;

import java.awt.*;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.pictureEdit.PictureRound;


public class FourPeople extends MiniProfileDesign_Chat{
	private String[] imgNames;
	public FourPeople(String[] imgNames){
		this.imgNames = imgNames;
	}

	//new ImageIcon("img/sampleProfile.jpg")
	//"img/Profile30.png"
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
//		Image i0 = new ImageIcon(imgNames[0]).getImage().getScaledInstance(34, 33, Image.SCALE_DEFAULT);
//		Image i1 = new ImageIcon(imgNames[1]).getImage().getScaledInstance(34, 33, Image.SCALE_DEFAULT);
//		Image i2 = new ImageIcon(imgNames[2]).getImage().getScaledInstance(34, 33, Image.SCALE_DEFAULT);


//		JButton profileButton_1 = new RoundedButton("",new ImageIcon(i0));
//		profileButton_1.setBounds(22, 5, 34, 33);
//		profileButton_1.setBackground(Color.white);
//		chattingPanel.add(profileButton_1);
//
//		JButton profileButton_1_1_1 = new RoundedButton("",new ImageIcon(i1));
//		profileButton_1_1_1.setBounds(35, 28, 34, 33);
//		profileButton_1_1_1.setBackground(Color.white);
//		chattingPanel.add(profileButton_1_1_1);
//
//		JButton profileButton_1_1 = new RoundedButton("",new ImageIcon(i2));
//		profileButton_1_1.setBounds(9, 28, 34, 33);
//		//profileButton_1_1_1.setBackground(Color.white);
//		chattingPanel.add(profileButton_1_1);

		try{
			ImageIcon i0 = PictureRound.setImageRound(imgNames[0],33);
			ImageIcon i1 = PictureRound.setImageRound(imgNames[1],33);
			ImageIcon i2 = PictureRound.setImageRound(imgNames[2],33);

			JButton profileButton1 = new RoundedButton("",i0);
			profileButton1.setBounds(22, 5, 34, 33);
			profileButton1.setToolTipText("0");
			chattingPanel.add(profileButton1);

			JButton profileButton = new RoundedButton("",i1);
			profileButton.setBounds(35, 28, 34, 33);
			profileButton.setToolTipText("1");
			chattingPanel.add(profileButton);

			JButton profileButton2 = new RoundedButton("",i2);
			profileButton2.setBounds(9, 28, 34, 33);
			profileButton2.setToolTipText("2");
			chattingPanel.add(profileButton2);


		}catch (IOException ex){

		}
	}

}
