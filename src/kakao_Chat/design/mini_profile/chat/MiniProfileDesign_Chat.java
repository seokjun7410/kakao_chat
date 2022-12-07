package kakao_Chat.design.mini_profile.chat;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kakao_Chat.design.mini_profile.MiniProfileDesign;

public class MiniProfileDesign_Chat extends MiniProfileDesign {

    protected String ProfileDesign(int size, ArrayList<String> members, JPanel chattingPanel, int chattingListHeight, int chattingListIndex, String lastMsg) throws IOException {


        StringBuffer name = new StringBuffer();
        for (int i = 0; i < members.size(); i++) {
            if(i!= members.size()-1)
                name.append(members.get(i)+", ");
            else
                name.append(members.get(i));
        }

        String chattingName = name.toString();
        JLabel userNameLabel = new JLabel(chattingName);
        userNameLabel.setFont(new Font("나눔고딕", Font.BOLD, 13));
        userNameLabel.setBounds(75, 16, 93, 15);
        chattingPanel.add(userNameLabel);

        JLabel lastMassageLabel = new JLabel(lastMsg);
        lastMassageLabel.setFont(new Font("나눔고딕", Font.PLAIN, 12));
        lastMassageLabel.setForeground(new Color(168,168,168));
        lastMassageLabel.setBounds(75, 38, 199, 15);
        lastMassageLabel.setToolTipText("lastMessageLabel");
        chattingPanel.add(lastMassageLabel);

        miniProfileMakeByNumber(chattingPanel);

        return chattingName;
    }

    public void miniProfileMakeByNumber(JPanel chattingPanel) throws IOException {
        // TODO Auto-generated method stub

    }


}
