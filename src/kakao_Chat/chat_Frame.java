package kakao_Chat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class chat_Frame extends JFrame implements MouseListener, MouseMotionListener,ActionListener, KeyListener
{	
	private Point initialClick;
	private JPanel title_bar;
	private JPanel Panel_1;
	private JLabel chat_text;
	private JButton btn_exit,btn_send;
	private Point comPoint;
	private int chat_number;
	private String user_names;
	private JTextArea text_area;
	private JPanel chat_panel;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	private static final int BUF_LEN = 128;
	public static String userName;
	public static ArrayList<User> User_list;
	public int Size_list;
	public chat_Frame(int chat_num, String un, Socket s)  {
		this.socket = s;

		try {
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}


		chat_number = chat_num;
		user_names = un;
//		System.out.println("un = " + un);
//		System.out.println("user_names = " + user_names);
//		System.out.println("chat_number = " + chat_number);
		setResizable(false);
		setUndecorated(true);		
//		setTitle("");
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);


		
		//상단바 구성
		title_bar = new JPanel();
		title_bar.setPreferredSize(new Dimension(380,34));
		title_bar.setLayout(null);
		title_bar.setBackground(new Color(186,206,224));
		title_bar.addMouseListener(this);
		title_bar.addMouseMotionListener(this);
		
		//상단바 X버튼
		btn_exit = new JButton("x");
		btn_exit.setBounds(338,2, 40, 30);
		btn_exit.setPreferredSize(new Dimension(30,30));
		btn_exit.addActionListener(this);
		title_bar.add(btn_exit);
		
		//상단바를 제외한 부분 패널
		Panel_1 = new JPanel();
		Panel_1.setPreferredSize(new Dimension(380,30));
		Panel_1.setLayout(null);
		Panel_1.setBackground(new Color(186,206,224));
		Panel_1.setLayout(new BorderLayout());
		
		//상단 프로필 패널
		JPanel Profile_panel = new JPanel();
		Profile_panel.setPreferredSize(new Dimension(380,50));
		Profile_panel.setLayout(new BorderLayout());
		Profile_panel.setBackground(new Color(186,206,224));
			//유저 이미지
		ImageIcon user_icon = new ImageIcon("img/user2.png");
		Image user = user_icon.getImage().getScaledInstance(45,45,Image.SCALE_DEFAULT);
		ImageIcon user_icon2 = new ImageIcon(user);
		JLabel img = new JLabel(user_icon);
		Profile_panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		Profile_panel.add(img,BorderLayout.WEST);
			//유저 이름, 채팅 명수 표시 패널
		JPanel user_info = new JPanel();
		user_info.setPreferredSize(new Dimension(380,50));
		user_info.setLayout(new BorderLayout());
		user_info.setBorder(BorderFactory.createEmptyBorder(0,15,0,0));
		Profile_panel.add(user_info,BorderLayout.CENTER);
				//유저 이름 패널
		JPanel user_name = new JPanel();
		user_name.setPreferredSize(new Dimension(380,20));
		user_name.setBackground(new Color(186,206,224));
		user_name.setLayout(new BorderLayout());
		JLabel name = new JLabel(user_names);
		name.setFont(new Font("맑은 고딕",Font.BOLD,14));
		user_name.add(name,BorderLayout.WEST);
		
				//인원 수 패널
		JPanel user_num = new JPanel();
		user_num.setPreferredSize(new Dimension(380,20));
		user_num.setBackground(new Color(186,206,224));
		user_num.setLayout(new BorderLayout());
		JLabel num = new JLabel(String.valueOf(chat_number));
		num.setBorder(BorderFactory.createEmptyBorder(0,3,0,0));
		user_num.add(num,BorderLayout.CENTER);
		ImageIcon user_icon_s = new ImageIcon("img/user_s.png");
		Image user_s = user_icon_s.getImage().getScaledInstance(10,10,Image.SCALE_DEFAULT);
		ImageIcon user_icon_s2 = new ImageIcon(user_s);
		JLabel img_2 = new JLabel(user_icon_s2);
		user_num.add(img_2,BorderLayout.WEST);
		
		user_info.setBackground(new Color(186,206,224));
		user_info.add(user_name,BorderLayout.NORTH);
		user_info.add(user_num,BorderLayout.CENTER);
		
		
		Panel_1.add(Profile_panel,BorderLayout.NORTH);
		
		//중간 채팅창 패널
		chat_panel = new JPanel();
		chat_panel.setPreferredSize(new Dimension(380,70));
		chat_panel.setBackground(new Color(186,206,224));
		chat_panel.setLayout(new FlowLayout());
		chat_panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,10));
	

		//JScrollPane scrollPane = new JScrollPane(chat_view, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		//scrollPane.setBounds(12, 10, 352, 340);
		//chat_panel.add(scrollPane,BorderLayout.SOUTH);
