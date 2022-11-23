package kakao_Chat;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;

import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.plaf.basic.BasicMenuBarUI;

import kakao_Chat.design.friendslist_drawLine.BottomDrawPanel;
import kakao_Chat.design.mini_profile.MiniProfileManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ChattingListGUI extends JFrame{
	int chattingListHeight = 71; // 채팅방 버튼을 담는 list의 크기
	static int chattingListIndex = -1; //채팅방이 생성될 때마다 +1
	private MiniProfileManager miniProfileManager; //미니 프로필 디자인 동적 선택 생성 매니저
	private JPanel chatPanel;
	JButton moveFriendsList;
	JButton moveChattingList;
	FriendsListGUI friendsListGUI = new FriendsListGUI();
	
	public ChattingListGUI(String name) {
		ArrayList<JPanel> chattingButtonList = new ArrayList<JPanel>();
		setBackground(new Color(255, 255, 255));
		setBounds(900, 100, 400, 690);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
//		JPanel friends = friendsListGUI.get();
		
		chatPanel = new JPanel();
		chatPanel.setBackground(Color.WHITE);
		chatPanel.setBounds(67, 0, 317, 650);
		
		JPanel chattingListPanel = new JPanel();
		chattingListPanel.setBackground(Color.WHITE);
		chattingListPanel.setBounds(67, 50, 317, 590);
		chattingListPanel.setLayout(new FlowLayout());
		JScrollPane pane = new JScrollPane (chattingListPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBounds(0, 61, 317, 589);
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
		
		JMenu chattingLableAndMenu = new JMenu("채팅");
		chattingLableAndMenu.setBackground(new Color(255, 255, 255));
		chattingLableAndMenu.setBorderPainted(false);
		chattingLableAndMenu.setFocusable(false);
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
		creatChatting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(chattingListIndex < 20) {
					createChattingRoom(chattingButtonList,chattingListPanel); //chatingRoom 생성
					//chattingListPanel.setSize(317,chattingListHeight);
					chattingListPanel.setPreferredSize(new Dimension(317,chattingListHeight));
					revalidate();
					repaint();
				}else {
					System.out.println("최대 방 개수를 초과했습니다.");
				}
			}
		});
		creatChatting.setBounds(279, 30, 25, 23);
		topBar.add(creatChatting);
	
		
		
		JPanel sideMenuPane = new JPanel();
		sideMenuPane.setBackground(new Color(236, 236, 237));
		sideMenuPane.setBounds(0, 0, 67, 651);
		getContentPane().add(sideMenuPane);
		sideMenuPane.setLayout(null);
		
		moveFriendsList = new JButton("");
		Image f = new ImageIcon("img/side_btn_1_1.png").getImage().getScaledInstance(22,22,Image.SCALE_DEFAULT);
		ImageIcon f_button = new ImageIcon(f);
		moveFriendsList.setBackground(new Color(236, 236, 237));
		moveFriendsList.setIcon(f_button);
		moveFriendsList.setBorderPainted(false);
		moveFriendsList.setFocusPainted(false);
		moveFriendsList.setBounds(22, 35, 22, 22);
		moveFriendsList.setBorderPainted(false);
		sideMenuPane.add(moveFriendsList);

		moveChattingList = new JButton("");
		Image c = new ImageIcon("img/side_btn_2_2.png").getImage().getScaledInstance(22,22,Image.SCALE_DEFAULT);
		ImageIcon c_button = new ImageIcon(c);
		moveChattingList.setBackground(new Color(236, 236, 237));
		moveChattingList.setIcon(c_button);
		moveChattingList.setBounds(22, 95, 22, 22);
		moveChattingList.setBorderPainted(false);
		sideMenuPane.add(moveChattingList);
		
		JButton moveMore = new JButton("");
		Image m = new ImageIcon("img/side_btn_3_2.png").getImage().getScaledInstance(22,6,Image.SCALE_DEFAULT);
		ImageIcon m_button = new ImageIcon(m);
		moveMore.setBackground(new Color(236, 236, 237));
		moveMore.setIcon(m_button);
		moveMore.setBorderPainted(false);
		moveMore.setBounds(22, 155, 22, 7);
		sideMenuPane.add(moveMore);
		
		JPanel friendPanel = friendsListGUI.get();
		friendPanel.setBounds(67, 0, 317, 650);
		getContentPane().add(friendPanel);
		friendPanel.setLayout(null);
		friendPanel.setVisible(true);
	
		
		/* 채팅방 사이드바 이벤트 */
		
		moveFriendsList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chatPanel.setVisible(false);
				friendPanel.setVisible(true);
				Image f = new ImageIcon("img/side_btn_1_1.png").getImage().getScaledInstance(22,22,Image.SCALE_DEFAULT);
				ImageIcon f_button = new ImageIcon(f);
				moveFriendsList.setIcon(f_button);
				
				Image c = new ImageIcon("img/side_btn_2_2.png").getImage().getScaledInstance(22,22,Image.SCALE_DEFAULT);
				ImageIcon c_button = new ImageIcon(c);
				moveChattingList.setIcon(c_button);
			}
		});
		moveChattingList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chatPanel.setVisible(true);
				friendPanel.setVisible(false);
				Image f = new ImageIcon("img/side_btn_1_2.png").getImage().getScaledInstance(22,22,Image.SCALE_DEFAULT);
				ImageIcon f_button = new ImageIcon(f);
				moveFriendsList.setIcon(f_button);
				
				Image c = new ImageIcon("img/side_btn_2_1.png").getImage().getScaledInstance(22,22,Image.SCALE_DEFAULT);
				ImageIcon c_button = new ImageIcon(c);
				moveChattingList.setIcon(c_button);
			}
		});
		
		
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
		
		chatPanel.setVisible(false);
	}
	
	private void createChattingRoom(ArrayList<JPanel> chattingButtonList,JPanel chattingListPanel) {
		
		JPanel chattingPanel = new JPanel();
		chattingPanel.setAutoscrolls(true);
		chattingPanel.setBounds(67, 50, 317, chattingListHeight);
		chattingPanel.setBackground(Color.white);
		chattingPanel.setLayout(null);
		chattingPanel.setPreferredSize(new Dimension(317,71));
		
		/**** 채팅방 생성 ****/
		int random = (int) ((Math.random() * (6 - 2)) + 2); //Random한 DummyData 생성
		int DUMMY_NumberOfPeople = random;
		
		miniProfileManager = MiniProfileManager.getInstance();
		miniProfileManager.setMiniProfileDesign_Chat( DUMMY_NumberOfPeople );
		String chatName = miniProfileManager.makeMiniProfile(chattingPanel,chattingListHeight, chattingListIndex);
		/*******************/
		
		
		chattingButtonList.add(chattingPanel);
		chattingListPanel.setBounds(0, 0, 317, chattingListHeight);
		chattingListPanel.add(chattingButtonList.get(++chattingListIndex));
		chattingListHeight += 71;
		
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
			@Override//마우스가 버튼 밖으로 나가면 노란색으로 바뀜
		    public void mouseExited(MouseEvent e) {
				JPanel b = (JPanel)e.getSource();
		        b.setBackground(Color.white);
		    }
			});
	}
}
