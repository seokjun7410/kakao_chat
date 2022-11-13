package chatting_list.design.miniProfileDesign;

import javax.swing.JFrame;
import javax.swing.JPanel;

/* miniProfile의 디자인을 결정하는 class => setMiniProfileDesign */
public class MiniProfileManager {
	
	private MiniProfileDesign miniProfileDesign;
	
	/* single-ton : LazyHolder */
	private MiniProfileManager() {}

	private static class LazyHolder {
	        static final MiniProfileManager INSTANCE = new MiniProfileManager();
	    }
	
	public static MiniProfileManager getInstance() {
	        return LazyHolder.INSTANCE;
	    }
	
	public void setMiniProfileDesign(int numberOfPeople) {
		switch(numberOfPeople) {
		case 2: miniProfileDesign = new TwoPeople(); break;
		case 3: miniProfileDesign = new ThreePeople(); break;
		case 4: miniProfileDesign = new FourPeople(); break;
		default: miniProfileDesign = new MoreThanFivePeople(); break;
		}
	}
	
	public void makeMiniProfile(JPanel chattingPanel, int chattingListHeight, int chattingListIndex) {
		miniProfileDesign.ProfileDesign(chattingPanel,chattingListHeight,chattingListIndex);
	}

}
