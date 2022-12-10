package kakao_Chat.design.mini_profile.chatroom.profile;


import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.mini_profile.chatroom.MiniProfileDesign_chatroom;
import kakao_Chat.design.pictureEdit.PictureRound;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class MoreThanFivePeople extends MiniProfileDesign_chatroom {
    private String[] imgNames;

    public MoreThanFivePeople(String[] imgNames) {
        this.imgNames = imgNames;
    }
    private JLabel profileButton2;
    private JLabel profileButton1;
    private JLabel profileButton3;
    private JLabel profileButton;

    public JLabel getProfileButton2() {
        return profileButton2;
    }

    public JLabel getProfileButton1() {
        return profileButton;
    }

    public JLabel getProfileButton3() {
        return profileButton3;
    }

    public JLabel getProfileButton() {
        return profileButton1;
    }

    //"img/defaultProfile40.png")
    @Override
    public void miniProfileMakeByNumber(JPanel chattingPanel) {
//		Image i0 = new ImageIcon(imgNames[0]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//		Image i1 = new ImageIcon(imgNames[1]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//		Image i2 = new ImageIcon(imgNames[2]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//		Image i3 = new ImageIcon(imgNames[3]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//
//		JButton profileButton_1 = new RoundedButton("",new ImageIcon(i0));
//		profileButton_1.setBounds(10, 6, 30, 30);
//		chattingPanel.add(profileButton_1);
//
//		JButton profileButton_1_1 = new RoundedButton("",new ImageIcon(i1));
//		profileButton_1_1.setBounds(39, 6, 30, 30);
//		chattingPanel.add(profileButton_1_1);
//
//		JButton profileButton_1_2 = new RoundedButton("",new ImageIcon(i2));
//		profileButton_1_2.setBounds(10, 34, 30, 30);
//		chattingPanel.add(profileButton_1_2);
//
//		JButton profileButton_1_3 = new RoundedButton("",new ImageIcon(i3));
//		profileButton_1_3.setBounds(39, 34, 30, 30);
//		chattingPanel.add(profileButton_1_3);

        try {
            ImageIcon i0 = PictureRound.setImageRound(imgNames[0], 25);
            ImageIcon i1 = PictureRound.setImageRound(imgNames[1], 25);
            ImageIcon i2 = PictureRound.setImageRound(imgNames[2], 25);
            ImageIcon i3 = PictureRound.setImageRound(imgNames[3], 25);

			JPanel p = new JPanel();
			p.setLayout(null);
			p.setBackground(new Color(186,206,224));
			p.setPreferredSize(new Dimension(50,50));

            profileButton1 = new JLabel(i0);
            profileButton1.setBounds(0, 0, 25, 25);
            profileButton1.setToolTipText("0");
            //chattingPanel.add(profileButton1, BorderLayout.WEST);

            profileButton = new JLabel(i1);
            profileButton.setBounds(24, 0, 25, 25);
            profileButton.setToolTipText("1");
            //chattingPanel.add(profileButton, BorderLayout.WEST);

            profileButton2 = new JLabel(i2);
            profileButton2.setBounds(0, 23, 25, 25);
            profileButton2.setToolTipText("2");
            //chattingPanel.add(profileButton2, BorderLayout.WEST);

            profileButton3 = new JLabel(i3);
            profileButton3.setBounds(24, 23, 25, 25);
            profileButton3.setToolTipText("3");
            //chattingPanel.add(profileButton3, BorderLayout.WEST);

			p.add(profileButton);
			p.add(profileButton1);
			p.add(profileButton2);
			p.add(profileButton3);

			chattingPanel.add(p, BorderLayout.WEST);
        } catch (Exception e) {

        }
    }

}
