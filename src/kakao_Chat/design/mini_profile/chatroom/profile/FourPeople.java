package kakao_Chat.design.mini_profile.chatroom.profile;

import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.mini_profile.chatroom.MiniProfileDesign_chatroom;
import kakao_Chat.design.pictureEdit.PictureRound;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class FourPeople extends MiniProfileDesign_chatroom {
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
//
//
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
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(186,206,224));
		p.setPreferredSize(new Dimension(50,50));

		ImageIcon i0 = PictureRound.setImageRound(imgNames[0],23);
		ImageIcon i1 = PictureRound.setImageRound(imgNames[1],23);
		ImageIcon i2 = PictureRound.setImageRound(imgNames[2],23);

		JLabel profileButton1 = new JLabel(i0);
		profileButton1.setBounds(12, 0, 23, 23);
		profileButton1.setToolTipText("0");
		//chattingPanel.add(profileButton1, BorderLayout.WEST);

		JLabel profileButton = new JLabel(i1);
		profileButton.setBounds(22, 20, 23, 23);
		profileButton.setToolTipText("1");
		//chattingPanel.add(profileButton, BorderLayout.WEST);

		JLabel profileButton2 = new JLabel(i2);
		profileButton2.setBounds(0, 20, 23, 23);
		profileButton2.setToolTipText("2");
		//chattingPanel.add(profileButton2, BorderLayout.WEST);

		p.add(profileButton);
		p.add(profileButton1);
		p.add(profileButton2);

		chattingPanel.add(p, BorderLayout.WEST);

	}

}
