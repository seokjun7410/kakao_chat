package kakao_Chat;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import java.awt.Graphics2D;

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
import java.awt.event.MouseMotionListener;
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
import java.awt.Point;
import java.awt.ScrollPane;

import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.plaf.basic.BasicMenuBarUI;

import kakao_Chat.addFriendGUI.TranslucentLabel;
import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.friendslist_drawLine.BottomDrawPanel;
import kakao_Chat.design.mini_profile.MiniProfileManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class addChattingGUI extends JFrame implements MouseListener,MouseMotionListener,ActionListener{
	int chattingListHeight = 71; // 채팅방 버튼을 담는 list의 크기
	static int chattingListIndex = -1; //채팅방이 생성될 때마다 +1
	private MiniProfileManager miniProfileManager; //미니 프로필 디자인 동적 선택 생성 매니저
	private JPanel chatPanel;
	private JTextField textField;
	private TranslucentLabel title_bar;
	private JButton btn_exit;
	private Point comPoint;
	private JPanel menu_bar;
	private JLabel empty_menu_bar[];
	private boolean editFrist;
	public JPanel get() {
		return chatPanel;
	}
	private Socket socket;
	private String currentName;

	public addChattingGUI(Socket s, String name) {
		this.socket = s;
		editFrist = true;
		currentName = name;
		setResizable(false);
		setUndecorated(true);		
//		setTitle("");
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		//상단바 구성
		title_bar = new TranslucentLabel();
		title_bar.setPreferredSize(new Dimension(380,34));
		title_bar.setBounds(0,0,365,35);
		title_bar.setLayout(null);
		title_bar.setBackground(new Color(0, 0, 0, 0));
		title_bar.addMouseListener(this);
		title_bar.addMouseMotionListener(this);
		
		//상단바 X버튼
		btn_exit = new JButton("x");
		btn_exit.setFont(new Font("Arial", Font.PLAIN, 18));
		btn_exit.setBackground(Color.white);
		btn_exit.setBorderPainted(false);
		btn_exit.setBounds(320,2, 45, 30);
		btn_exit.setPreferredSize(new Dimension(30,30));
		btn_exit.addActionListener(this);
		title_bar.add(btn_exit);
		add(title_bar,BorderLayout.NORTH);
		ArrayList<JPanel> chattingButtonList = new ArrayList<JPanel>();
		setBackground(new Color(255, 255, 255));
		setBounds(400, 200, 365, 690);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		

		chatPanel = new JPanel();
		chatPanel.setBackground(Color.WHITE);
		chatPanel.setBounds(0, 0, 384, 650);
		
		JPanel chattingListPanel = new JPanel();
		chattingListPanel.setBackground(Color.WHITE);
		chattingListPanel.setBounds(67, 50, 317, 590);
		chattingListPanel.setLayout(new FlowLayout());
		JScrollPane pane = new JScrollPane (chattingListPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBounds(0, 148, 355, 442);
		pane.setPreferredSize(new Dimension(317,590));
		pane.getVerticalScrollBar().setUnitIncrement(14);
		chatPanel.setLayout(null);
		pane.setBorder(null);
		chatPanel.add(pane);
		getContentPane().add(chatPanel);
		Image af = new ImageIcon("img/add_friend.png").getImage().getScaledInstance(28,22,Image.SCALE_DEFAULT);
		ImageIcon btn_af = new ImageIcon(af);
		
		JPanel topBar = new JPanel();
		topBar.setLayout(null);
		topBar.setBackground(Color.WHITE);
		topBar.setBounds(0, 0, 355, 116);
		chatPanel.add(topBar);
		
		JLabel lblNewLabel = new JLabel("대화상대 선택");
		lblNewLabel.setForeground(new Color(55, 55, 55));
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 17));
		lblNewLabel.setBounds(12, 28, 112, 20);
		topBar.add(lblNewLabel);
		
		JPanel search = new JPanel(){
			Image background=new ImageIcon("img/addChatting.png").getImage();
			public void paint(Graphics g) {//그리는 함수
					g.drawImage(background, 0, 0, null);//background를 그려줌		
			}
		};
		search.setLayout(null);
		search.setBackground(Color.WHITE);
		search.setBounds(0, 69, 355, 66);
		topBar.add(search);
		
		textField = new JTextField() {
			public void setBorder(Border border) {
			}
		};
		textField.setColumns(10);
		textField.setBounds(46, 18, 276, 21);
		search.add(textField);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 112, 355, 38);
		chatPanel.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("친구");
		lblNewLabel_1.setBounds(16, 13, 57, 15);
		panel.add(lblNewLabel_1);
		
		JButton creatChatting = new JButton("");
		creatChatting.setBounds(300, 13, 25, 23);
		panel.add(creatChatting);
		creatChatting.setIcon(new ImageIcon("img/creatChat.PNG"));
		creatChatting.setBorderPainted(false);
		
		JPanel bottmBarPanel = new BottomDrawPanel(0,5,350,5);
		bottmBarPanel.setLayout(null);
		bottmBarPanel.setBackground(Color.WHITE);
		bottmBarPanel.setBounds(0, 588, 355, 62);
		chatPanel.add(bottmBarPanel);
		
		JButton accpet = new JButton("확인");
		accpet.setBounds(173, 20, 79, 34);
		bottmBarPanel.add(accpet);
		
		JButton cancle = new JButton("취소");
		cancle.setBounds(264, 20, 79, 34);
		bottmBarPanel.add(cancle);
		creatChatting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(chattingListIndex < 20) {
					//createChattingRoom(chattingButtonList,chattingListPanel); //chatingRoom 생성
					//chattingListPanel.setSize(317,chattingListHeight);
					chattingListPanel.setPreferredSize(new Dimension(317,chattingListHeight));
					
					revalidate();
					repaint();
				}else {
					System.out.println("최대 방 개수를 초과했습니다.");
				}
			}
		});
		setVisible(true);
		
	}
	
