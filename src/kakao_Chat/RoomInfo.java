package kakao_Chat;

import java.util.ArrayList;

public class RoomInfo {
	private int roomNum;
	private int size;
	/* HSJ 채팅리스트 만들때 마지막 메시지 필요해서 추가 */
	private String lastMessage;
	private ArrayList<String> members;
	public RoomInfo (int RoomNum, int size, ArrayList<String> members, String lastMessage) {
		this.roomNum = RoomNum;
		this.size= size ;
		this.members =members;
		this.lastMessage = lastMessage;
	}
	public ArrayList<String> getMembers() {
		return members;
	}
	
	public String getMembersToString() {
		StringBuffer buffer = new StringBuffer();
		for(int i =0; i<members.size(); i++) {
			buffer.append(members.get(i)+" ");
		}
		return buffer.toString();
	}
	
	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	/* HSJ 채팅리스트 만들때 마지막 메시지 필요해서 추가 */
	public String getLastMessage() {
		return lastMessage;
	}
}
