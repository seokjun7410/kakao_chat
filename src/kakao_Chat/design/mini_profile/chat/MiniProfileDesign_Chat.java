package kakao_Chat.design.mini_profile.chat;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kakao_Chat.design.mini_profile.MiniProfileDesign;

public class MiniProfileDesign_Chat extends MiniProfileDesign {

    protected String ProfileDesign(int size, ArrayList<String> members, JPanel chattingPanel, int chattingListHeight, int chattingListIndex, String lastMsg) {


        StringBuffer name = new StringBuffer();
        for (int i = 0; i < members.size(); i++) {
            name.append(" " + members.get(i));
        }

        String chattingName = name.toString();
        JLabel userNameLabel = new JLabel(chattingName);
        userNameLabel.setBounds(86, 16, 93, 15);
        chattingPanel.add(userNameLabel);

        JLabel lastMassageLabel = new JLabel(lastMsg);
        lastMassageLabel.setBounds(86, 42, 199, 15);
        lastMassageLabel.setToolTipText("lastMessageLabel");
        chattingPanel.add(lastMassageLabel);

        miniProfileMakeByNumber(chattingPanel);

        return chattingName;
    }

    public void miniProfileMakeByNumber(JPanel chattingPanel) {
        // TODO Auto-generated method stub

    }


}
