package kakao_Chat.design.mini_profile.chat;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import kakao_Chat.design.RoundedButton;


public class FourPeople extends MiniProfileDesign_Chat{
	private String[] imgNames;
	public FourPeople(String[] imgNames){
		this.imgNames = imgNames;
	}

	//new ImageIcon("img/sampleProfile.jpg")
	//"img/Profile30.png"
	@Override
	public void miniProfileMakeByNumber(JPanel chattingPanel) {
		Image i0 = new ImageIcon(imgNames[0]).getImage().getScaledInstance(34, 33, Image.SCALE_DEFAULT);
		Image i1 = new ImageIcon(imgNames[1]).getImage().getScaledInstance(34, 33, Image.SCALE_DEFAULT);
		Image i2 = new ImageIcon(imgNames[2]).getImage().getScaledInstance(34, 33, Image.SCALE_DEFAULT);


		JButton profileButton_1 = new RoundedButton("",new ImageIcon(i0));
		profileButton_1.setBounds(22, 5, 34, 33);
		profileButton_1.setBackground(Color.white);
		chattingPanel.add(profileButton_1);
		
		JButton profileButton_1_1_1 = new RoundedButton("",new ImageIcon(i1));
		profileButton_1_1_1.setBounds(35, 28, 34, 33);
		profileButton_1_1_1.setBackground(Color.white);
		chattingPanel.add(profileButton_1_1_1);
		
		JButton profileButton_1_1 = new RoundedButton("",new ImageIcon(i2));
		profileButton_1_1.setBounds(9, 28, 34, 33);
		//profileButton_1_1_1.setBackground(Color.white);
		chattingPanel.add(profileButton_1_1);
	}

}
