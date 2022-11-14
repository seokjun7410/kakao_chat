package chatting_list;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
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
import chatting_list.design.miniProfileDesign.MiniProfileManager;
import chatting_list.design.miniProfileDesign.MiniProfileType;
import chatting_list.design.miniProfileDesign.TwoPeople;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ChattingListGUI{

	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChattingListGUI window = new ChattingListGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChattingListGUI() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	static int chattingListHeight = 71; // 채팅방 버튼을 담는 list의 크기
	static int chattingListIndex = -1; //채팅방이 생성될 때마다 +1
	private MiniProfileManager miniProfileManager; //미니 프로필 디자인 동적 선택 생성 매니저
	
	private void initialize() {
		ArrayList<JPanel> chattingButtonList = new ArrayList<JPanel>();
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 400, 690);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel chattingListPanel = new JPanel();
		chattingListPanel.setBackground(new Color(255, 255, 255));
		chattingListPanel.setBounds(67, 50, 317, 71);
		chattingListPanel.setLayout(new GridLayout(0,1));
		frame.getContentPane().add(chattingListPanel);
		
		
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
		
		JPanel topBar = new JPanel();
		topBar.setBackground(new Color(255, 255, 255));
		topBar.setBounds(67, 0, 317, 52);
		frame.getContentPane().add(topBar);
		topBar.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(12, 29, 50, 23);
		topBar.add(menuBar);
		
		JMenu chattingLableAndMenu = new JMenu("채팅");
		chattingLableAndMenu.setBackground(new Color(255, 255, 255));
		chattingLableAndMenu.setBorderPainted(false);
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
		
		JButton creatChatting = new JButton("");
		creatChatting.setIcon(new ImageIcon("img/creatChat.PNG"));
		creatChatting.setBorderPainted(false);
		
		/* 채팅방 생성 버튼 클릭 */
		creatChatting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(chattingListIndex < 7) {
					createChattingRoom(chattingButtonList,chattingListPanel); //chatingRoom 생성
					chattingListPanel.revalidate();
					chattingListPanel.repaint();
				}else {
					System.out.println("최대 방 개수를 초과했습니다.");
				}
			}
		});
		creatChatting.setBounds(279, 24, 25, 23);
		topBar.add(creatChatting);
		
		
		/* *
		 * MiniProfile 디자인 할때 사용 
		 * */
		
//		JPanel chattingPanel_1 = new JPanel();
//		chattingPanel_1.setLayout(null);
//		chattingPanel_1.setBounds(67, 190, 317, 71);
//		frame.getContentPane().add(chattingPanel_1);
//		
//		JLabel userNameLabel_1 = new JLabel("User1");
//		userNameLabel_1.setBounds(86, 16, 93, 15);
//		chattingPanel_1.add(userNameLabel_1);
//		
//		JLabel lastMassageLabel_1 = new JLabel("last message left");
//		lastMassageLabel_1.setBounds(86, 42, 199, 15);
//		chattingPanel_1.add(lastMassageLabel_1);
//		
//		JButton profileButton_1 = new JButton("");
//		profileButton_1.setBounds(1, 6, 30, 30);
//		chattingPanel_1.add(profileButton_1);
//		
//		JButton profileButton_1_1 = new JButton("");
//		profileButton_1_1.setBounds(29, 6, 30, 30);
//		chattingPanel_1.add(profileButton_1_1);
//		
//		JButton profileButton_1_2 = new JButton("");
//		profileButton_1_2.setBounds(1, 34, 30, 30);
//		chattingPanel_1.add(profileButton_1_2);
//		
//		JButton profileButton_1_3 = new JButton("");
//		profileButton_1_3.setBounds(29, 34, 30, 30);
//		chattingPanel_1.add(profileButton_1_3);
		
		
	}
	
	private void createChattingRoom(ArrayList<JPanel> chattingButtonList,JPanel chattingListPanel) {
		
		JPanel chattingPanel = new JPanel();
		chattingPanel.setBounds(67, 50, 317, chattingListHeight);
		frame.getContentPane().add(chattingPanel);
		chattingPanel.setBackground(Color.white);
		chattingPanel.setLayout(null);
		
		/**** 채팅방 생성 ****/
		int random = (int) ((Math.random() * (6 - 2)) + 2); //Random한 DummyData 생성
		int DUMMY_NumberOfPeople = random;
		
		miniProfileManager = MiniProfileManager.getInstance();
		miniProfileManager.setMiniProfileDesign( DUMMY_NumberOfPeople );
		String chatName = miniProfileManager.makeMiniProfile(chattingPanel,chattingListHeight, chattingListIndex);
		/*******************/
		
		
		chattingButtonList.add(chattingPanel);
		chattingListPanel.setBounds(67, 50, 317, chattingListHeight);
		chattingListPanel.add(chattingButtonList.get(++chattingListIndex));
		chattingListHeight += 71;
		
		chattingPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("clicked ["+chatName+"]");
				Chatting chatting = new Chatting(chatName,e.getX(),e.getY());
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
	
}
