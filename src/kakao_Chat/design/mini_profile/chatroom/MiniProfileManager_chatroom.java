package kakao_Chat.design.mini_profile.chatroom;


import kakao_Chat.design.mini_profile.MiniProfileDesign;
import kakao_Chat.design.mini_profile.chatroom.profile.FourPeople;
import kakao_Chat.design.mini_profile.chatroom.profile.MoreThanFivePeople;
import kakao_Chat.design.mini_profile.chatroom.profile.ThreePeople;
import kakao_Chat.design.mini_profile.chatroom.profile.TwoPeople;


import javax.swing.*;
import java.util.ArrayList;

/* miniProfile의 디자인을 결정하는 class => setMiniProfileDesign */
public class MiniProfileManager_chatroom {
	
	private MiniProfileDesign_chatroom miniProfileDesign;
	
	/* single-ton : LazyHolder */
	private MiniProfileManager_chatroom() {}

	private static class LazyHolder {
	        static final MiniProfileManager_chatroom INSTANCE = new MiniProfileManager_chatroom();
	    }
	
	public static MiniProfileManager_chatroom getInstance() {
	        return new MiniProfileManager_chatroom();//LazyHolder.INSTANCE;
	    }
	
	public void setMiniProfileDesign_Chat(int numberOfPeople,String[] imgNames) {
		switch(numberOfPeople) {
		case 2: miniProfileDesign = new TwoPeople(imgNames); break;
		case 3: miniProfileDesign = new ThreePeople(imgNames); break;
		case 4: miniProfileDesign = new FourPeople(imgNames); break;
		default: miniProfileDesign = new MoreThanFivePeople(imgNames); break;
		}
	}

	
	public void makeMiniProfile(JPanel chattingPanel) {
		miniProfileDesign.miniProfileMakeByNumber(chattingPanel);

	}

	public MiniProfileDesign_chatroom getMiniProfileDesign() {
		return miniProfileDesign;
	}
}
