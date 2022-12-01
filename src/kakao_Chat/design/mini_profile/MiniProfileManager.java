package kakao_Chat.design.mini_profile;


import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import kakao_Chat.RoomInfo;
import kakao_Chat.design.mini_profile.chat.FourPeople;
import kakao_Chat.design.mini_profile.chat.MoreThanFivePeople;
import kakao_Chat.design.mini_profile.chat.ThreePeople;
import kakao_Chat.design.mini_profile.chat.TwoPeople;
import kakao_Chat.design.mini_profile.friend.FriendMiniProfile;

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
	
	public void setMiniProfileDesign_Chat(int numberOfPeople) {
		switch(numberOfPeople) {
		case 2: miniProfileDesign = new TwoPeople(); break;
		case 3: miniProfileDesign = new ThreePeople(); break;
		case 4: miniProfileDesign = new FourPeople(); break;
		default: miniProfileDesign = new MoreThanFivePeople(); break;
		}
	}
	
	public void setMiniProfileDesign_Friend(String profile_img) {
		miniProfileDesign = new FriendMiniProfile(profile_img);

	}
	
	public String makeMiniProfile(int size, ArrayList<String> members, JPanel chattingPanel, int chattingListHeight, int chattingListIndex) {
		String chattingName = miniProfileDesign.ProfileDesign(size,members,chattingPanel,chattingListHeight,chattingListIndex);
		return chattingName;
	}


}
