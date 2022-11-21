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
import java.awt.event.MouseListener;
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

import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.friendslist_drawLine.BottomDrawPanel;
import kakao_Chat.design.mini_profile.MiniProfileManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FriendsListGUI extends JFrame implements MouseListener{
	int chattingListHeight = 71; // 채팅방 버튼을 담는 list의 크기
	static int chattingListIndex = -1; //채팅방이 생성될 때마다 +1
	private MiniProfileManager miniProfileManager; //미니 프로필 디자인 동적 선택 생성 매니저
	private JPanel chatPanel;
	private JLabel profileButton;
	public JPanel get() {
		return chatPanel;
	}
	
	public FriendsListGUI() {
		ArrayList<JPanel> chattingButtonList = new ArrayList<JPanel>();
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 400, 690);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		

		chatPanel = new JPanel();
		chatPanel.setBackground(Color.WHITE);
		chatPanel.setBounds(67, 0, 317, 650);
		
		JPanel chattingListPanel = new JPanel();
		chattingListPanel.setBackground(Color.WHITE);
		chattingListPanel.setBounds(67, 50, 317, 590);
		chattingListPanel.setLayout(new FlowLayout());
		JScrollPane pane = new JScrollPane (chattingListPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
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
		
		JButton addFriend = new JButton("");
		Image af = new ImageIcon("img/add_friend.png").getImage().getScaledInstance(28,22,Image.SCALE_DEFAULT);
		ImageIcon btn_af = new ImageIcon(af);
		addFriend.setIcon(btn_af);
		addFriend.setBackground(new Color(255, 255, 255));
		addFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new addFriendGUI();
			}
		});
		addFriend.setBorderPainted(false);
		addFriend.setBounds(237, 29, 31, 25);
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
		profileButton = new JLabel(new ImageIcon("img/defaultProfile80.png")); //본인 프로필 이미지
		profileButton.setBounds(13, 8, 61, 61);
		profileButton.setBackground(Color.WHITE);
		profileButton.addMouseListener(this);
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
		chattingPanel.setAutoscrolls(true);
		chattingPanel.setBounds(67, 50, 317, chattingListHeight);
		chattingPanel.setBackground(Color.white);
		chattingPanel.setLayout(null);
		chattingPanel.setPreferredSize(new Dimension(317,55));
		
		/**** 채팅방 생성 ****/
		int random = (int) ((Math.random() * (6 - 2)) + 2); //Random한 DummyData 생성
		int DUMMY_NumberOfPeople = random;
		
		miniProfileManager = MiniProfileManager.getInstance();
		miniProfileManager.setMiniProfileDesign_Friend();
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
				Profile_Frame profile_Frame = new Profile_Frame();
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("clicked ["+chatName+"]");
		if (e.getSource().equals(profileButton)) {
		Profile_Frame profile_Frame = new Profile_Frame();
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
