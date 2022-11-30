package server;

// Java Chatting Server
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kakao_Chat.RoomInfo;
import kakao_Chat.User;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class JavaChatServer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextArea textArea;
	private JTextField txtPortNumber;
	private Vector UserLoginInfo = new Vector();
	private ServerSocket socket;
	private Socket client_socket;
	private Vector UserVec = new Vector();
	private Vector RoomVec = new Vector();
	private static final int BUF_LEN = 128;
	public int recent_Num = 0;
	public int room_Num = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaChatServer frame = new JavaChatServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JavaChatServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 338, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 300, 244);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		JLabel lblNewLabel = new JLabel("Port Number");
		lblNewLabel.setBounds(12, 264, 87, 26);
		contentPane.add(lblNewLabel);

		txtPortNumber = new JTextField();
		txtPortNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtPortNumber.setText("30000");
		txtPortNumber.setBounds(111, 264, 199, 26);
		contentPane.add(txtPortNumber);
		txtPortNumber.setColumns(10);

		JButton btnServerStart = new JButton("Server Start");
		btnServerStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					socket = new ServerSocket(Integer.parseInt(txtPortNumber.getText()));
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AppendText("Chat Server Running..");
				btnServerStart.setText("Chat Server Running..");
				btnServerStart.setEnabled(false);
				txtPortNumber.setEnabled(false);
				AcceptServer accept_server = new AcceptServer();
				accept_server.start();
			}
		});
		btnServerStart.setBounds(12, 300, 300, 35);
		contentPane.add(btnServerStart);
	}

	class AcceptServer extends Thread {
		@SuppressWarnings("unchecked")
		public void run() {
			while (true) {
				try {
					AppendText("Waiting new clients ...");
					client_socket = socket.accept();
					AppendText("접속: " + client_socket);
					UserService new_user = new UserService(client_socket);
					new_user.start();

				} catch (IOException e) {
					AppendText("accept() error");
					// System.exit(0);
				}
			}
		}

	}

	public void AppendText(String str) {

		textArea.append(str + "\n");
		textArea.setCaretPosition(textArea.getText().length());
	}

	class UserService extends Thread {
		private InputStream is;
		private OutputStream os;
		private DataInputStream dis;
		private DataOutputStream dos;
		private Socket client_socket;
		private Vector user_vc;
		public String UserName = "";

		public UserService(Socket client_socket) {
			// TODO Auto-generated constructor stub

			this.client_socket = client_socket;
			this.user_vc = UserVec;
			try {
				is = client_socket.getInputStream();
				dis = new DataInputStream(is);
				os = client_socket.getOutputStream();
				dos = new DataOutputStream(os);
				// line1 = dis.readUTF();
				// /login user1 ==> msg[0] msg[1]
//				byte[] b = new byte[BUF_LEN];
//				dis.read(b);
//				
//				String line1 = new String(b);
//				AppendText(line1);
//				String[] msg = line1.split(" ");
//				UserName = msg[1].trim();
//				UserStatus = "O"; 
//				Login();
			} catch (Exception e) {
				AppendText("userService error");
			}
		}

		public String getUserName() {
			return this.UserName;
		}

		public void Login() {
			AppendText(UserName + " 님이 접속하였습니다");
			UserVec.add(this);
			WriteOne(UserName + " /101");
			String msg = UserName + " /login" + " /101";
			WriteAll(msg);
			AppendText("현재 인원: " + UserVec.size());
		}

		public void Logout() {
			String msg = "[" + UserName + "]���� ���� �Ͽ����ϴ�.\n";
			UserVec.removeElement(this);
			WriteAll(msg);
			AppendText("[" + UserName + "] 님이 로그아웃 하셨습니다. 인원: " + UserVec.size());
		}

		public void LoginFail() {
			AppendText(UserName + " 님이 로그인에 실패하였습니다");
			// WriteOne("Welcome to Java chat server\n");
			WriteOne(UserName + " /102");
		}

		public void SignUp() {
			AppendText(UserName + " 님이 새로 가입하였습니다");
			// WriteOne("Welcome to Java chat server\n");
			UserVec.add(this);
			WriteOne(UserName + " /103");
			AppendText("현재 인원: " + UserVec.size());
		}

		public void WriteAll(String str) {
			for (int i = 0; i < user_vc.size(); i++) {
				UserService user = (UserService) user_vc.elementAt(i);
				user.WriteOne(str);
//				if (user.UserStatus == "O")
//					user.WriteOne(str);
			}
		}

		public void WriteOthers(String str) {
			for (int i = 0; i < user_vc.size(); i++) {
				UserService user = (UserService) user_vc.elementAt(i);
//				if (user!=this && user.UserStatus == "O")
//					user.WriteOne(str);
			}
		}

		public byte[] MakePacket(String msg) {
			byte[] packet = new byte[BUF_LEN];
			byte[] bb = null;
			int i;
			for (i = 0; i < BUF_LEN; i++)
				packet[i] = 0;
			try {
				bb = msg.getBytes("euc-kr");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (i = 0; i < bb.length; i++)
				packet[i] = bb[i];
			return packet;
		}

		public void sendTo(String name, String msg) {
			for (int i = 0; i < UserVec.size(); i++) {
				UserService user = (UserService) user_vc.elementAt(i);
				if (user.getUserName().equals(name)) {
					user.WriteOne(msg);
					break;
				}
			}
		}

		public void WriteOne(String msg) {
			try {
				// dos.writeUTF(msg);
				byte[] bb;
				bb = MakePacket(msg);
				dos.write(bb, 0, bb.length);
			} catch (IOException e) {
				AppendText("dos.write() error");
				try {
					dos.close();
					dis.close();
					client_socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Logout();
			}
		}

		@SuppressWarnings("unchecked")
		public void run() {
			while (true) {
				try {
					// String msg = dis.readUTF();
					byte[] b = new byte[BUF_LEN];
					int ret;
					ret = dis.read(b);
					if (ret < 0) {
						AppendText("dis.read() < 0 error");
						try {
							dos.close();
							dis.close();
							client_socket.close();
							Logout();
							break;
						} catch (Exception ee) {
							break;
						} // catch�� ��
					}
					String msg = new String(b, "euc-kr");
					msg = msg.trim();
					AppendText(msg);
					System.out.println("ServcerRecv = " + msg);

					String[] args = msg.split(" ");

					if (args[0].matches("/100")) { // 로그인 "/100 id pw"
						String id = args[1].trim();
						String pw = args[2].trim();
						if (SearchUserInfo(id, pw) == 1) { // 로그인 성공
							UserName = id;
							Login();
						} else if (SearchUserInfo(id, pw) == -2) { // 로그인 실패
							UserName = id;
							LoginFail();
						} else if (SearchUserInfo(id, pw) == -1) { // 회원 정보 추가
							UserName = id;
							User new_user = new User(id, pw, assign_UserNum());
							UserLoginInfo.add(new_user);
							SignUp();
						}
					}

					else if (args[0].matches("/200")) { // 친구 추가
						String id = args[1].trim();
						if (SearchUserInfo(id) == -1) { // 유저 조회 실패
							WriteOne("/202");
						} else { // 유저 조회 성공
							int userNum = SearchUserInfo(id);
							WriteOne("/201 " + ((User) UserLoginInfo.get(userNum)).getId() + " "
									+ ((User) UserLoginInfo.get(userNum)).getImage());
						}
					} else if (args[0].matches("/300")) { // 채팅방 생성
						RoomInfo new_Room; // 방 객체
						int room_num = assign_RoomNum(); //방 번호 배정
						int size = Integer.parseInt(args[1]); //방 인원
						ArrayList<String> members = new ArrayList<String>();
						StringBuffer temp = new StringBuffer(); //보낼 메세지
						
						System.out.println("servRecv /300");
						
						String send_msg = "/301 " + String.valueOf(room_num)+ " " + UserName ;
						temp.append(send_msg);
						for (int i = 0; i < Integer.parseInt(args[1]); i++) {
							String name = args[i + 2];
							members.add(name);
							temp.append(" ").append(name);
						}
						new_Room = new RoomInfo(room_num,size,members);
						send_msg = temp.toString();
						for (int i = 0; i < Integer.parseInt(args[1]); i++) {
							sendTo(args[i + 2], send_msg); //send_msg : /301 방번호 이름1 이름2 ...
						}
//						WriteAll(send_msg);
					} else if (args[0].matches("/500")) { // 메세지 전송
						String send_msg = "/502 " + args[2];
						System.out.println("500 send_msg : " + send_msg);
						sendTo(args[1], send_msg);
						//WriteAll(send_msg);
					}

					else if (args[0].matches("/501")) { // 사진, 파일 전송
						String send_msg = "/503 " + args[2];
						sendTo(args[1], send_msg);
						
					}

					else if (args[0].matches("/600")) { // 프로필 변경 전송
						String send_msg = "/601 " + getUserName() + " " + args[1];
						WriteAll(send_msg);
					}

//					else if (args[1].matches("/exit")) {
//						Logout();
//						break;
//					} else if (args[1].matches("/list")) {
//						WriteOne("User list\n");
//						WriteOne("Name\tStatus\n");
//						WriteOne("-----------------------------\n");
//						for (int i = 0; i < user_vc.size(); i++) {
//							UserService user = (UserService) user_vc.elementAt(i);
////							WriteOne(user.UserName + "\t" + user.UserStatus + "\n");
//						}
//						WriteOne("-----------------------------\n");
//					} else if (args[1].matches("/sleep")) {
//						UserStatus = "S";
//					} else if (args[1].matches("/wakeup")) {
//						UserStatus = "O";
					else if (args[1].matches("/to")) {
						for (int i = 0; i < user_vc.size(); i++) {
							UserService user = (UserService) user_vc.elementAt(i);
//							if (user.UserName.matches(args[2]) && user.UserStatus.matches("O")) {
//								String msg2 = "";
//								for (int j = 3;j<args.length;j++) {
//									msg2 += args[j];
//									if (j < args.length - 1)
//										msg2 += " ";
//								}
//								user.WriteOne("[�ӼӸ�] " + args[0] + " " + msg2 + "\n");
//								break;
//							}		
						}
					} else {
//						UserStatus = "O";
						WriteAll(msg + "\n"); // Write All
					}
				} catch (IOException e) {
					AppendText("dis.read() error");
					try {
						dos.close();
						dis.close();
						client_socket.close();
						Logout();
						break;
					} catch (Exception ee) {
						break;
					}
				}
			}
		}

		public int assign_UserNum() {
			int num = recent_Num + 1;
			recent_Num = num;
			return num;
		}

		public int assign_RoomNum() {
			int num = room_Num + 1;
			recent_Num = num;
			return num;
		}

		public int SearchUserInfo(String user_id, String user_pw) {
			int found = -1;
			for (int i = 0; i < UserLoginInfo.size(); i++) {
				if (((User) UserLoginInfo.get(i)).getId().equals(user_id)) {
					if (((User) UserLoginInfo.get(i)).getPw().equals(user_pw)) { // 아이디, 비밀번호 일치
						found = 1;
						break;
					} else { // 아이디만 일치, 로그인 실패
						found = -2;
						break;
					}
				}
			}
			return found; // 일치하는 아이디가 없을 경우 0 리턴
		}

		public int SearchUserInfo(String user_id) {
			int found = -1;
			for (int i = 0; i < UserLoginInfo.size(); i++) {
				if (((User) UserLoginInfo.get(i)).getId().equals(user_id)) {
					found = i;
					break;
				}
			}
			return found; // 일치하는 아이디가 없을 경우 -1 리턴
		}
	}
}
