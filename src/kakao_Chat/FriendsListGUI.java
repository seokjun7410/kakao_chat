package kakao_Chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.friendslist_drawLine.BottomDrawPanel;
import kakao_Chat.design.mini_profile.MiniProfileManager;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FriendsListGUI {

	private JFrame frame;
	public JFrame getFrmae() {
		return frame;
	}

	private JPanel friends;
	public JPanel getFriendsPanel() {
		return friends;
	}
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FriendsListGUI window = new FriendsListGUI();
//					window.frame.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public FriendsListGUI(JFrame frame) {
		this.frame = frame;
		initialize(frame);
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	static int chattingListHeight = 53; // 채팅방 버튼을 담는 list의 크기
	static int chattingListIndex = -1; //채팅방이 생성될 때마다 +1
	private MiniProfileManager miniProfileManager; //미니 프로필 디자인 동적 선택 생성 매니저
	
	private void initialize( JFrame frame ) {
		ArrayList<JPanel> chattingButtonList = new ArrayList<JPanel>();
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 400, 690);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel sideMenuPane = new JPanel();
		sideMenuPane.setBackground(new Color(236, 236, 236));
		sideMenuPane.setBounds(0, 0, 67, 651);
		frame.getContentPane().add(sideMenuPane);
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
		
		JPanel firendPanel = new JPanel();
		firendPanel.setBounds(0, 0, 383, 651);
		sideMenuPane.add(firendPanel);
		
		/* 채팅방 생성 버튼 클릭 */
		
		friends = new JPanel();
		friends.setBackground(new Color(255, 255, 255));
		friends.setBounds(67, 0, 317, 651);
		friends.setLayout(null);
		frame.getContentPane().add(friends);
		
		JPanel chattingListPanel = new JPanel();
		chattingListPanel.setBackground(new Color(255, 255, 255));
		chattingListPanel.setBounds(0, 136, 317, 71);
		chattingListPanel.setLayout(new GridLayout(0,1));
		//frame.getContentPane().add(chattingListPanel);
		friends.add(chattingListPanel);
		
		JPanel topBar = new JPanel();
		topBar.setBackground(new Color(255, 255, 255));
		topBar.setBounds(0, 0, 317, 52);
		//frame.getContentPane().add(topBar);
		friends.add(topBar);
		topBar.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(12, 29, 50, 23);
		topBar.add(menuBar);
		
		JMenu chattingLableAndMenu = new JMenu("친구");
		chattingLableAndMenu.setBackground(new Color(255, 255, 255));
		chattingLableAndMenu.setBorderPainted(false);
		menuBar.add(chattingLableAndMenu);
		chattingLableAndMenu.setFont(new Font("돋움", Font.BOLD, 17));
		
		
		JButton creatChatting = new JButton("");
		creatChatting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		creatChatting.setIcon(new ImageIcon("img/creatChat.PNG"));
		creatChatting.setBorderPainted(false);
		creatChatting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(chattingListIndex < 8) {
					createChattingRoom(chattingButtonList,chattingListPanel); //chatingRoom 생성
					chattingListPanel.revalidate();
					chattingListPanel.repaint();
				}else {
					System.out.println("최대 방 개수를 초과했습니다.");
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton b = (JButton)e.getSource();
				b.setIcon(new ImageIcon("img/creatChat_dark.PNG"));
			}
			@Override//마우스가 버튼 밖으로 나가면 하얀색으로 바뀜
		    public void mouseExited(MouseEvent e) {
				JButton b = (JButton)e.getSource();
				b.setIcon(new ImageIcon("img/creatChat.PNG"));
		    }
		});
		
		creatChatting.setBounds(279, 24, 25, 23);
		topBar.add(creatChatting);
		
		JButton addFriend = new JButton("");
		addFriend.setBorderPainted(false);
		addFriend.setBounds(242, 24, 25, 23);
		addFriend.setIcon(new ImageIcon("img/addFriend33.PNG"));
		addFriend.setBorderPainted(false);
		topBar.add(addFriend);
		addFriend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new addFriendGUI();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton b = (JButton)e.getSource();
				b.setIcon(new ImageIcon("img/addFriend33_dark.PNG"));
			}
			@Override//마우스가 버튼 밖으로 나가면 하얀색으로 바뀜
		    public void mouseExited(MouseEvent e) {
				JButton b = (JButton)e.getSource();
				b.setIcon(new ImageIcon("img/addFriend33.PNG"));
		    }
		});
		
		
		JPanel myInfoPanel = new BottomDrawPanel();
		myInfoPanel.setBackground(Color.WHITE);
		myInfoPanel.setBounds(0, 52, 317, 85);
		//frame.getContentPane().add(myInfoPanel);
		friends.add(myInfoPanel);
		myInfoPanel.setLayout(null);
		JButton profileButton = new RoundedButton("",new ImageIcon("img/defaultProfile80.png")); //본인 프로필 이미지
		profileButton.setBounds(13, 8, 61, 61);
		myInfoPanel.add(profileButton);
		
		JLabel currentUserName = new JLabel("USER");//본인 이름
		currentUserName.setBounds(86, 30, 57, 15);
		myInfoPanel.add(currentUserName);
		myInfoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chat_Frame chatting = new chat_Frame(1,"test");
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
	
	private void createChattingRoom(ArrayList<JPanel> chattingButtonList,JPanel chattingListPanel) {
		
		JPanel chattingPanel = new JPanel();
		chattingPanel.setBounds(67, 121, 317, chattingListHeight);
		frame.getContentPane().add(chattingPanel);
		chattingPanel.setBackground(Color.white);
		chattingPanel.setLayout(null);
		
		/**** 친구 생성 ****/
		int random = (int) ((Math.random() * (6 - 2)) + 2); //Random한 DummyData 생성
		int DUMMY_NumberOfPeople = random;
		
		miniProfileManager = MiniProfileManager.getInstance();
		miniProfileManager.setMiniProfileDesign_Friend();
		String chatName = miniProfileManager.makeMiniProfile(chattingPanel,chattingListHeight, chattingListIndex);
		/*******************/
		
		
		chattingButtonList.add(chattingPanel);
		chattingListPanel.setBounds(0, 130, 317, chattingListHeight);
		chattingListPanel.add(chattingButtonList.get(++chattingListIndex));
		chattingListHeight += 53;
		
		chattingPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("clicked ["+chatName+"]");
				chat_Frame chatting = new chat_Frame(DUMMY_NumberOfPeople,chatName);
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
}
