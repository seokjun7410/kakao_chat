package kakao_Chat.design.mini_profile;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kakao_Chat.RoomInfo;


public abstract class MiniProfileDesign {
	
	
	protected abstract String ProfileDesign(int size, ArrayList<String> member,JPanel chattingPanel,int chattingListHeight, int chattingListIndex, String lastMsg) throws IOException;

	protected abstract void miniProfileMakeByNumber(JPanel chattingPanel) throws IOException;
}
