package kakao_Chat;

import java.util.ArrayList;

public class RoomInfo {
	private int roomNum;
	private int size;
	private ArrayList<String> members;
	public RoomInfo (int RoomNum, int size, ArrayList<String> members) {
		this.roomNum = RoomNum;
		this.size= size ;
		this.members =members;
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
}
