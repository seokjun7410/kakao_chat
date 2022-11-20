package kakao_Chat.design.mini_profile;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public abstract class MiniProfileDesign {
	
	
	protected abstract String ProfileDesign(JPanel chattingPanel,int chattingListHeight, int chattingListIndex);
	
	protected abstract void miniProfileMakeByNumber(JPanel chattingPanel);
}