//	private void createChattingRoom(ArrayList<JPanel> chattingButtonList,JPanel chattingListPanel) {
//		
//		JPanel chattingPanel = new JPanel();
//		chattingPanel.setAutoscrolls(true);
//		chattingPanel.setBounds(67, 50, 317, chattingListHeight);
//		chattingPanel.setBackground(Color.white);
//		chattingPanel.setLayout(null);
//		chattingPanel.setPreferredSize(new Dimension(317,55));
//		
//		/**** 친구목록 생성 ****/
//		int random = (int) ((Math.random() * (6 - 2)) + 2); //Random한 DummyData 생성
//		int DUMMY_NumberOfPeople = random;
//		
//		miniProfileManager = MiniProfileManager.getInstance();
//		miniProfileManager.setMiniProfileDesign_Friend("img/default45.png");
//		String chatName = miniProfileManager.makeMiniProfile(chattingPanel,chattingListHeight, chattingListIndex,"img/default45.png");
//		/*******************/
//		
//		//우측 체크박스 add
//		JCheckBox chckbxNewCheckBox = new JCheckBox("");
//		chckbxNewCheckBox.setBounds(260, 10, 40, 30);
//		chckbxNewCheckBox.setBackground(Color.white);
//		chckbxNewCheckBox.setIcon(new ImageIcon("img/checkBox.png"));
//		chckbxNewCheckBox.setSelectedIcon(new ImageIcon("img/checkBox_checked.PNG"));
//		chattingPanel.add(chckbxNewCheckBox);
//		
//		
//		chattingButtonList.add(chattingPanel);
//		chattingListPanel.setBounds(0, 0, 317, chattingListHeight);
//		chattingListPanel.add(chattingButtonList.get(++chattingListIndex));
//		chattingListHeight += 71;
//		
//		chattingPanel.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				System.out.println("clicked ["+chatName+"]");
//				Profile_Frame profile_Frame = new Profile_Frame(chatName,socket,currentName);
//				}
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				JPanel b = (JPanel)e.getSource();
//		        b.setBackground(new Color(245,245,245));
//			}
//			@Override//마우스가 버튼 밖으로 나가면 노란색으로 바뀜
//		    public void mouseExited(MouseEvent e) {
//				JPanel b = (JPanel)e.getSource();
//		        b.setBackground(Color.white);
//		    }
//			});
//	}

	
	public class TranslucentLabel extends JLabel {
	       public TranslucentLabel() {
	            super();
	        }
	        @Override
	        public boolean isOpaque() {
	            return false;
	        }

	        @Override
	        protected void paintComponent(Graphics g) {
	            Graphics2D g2d = (Graphics2D) g.create();
	            g2d.setColor(getBackground());
	            g2d.fillRect(0, 0, getWidth(), getHeight());
	            super.paintComponent(g2d);
	            g2d.dispose();
	        }
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btn_exit)) {
			this.dispose();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}

	public void mousePressed(MouseEvent e) 
	{ 
		if(e.getSource().equals(title_bar)) {
			comPoint = e.getPoint();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if(e.getSource().equals(title_bar)) {
			comPoint = null;
		}
	}

	public void mouseDragged(MouseEvent e) 
	{
		Point current = e.getLocationOnScreen();
		this.setLocation(current.x - comPoint.x,current.y - comPoint.y);
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}
}