//		chat_text = new JLabel("test");
//		chat_text.setPreferredSize(new Dimension(100,300));
//		chat_text.setBackground(new Color(255,235,51));
//		chat_text.setOpaque(true);
//		chat_panel.add(chat_text);
		
		Panel_1.add(chat_panel,BorderLayout.CENTER);
		
		//하단 텍스트 패널
		JPanel text_panel = new JPanel();
		text_panel.setPreferredSize(new Dimension(380,150));
		text_panel.setLayout(new BorderLayout());
		text_area = new JTextArea(7,13);
		text_area.addKeyListener(this);
		text_area.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		text_area.setLineWrap(true);
		text_panel.add(text_area,BorderLayout.CENTER);
		Panel_1.add(text_panel,BorderLayout.SOUTH);
		
		//최하단 전송, 이모티콘 패널
			//채팅 내용 입력 패널
		JPanel send_panel = new JPanel();
		send_panel.setPreferredSize(new Dimension(380,50));
		send_panel.setLayout(new BorderLayout());
		ImageIcon send_img = new ImageIcon("img/btn_send.png");
		Image send_img2 = send_img.getImage().getScaledInstance(50,30,Image.SCALE_DEFAULT);
		ImageIcon send_img3 = new ImageIcon(send_img2);
		btn_send = new JButton(send_img3);
		btn_send.setBorderPainted(false); 
		btn_send.setPreferredSize(new Dimension(50, 30)); 
		btn_send.addActionListener(this);
		send_panel.add(btn_send,BorderLayout.EAST);		
		text_panel.add(send_panel,BorderLayout.SOUTH);
		

		add(title_bar,BorderLayout.NORTH);
		add(Panel_1,BorderLayout.CENTER);
		
		setBounds(510, 130, 380, 635);
		setVisible(true);
	}
		
	
	public void makeLeftBubble (String value) {
		if(!value.equals("")) {
			JPanel chat_view = new JPanel();
			chat_view.setLayout(new FlowLayout(FlowLayout.LEFT));
			chat_view.setBackground(new Color(186,206,224));
			chat_view.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
			JLabel label = new JLabel(value);
			label.setFont(new Font("나눔고딕", Font.PLAIN, 12));
			label.setBorder(BorderFactory.createEmptyBorder(0,11,0,0));
			LeftArrowBubble LeftArrowBubble = new LeftArrowBubble();
			int width =(int) label.getPreferredSize().getWidth()+15;		
			int height = 27;
			if(width > 208) {
				width = 208;
				height = (int)((width /208) +1)*27;
			}
			chat_view.setPreferredSize(new Dimension(380,height+5));
			LeftArrowBubble.setPreferredSize(new Dimension(width,height));
			LeftArrowBubble.setBackground(new Color(255,255,255));	
			System.out.println(label.getPreferredSize()+""+height);
			LeftArrowBubble.add(label);
			chat_view.add(LeftArrowBubble);
			chat_panel.add(chat_view);
			revalidate();
		}
	}
	
		public void makeRightBubble (String value) {
			if(!value.equals("")) {
				JPanel chat_view = new JPanel();
				chat_view.setLayout(new FlowLayout(FlowLayout.RIGHT));
				chat_view.setBackground(new Color(186,206,224));
				chat_view.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
				JLabel label = new JLabel(value);
				label.setFont(new Font("나눔고딕", Font.PLAIN, 12));
				label.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
				RightArrowBubble RightArrowBubble = new RightArrowBubble();
				int width =(int) label.getPreferredSize().getWidth()+15;		
				int height = 27;
				if(width > 208) {
					width = 208;
					height = (int)((width /208) +1)*27;
				}
				chat_view.setPreferredSize(new Dimension(380,height+5));
				RightArrowBubble.setPreferredSize(new Dimension(width,height));
				RightArrowBubble.setBackground(new Color(255,235,51));	
				System.out.print(label.getPreferredSize()+""+height);
				RightArrowBubble.add(label);
				chat_view.add(RightArrowBubble);
				chat_panel.add(chat_view);
				revalidate();
			}
		}
		

		public class LeftArrowBubble extends JPanel {
			   private int radius = 10;
			   private int arrowSize = 6;
			   private int strokeThickness = 3;
			   private int padding = strokeThickness / 2;
			   @Override
			   protected void paintComponent(final Graphics g) {
			      final Graphics2D g2d = (Graphics2D) g;
			      g2d.setColor(new Color(255,255,255));
			      int x = padding + strokeThickness + arrowSize;
			      int width = getWidth() - arrowSize - (strokeThickness * 2);
			      int bottomLineY = getHeight() - strokeThickness;
			      g2d.fillRect(x, padding, width, bottomLineY);
			      g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,   RenderingHints.VALUE_ANTIALIAS_ON));
			      g2d.setStroke(new BasicStroke(strokeThickness));
			      RoundRectangle2D.Double rect = new RoundRectangle2D.Double(x, padding, width, bottomLineY, radius, radius);
			      Polygon arrow = new Polygon();
			      arrow.addPoint(20, 8);
			      arrow.addPoint(0, 10);
			      arrow.addPoint(20, 12);
			      Area area = new Area(rect);
			      area.add(new Area(arrow));
			      g2d.draw(area);
			   }
			}
		
		public class RightArrowBubble extends JPanel {
			   private int strokeThickness = 3;
			   private int radius = 10;
			   private int arrowSize = 6;
			   private int padding = strokeThickness / 2;
			   @Override
			   protected void paintComponent(final Graphics g) {
			      final Graphics2D g2d = (Graphics2D) g;
			      g2d.setColor(new Color(255,235,51));
			      int bottomLineY = getHeight() - strokeThickness;
			      int width = getWidth() - arrowSize - (strokeThickness * 2);
			      g2d.fillRect(padding, padding, width, bottomLineY);
			      RoundRectangle2D.Double rect = new RoundRectangle2D.Double(padding, padding, width, bottomLineY,  radius, radius);
			      Polygon arrow = new Polygon();
			      arrow.addPoint(width, 8);
			      arrow.addPoint(width + arrowSize, 10);
			      arrow.addPoint(width, 12);
			      Area area = new Area(rect);
			      area.add(new Area(arrow));
			      g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
			      g2d.setStroke(new BasicStroke(strokeThickness));
			      g2d.draw(area);
			   }
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
		public void mouseMoved(MouseEvent e) {
			// TODO 자동 생성된 메소드 스텁
			
		}



		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO 자동 생성된 메소드 스텁
			
		}



		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO 자동 생성된 메소드 스텁
			
		}



		@Override
		public void mouseExited(MouseEvent e) {
			// TODO 자동 생성된 메소드 스텁
			
		}



		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btn_exit)) {
				this.dispose();
			}
			else if(e.getSource().equals(btn_send)) {
				String value = text_area.getText();
				text_area.setText(""); // �޼����� ������ ���� �޼��� ����â�� ����.
				text_area.requestFocus(); // �޼����� ������ Ŀ���� �ٽ� �ؽ�Ʈ �ʵ�� ��ġ��Ų��
				makeRightBubble(value);

				SendMessage("/500 "+user_names+" "+value);
				System.out.println("LOG.User_names"+user_names+" "+value);

				// same value as
//				text_area.setCaretPosition(len); // place caret at the end (with no selection)
//		 		textArea.replaceSelection(msg + "\n");
			}
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO 자동 생성된 메소드 스텁
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			  if(e.getKeyCode() == KeyEvent.VK_ENTER){
				    e.consume();
				    btn_send.doClick();
			  }
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO 자동 생성된 메소드 스텁
			
			
		}

	public void SendMessage(String msg) {
		try {
			// dos.writeUTF(msg);
			byte[] bb;
			bb = MakePacket(msg);
			dos.write(bb, 0, bb.length);
		} catch (IOException e) {
			//AppendText("dos.write() error");
			try {
				dos.close();
				dis.close();
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(0);
			}
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
			System.exit(0);
		}
		for (i = 0; i < bb.length; i++)
			packet[i] = bb[i];
		return packet;
	}
	
}