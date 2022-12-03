package kakao_Chat;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kakao_Chat.design.friendslist_drawLine.BottomDoubleDrawPanel;
import kakao_Chat.design.friendslist_drawLine.BottomLineJTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class addFriendGUI extends Frame implements MouseListener, MouseMotionListener,ActionListener{
	private TranslucentLabel title_bar;
	private JButton btn_exit;
	private Point comPoint;
	private JPanel menu_bar;
	private JLabel empty_menu_bar[];
	private JTextField input_id;
	JButton btn_addFriend;
	private boolean editFrist;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	private static final  int BUF_LEN = 128;
	public static String userName;
	public static ArrayList<User> User_list;
	public int Size_list;
	private JLabel lb_addFriend;
	private addFriendGUI thisGUI = this;
	private FriendsListGUI friendsListGUI;
	public addFriendGUI(Socket s, FriendsListGUI gui)
	{
		this.socket = s;
		friendsListGUI = gui;
		editFrist = true;
		
		setResizable(false);
		setUndecorated(true);		
//		setTitle("");

		setLocationRelativeTo(null);
		
		
		JPanel background_panel = new JPanel();
		background_panel.setBackground(new Color(255, 255, 255));
		background_panel.setLayout(null);
		Image bg_img = new ImageIcon("img/default_back.jpg").getImage().getScaledInstance(380,650,Image.SCALE_DEFAULT);
		ImageIcon bg = new ImageIcon(bg_img);
		add(background_panel,BorderLayout.CENTER);
		
		//상단바 구성
		title_bar = new TranslucentLabel();
		title_bar.setPreferredSize(new Dimension(380,34));
		title_bar.setBounds(0,0,300,35);
		title_bar.setLayout(null);
		title_bar.setBackground(new Color(0, 0, 0, 0));
		title_bar.addMouseListener(this);
		title_bar.addMouseMotionListener(this);
		
		//상단바 X버튼
		btn_exit = new JButton("x");
		btn_exit.setBounds(255,2, 45, 30);
		btn_exit.setPreferredSize(new Dimension(30,30));
		btn_exit.addActionListener(this);
		title_bar.add(btn_exit);
		
		background_panel.add(title_bar,BorderLayout.NORTH);
		
		JPanel panel = new BottomDoubleDrawPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 10, 300, 95);
		background_panel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("이름으로 추가");
		lblNewLabel_1.setBounds(12, 65, 93, 20);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 12));
		
		lb_addFriend = new JLabel("친구 추가");
		lb_addFriend.setBounds(12, 23, 69, 20);
		panel.add(lb_addFriend);
		lb_addFriend.setFont(new Font("굴림", Font.PLAIN, 17));


		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 103, 300, 297);
		background_panel.add(panel_1);
		panel_1.setLayout(null);
		
		input_id = new BottomLineJTextField(){
			public void setBorder(Border bodrder){
				
			}
		};
		input_id.setText("친구 이름");
		input_id.setBounds(12, 41, 276, 34);
		panel_1.add(input_id);
		input_id.setColumns(10);
		input_id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(editFrist == true){
					editFrist = false;
					input_id.setText("");
				}
				}
			});
		
		btn_addFriend = new JButton("친구 추가");
		btn_addFriend.setBounds(195, 253, 93, 34);
		panel_1.add(btn_addFriend);
		btn_addFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("친구추가 버튼이 눌렸습니다.");
			try {
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);

				String id = input_id.getText();



//				ListenNetwork net = new ListenNetwork();
//				net.start();

				SendMessage("/200 " + id);
				dispose();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
			}
		});
	
		setBounds(100, 100, 300, 400);
		setVisible(true);
				
	}
	
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
//		else if(e.getSource().equals(btn_addFriend)){
//			System.out.println("hello");
//			try {
//				is = socket.getInputStream();
//				dis = new DataInputStream(is);
//				os = socket.getOutputStream();
//				dos = new DataOutputStream(os);
//
//				String id = input_id.getText();
//
//				SendMessage("/200 " + id);
//
//				ListenNetwork net = new ListenNetwork();
//			} catch (IOException ex) {
//				throw new RuntimeException(ex);
//			}
//		}
		
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

	class ListenNetwork extends Thread {
		public void run() {
			while (true) {
				try {
					// String msg = dis.readUTF();
					byte[] b = new byte[BUF_LEN];
					int ret;
					ret = dis.read(b);
					if (ret < 0) {
						//AppendText("dis.read() < 0 error");
						try {
							dos.close();
							dis.close();
							socket.close();
							break;
						} catch (Exception ee) {
							break;
						}// catch�� ��
					}
					String	msg = new String(b, "euc-kr");
					msg = msg.trim(); // �յ� blank NULL, \n ��� ����

					String[] args = msg.split(" ");
					System.out.println("afmsg:"+ msg); // server ȭ�鿡 ���
					System.out.println("args "+args[0]);

					if(args.length >1) {
//						if(args[1].equals("/101")) {
//							userName = args[0];
////							window = new ChattingListGUI(socket,args[0]);
////							window.setVisible(true);
//							setVisible(false);
//						}
						if (args[0].equals("/201")) {
							String id = args[1];
							String userProfile = args[2];
							System.out.println(id+" "+userProfile);

							friendsListGUI.addFriend(id,userProfile);

							thisGUI.dispose();
						}
						else if(args[0].equals("/202")){
							System.out.println("해당 id를 가진 USER가 없습니다.");
						}
					}
				} catch (IOException e) {
					//AppendText("dis.read() error");
					try {
						dos.close();
						dis.close();
						socket.close();
						break;
					} catch (Exception ee) {
						break;
					} // catch�� ��
				} // �ٱ� catch����

			}
		}
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
