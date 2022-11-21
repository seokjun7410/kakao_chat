package kakao_Chat.design.mini_profile;


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
	
<<<<<<< Updated upstream
	public void makeMiniProfile(String User_name,JPanel chattingPanel, int chattingListHeight, int chattingListIndex) {
		miniProfileDesign.ProfileDesign(User_name, chattingPanel,chattingListHeight,chattingListIndex);
=======
	public void setMiniProfileDesign_Friend() {
		miniProfileDesign = new FriendMiniProfile();

	}
	
	public void makeMiniProfile(String User_name,JPanel chattingPanel, int chattingListHeight, int chattingListIndex) {
		//String chattingName = miniProfileDesign.ProfileDesign(User_name,chattingPanel,chattingListHeight,chattingListIndex);
		miniProfileDesign.ProfileDesign(User_name,chattingPanel,chattingListHeight,chattingListIndex);
>>>>>>> Stashed changes
		//return chattingName;
	}

}
