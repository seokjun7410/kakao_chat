package kakao_Chat;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import java.awt.Image;

import javax.swing.plaf.basic.BasicMenuBarUI;

import kakao_Chat.design.friendslist_drawLine.BottomDrawPanel;
import kakao_Chat.design.mini_profile.MiniProfileManager;
import kakao_Chat.design.pictureEdit.PictureRound;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FriendsListGUI extends JFrame implements MouseListener{
	int chattingListHeight = 77; // 채팅방 버튼을 담는 list의 크기
	static int chattingListIndex = -1; //채팅방이 생성될 때마다 +1
	public static int myChat = 0;
	private MiniProfileManager miniProfileManager; //미니 프로필 디자인 동적 선택 생성 매니저
	private JPanel chatPanel;
	public static JLabel profileButton;
	public JPanel get() {
		return chatPanel;
	}
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	private JLabel lblUserName;
	private FriendsListGUI thisGUI = this;
	private static final  int BUF_LEN = 128;
	public static ArrayList <User> User_list;
	public int Size_list;
	private ArrayList<JPanel> chattingButtonList = new ArrayList<JPanel>();
	public static ArrayList<Integer> ChatRoomEntered = new ArrayList<Integer>();
	public static ArrayList<String> ProfileEntered = new ArrayList<String>();
	private JPanel chattingListPanel = new JPanel();
	private String currentName;
	private Login_Frame.ListenNetwork listenNetwork;
	private String lastMsg = "";
	private String profile_filename;
	private JScrollPane pane;

	public ArrayList<JPanel> getChattingButtonList() {
		return chattingButtonList;
	}

	public FriendsListGUI getFriendsListGUI(){
		return this;
	}

	public FriendsListGUI(Socket s, String name, Login_Frame.ListenNetwork listenNetwork) throws IOException {
		currentName = name;

		this.listenNetwork = listenNetwork;
		setBackground(new Color(255, 255, 255));
		setBounds(400, 200, 400, 690);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		this.socket = s;

		chatPanel = new JPanel();
		chatPanel.setBackground(Color.WHITE);
		chatPanel.setBounds(67, 0, 317, 650);
		

		chattingListPanel.setBackground(Color.WHITE);
		chattingListPanel.setBounds(67, 50, 317, 590);
		chattingListPanel.setLayout(new FlowLayout());
		pane = new JScrollPane (chattingListPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBounds(0, 144, 317, 506);
		pane.setPreferredSize(new Dimension(317,590));
		pane.getVerticalScrollBar().setUnitIncrement(14);
		chatPanel.setLayout(null);
		pane.setBorder(null);
		chatPanel.add(pane);
		getContentPane().add(chatPanel);
		
		JPanel topBar = new JPanel();
		topBar.setBounds(0, 0, 317, 60);
		chatPanel.add(topBar);
		topBar.setBackground(new Color(255, 255, 255));
		topBar.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		topBar.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(12, 29, 50, 35);
		menuBar.setUI ( new BasicMenuBarUI (){
		    public void paint ( Graphics g, JComponent c ){
		       g.setColor ( Color.white );
		       g.fillRect ( 0, 0, c.getWidth (), c.getHeight () );
		    }
		} );
		topBar.add(menuBar);
		
		JMenu chattingLableAndMenu = new JMenu("친구");
		chattingLableAndMenu.setBackground(new Color(255, 255, 255));
		chattingLableAndMenu.setBorderPainted(false);
		chattingLableAndMenu.setFocusable(false);
		chattingLableAndMenu.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
		menuBar.add(chattingLableAndMenu);
		chattingLableAndMenu.setFont(new Font("돋움", Font.BOLD, 17));
		JMenuItem recentSortMenuItem = new JMenuItem("최신 메시지 순");
		recentSortMenuItem.setBackground(new Color(255, 255, 255));
		chattingLableAndMenu.add(recentSortMenuItem);
		
		JMenuItem notReadSortMenuItem = new JMenuItem("안 읽은 메시지 순");
		notReadSortMenuItem.setBackground(new Color(255, 255, 255));
		chattingLableAndMenu.add(notReadSortMenuItem);
		
		JMenuItem favoriteSortMenuItem = new JMenuItem("즐겨찾기 순");
		favoriteSortMenuItem.setBackground(new Color(255, 255, 255));
		chattingLableAndMenu.add(favoriteSortMenuItem);
		
		JMenuItem allReadProcessMenuItem = new JMenuItem("모두 읽음 처리");
		allReadProcessMenuItem.setBackground(new Color(255, 255, 255));
		chattingLableAndMenu.add(allReadProcessMenuItem);
		
//		JButton creatChatting = new JButton("");
//		creatChatting.setIcon(new ImageIcon("img/creatChat.PNG"));
//		creatChatting.setBorderPainted(false);
//		creatChatting.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(chattingListIndex < 20) {
//					createFriendProfile(chattingButtonList,chattingListPanel,"user","img/defult45.png"); //chatingRoom 생성
//					//chattingListPanel.setSize(317,chattingListHeight);
//					chattingListPanel.setPreferredSize(new Dimension(317,chattingListHeight));
//					revalidate();
//					repaint();
//				}else {
//					System.out.println("최대 방 개수를 초과했습니다.");
//				}
//			}
//		});
//		creatChatting.setBounds(279, 30, 25, 23);
//		topBar.add(creatChatting);
		
		JButton addFriend = new JButton("");
		Image af = new ImageIcon("img/add_friend.png").getImage().getScaledInstance(28,22,Image.SCALE_DEFAULT);
		ImageIcon btn_af = new ImageIcon(af);
		addFriend.setIcon(btn_af);
		addFriend.setBackground(new Color(255, 255, 255));
		addFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new addFriendGUI(socket,thisGUI);
			}
		});
		addFriend.setBorderPainted(false);
		addFriend.setBounds(279, 30, 25, 23);
		topBar.add(addFriend);
	
		
		
		JPanel sideMenuPane = new JPanel();
		sideMenuPane.setBackground(new Color(236, 236, 236));
		sideMenuPane.setBounds(0, 0, 67, 651);
		getContentPane().add(sideMenuPane);
		sideMenuPane.setLayout(null);
		
		JButton moveFriendsList = new JButton("");
		moveFriendsList.setBackground(new Color(240, 240, 240));
		moveFriendsList.setIcon(new ImageIcon("img/friendsList.PNG"));
		moveFriendsList.setBorderPainted(false);
		moveFriendsList.setFocusPainted(false);
		moveFriendsList.setBounds(12, 25, 43, 39);
		moveFriendsList.setBorderPainted(false);
		sideMenuPane.add(moveFriendsList);

		JButton moveChattingList = new JButton("");
		moveChattingList.setBackground(new Color(240, 240, 240));
		moveChattingList.setIcon(new ImageIcon("img/chattingList.PNG"));
		moveChattingList.setBounds(12, 74, 43, 39);
		moveChattingList.setBorderPainted(false);
		sideMenuPane.add(moveChattingList);
		
		JButton moveMore = new JButton("");
		moveMore.setBackground(new Color(240, 240, 240));
		moveMore.setIcon(new ImageIcon("img/more.PNG"));
		moveMore.setBorderPainted(false);
		moveMore.setBounds(12, 123, 43, 39);
		sideMenuPane.add(moveMore);
		
		JPanel myInfoPanel = new BottomDrawPanel(25, 75, 290, 75);
		myInfoPanel.setBackground(Color.WHITE);
		myInfoPanel.setBounds(0, 59, 317, 85);
		chatPanel.add(myInfoPanel);
		myInfoPanel.setLayout(null);

        profile_filename = "img/UserProfile/"+Login_Frame.userName+".png";
        ImageIcon pi = PictureRound.setImageRound(profile_filename,60);
		profileButton = new JLabel(pi); //본인 프로필 이미지
		profileButton.setBounds(13, 8, 61, 61);
		profileButton.setBackground(Color.WHITE);
		profileButton.addMouseListener(this);
		myInfoPanel.add(profileButton);
		JLabel currentUserName = new JLabel(Login_Frame.userName);//본인 이름
		currentUserName.setBounds(86, 30, 57, 15);
		myInfoPanel.add(currentUserName);
		myInfoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!ChatRoomEntered.contains(-1)) {
					ArrayList<String> name = new ArrayList<String>();
					name.add(Login_Frame.userName);
					chat_Frame chatting = new chat_Frame(-1, 1, name, socket);
					ChatRoomEntered.add(-1);
				}
				}
			@Override
			public void mouseEntered(MouseEvent e) {
				JPanel b = (JPanel)e.getSource();
		        b.setBackground(new Color(245,245,245));
			}
			@Override//마우스가 버튼 밖으로 나가면 하얀색으로 바뀜
		    public void mouseExited(MouseEvent e) {
				JPanel b = (JPanel)e.getSource();
		        b.setBackground(Color.white);
		    }
			});
		
	}

	public void setlastMsg(String msg){
		this.lastMsg = msg;
		chattingListPanel.repaint();
		chattingListPanel.revalidate();
	}

	public void remveAllofFriends() {
		chattingButtonList.clear();
		chattingListHeight = 77;
		chattingListIndex = -1;
	}

	private void createFriendProfile(ArrayList<JPanel> chattingButtonList, JPanel chattingListPanel, String id, String profile_img) {
		
		JPanel chattingPanel = new JPanel();
		chattingPanel.setAutoscrolls(true);
		chattingPanel.setBounds(67, 50, 317, chattingListHeight);
		chattingPanel.setBackground(Color.white);
		chattingPanel.setLayout(null);
		chattingPanel.setPreferredSize(new Dimension(317,77));


		/**** 채팅방 생성 ****/
		int random = (int) ((Math.random() * (6 - 2)) + 2); //Random한 DummyData 생성
		int DUMMY_NumberOfPeople = random;
		ArrayList<String> name = new ArrayList<String>();
		name.add(id);
		
		miniProfileManager = MiniProfileManager.getInstance();
		miniProfileManager.setMiniProfileDesign_Friend(profile_img);
		String chatName = miniProfileManager.makeMiniProfile(2,name,chattingPanel,chattingListHeight, chattingListIndex,lastMsg);
		/*******************/
		
		
		chattingButtonList.add(chattingPanel);
		chattingListPanel.setBounds(0, 0, 317, chattingListHeight);
		chattingListPanel.add(chattingButtonList.get(++chattingListIndex));
		chattingListHeight += 77;

		chattingPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("clicked ["+chatName+"] 프로필이 눌렸습니다.");
				System.out.println("소켓포트 번호 : "+socket.getPort());
				try {
					Profile_Frame Profile_Frame = new Profile_Frame(chatName,socket,currentName,listenNetwork);
				} catch (IOException e1) {
					// TODO 자동 생성된 catch 블록
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				JPanel b = (JPanel)e.getSource();
		        b.setBackground(new Color(245,245,245));
			}
			@Override//마우스가 버튼 밖으로 나가면 노란색으로 바뀜
		    public void mouseExited(MouseEvent e) {
				JPanel b = (JPanel)e.getSource();
		        b.setBackground(Color.white);
		    }
			});
	}

	public void addFriend(String id,String profile_img) throws IOException {
		if(chattingListIndex < 20) {
			createFriendProfile(chattingButtonList,chattingListPanel,id,profile_img); //친구 프로필 생성
			//chattingListPanel.setSize(317,chattingListHeight);
			chattingListPanel.setPreferredSize(new Dimension(317,chattingListHeight));
			revalidate();
			repaint();
		}else {
			System.out.println("최대 방 개수를 초과했습니다.");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("clicked ["+chatName+"]");
		if (e.getSource().equals(profileButton)) {
			try {
				if(!ProfileEntered.contains(currentName)) {
					my_Profile_Frame profile_Frame = new my_Profile_Frame(Login_Frame.userName, socket, currentName, listenNetwork);
					ProfileEntered.add(currentName);
					System.out.println("프로필 클릭: "+currentName);
				}
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
