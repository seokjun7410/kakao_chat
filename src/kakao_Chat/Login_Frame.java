package kakao_Chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login_Frame extends JFrame implements MouseListener, MouseMotionListener, ActionListener, KeyListener {
    private JPanel title_bar;
    private JButton btn_exit, btn_login;
    private Point comPoint;
    private JTextField id_text;
    private JPasswordField pw_text;
    private JTextArea id_area, pw_area;
    ChattingListGUI window;
    private static Socket socket;
    private static InputStream is;
    private static OutputStream os;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    private static final int BUF_LEN = 128;
    public static String userName;
    public static ArrayList<User> User_list;
    public static Vector Room_list = new Vector();
    public int Size_list;
    private ChattingListGUI chattingListGUI;
    private FriendsListGUI friendsListGUI;
    private addFriendGUI addFriendGUI;

    public Login_Frame() {

        User_list = new ArrayList<User>();
        Size_list = 0;
        setResizable(false);
        setUndecorated(true);
//		setTitle("");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setBounds(900, 100, 380, 635);
        setVisible(true);


        Image k = new ImageIcon("img/defaultProfile.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        //ImageIcon k_img = new ImageIcon(k);
        ImageIcon k_img = new ImageIcon("img/kakao_talk.png");
        JLabel k_label = new JLabel(k_img);
        k_label.setBounds(0, 50, 380, 150);

        JPanel id_panel = new JPanel();
        id_area = new JTextArea();
        id_area.setText(" 아이디  ");
        id_area.setEditable(false);
        id_area.setBounds(80, 100, 380, 26);
        id_area.setBackground(new Color(254, 229, 0));
        id_panel.setPreferredSize(new Dimension(380, 40));
        id_text = new JTextField();
        id_text.setEditable(true);
        id_text.setBounds(80, 100, 380, 26);
        id_text.setPreferredSize(new Dimension(380, 30));
        id_text.setColumns(17);
        id_panel.add(id_area);
        id_panel.add(id_text);
        id_panel.setBackground(new Color(254, 229, 0));


        JPanel pw_panel = new JPanel();
        pw_area = new JTextArea();
        pw_area.setText("비밀번호");
        pw_area.setEditable(false);
        pw_area.setBackground(new Color(254, 229, 0));
        pw_panel.setPreferredSize(new Dimension(380, 40));
        pw_text = new JPasswordField();
        pw_text.setEditable(true);

        pw_text.setBounds(100, 100, 380, 26);
        pw_text.setPreferredSize(new Dimension(380, 30));
        pw_text.setColumns(17);
        pw_panel.add(pw_area);
        pw_panel.add(pw_text);
        pw_panel.setBackground(new Color(254, 229, 0));

        JPanel user_panel = new JPanel();
        user_panel.setBounds(-18, 220, 380, 100);
        user_panel.setBackground(new Color(254, 229, 0));
        user_panel.setLayout(new FlowLayout());
        user_panel.add(id_panel);
        user_panel.add(pw_panel);


        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(380, 30));
        panel.setLayout(null);
        panel.setBackground(new Color(254, 229, 0));
        panel.add(k_label);
        panel.add(user_panel);

        add(panel, BorderLayout.CENTER);
        btn_login = new JButton("로그인");
        btn_login.addActionListener(this);
        btn_login.setBounds(135, 330, 110, 35);
        panel.add(btn_login);
        title_bar = new JPanel();
        title_bar.setPreferredSize(new Dimension(380, 34));
        title_bar.setLayout(null);
        title_bar.setBackground(new Color(254, 229, 0));
        title_bar.addMouseListener(this);
        title_bar.addMouseMotionListener(this);

        //상단바 X버튼
        btn_exit = new JButton("x");
        btn_exit.setBounds(338, 2, 40, 30);
        btn_exit.setPreferredSize(new Dimension(30, 30));
        btn_exit.addActionListener(this);
        title_bar.add(btn_exit);
        add(title_bar, BorderLayout.NORTH);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO 자동 생성된 메소드 스텁

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO 자동 생성된 메소드 스텁

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO 자동 생성된 메소드 스텁

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO 자동 생성된 메소드 스텁
        if (e.getSource().equals(btn_exit)) {
            this.dispose();
        } else if (e.getSource().equals(btn_login)) {
            String Name = id_text.getText();
            String PW = pw_text.getText();
            try {

                socket = new Socket("127.0.0.1", 30000);
                is = socket.getInputStream();
                dis = new DataInputStream(is);
                os = socket.getOutputStream();
                dos = new DataOutputStream(os);

                SendMessage("/100 " + Name + " " + PW);
                ListenNetwork net = new ListenNetwork();
                net.start();
            } catch (NumberFormatException | IOException er) {
                // TODO Auto-generated catch block
                System.out.println("connect error");
                //er.printStackTrace();

            }

        }

    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(title_bar)) {
            comPoint = e.getPoint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getSource().equals(title_bar)) {
            comPoint = null;
        }
    }

    public void mouseDragged(MouseEvent e) {
        Point current = e.getLocationOnScreen();
        this.setLocation(current.x - comPoint.x, current.y - comPoint.y);
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
    
    public static int check_RoomNum(String name) {
    	int result = -1;
    	for( int i=0; i<Room_list.size(); i++) {
    		if(((RoomInfo) Room_list.get(i)).getMembersToString().equals(name)) {
    			result = i;
    		}
    	}
    	return result;
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
                    String msg = new String(b, "euc-kr");
                    msg = msg.trim(); // �յ� blank NULL, \n ��� ����

                    String[] args = msg.split(" ");
                    System.out.println("ClientRcev:" + msg); // server ȭ�鿡 ���

                    
                    if (args[0].matches("/201")) {
                        String id = args[1];
                        String userProfile = args[2];
                        System.out.println("process /201");
                        //System.out.println(id+" "+userProfile);
                        chattingListGUI.getFriendsListGUI().addFriend(id,userProfile);
                       // friendsListGUI.addFriend(id,userProfile);
//
//                        thisGUI.dispose();
                    } else if (args[0].matches("/202")) {
                        System.out.println("해당 id를 가진 USER가 없습니다.");
                    }
                    if(args[0].matches("/301")){ //msg : /301 방번호 이름1 이름2 ...
                        int argsSize = args.length;
                    	int size = argsSize-2;
                    	int RoomNum = Integer.parseInt(args[1]);
                    	ArrayList <String> members = new ArrayList<String>();
                    	for(int i =2; i<argsSize; i++) {
                    		members.add(args[i]);
                    	}
                    	RoomInfo new_room = new RoomInfo(RoomNum,size,members); //클라이언트에 새로운 방 정보 저장

                        //사이즈 출력 =
//                        System.out.println("************************");
//                        System.out.println("size = " + size);
//                        System.out.println("************************");


                    	Room_list.add(new_room); //벡터에 저장
                        System.out.println("채팅방 생성");
                        chattingListGUI.addChatting(new_room);
                    }
                    
                    if(args[0].matches("/302")){
                        int argsSize = args.length;
                      	int size = argsSize-2;
                      	int RoomNum = Integer.parseInt(args[1]);
                      	ArrayList <String> members = new ArrayList<String>();
                      	for(int i =2; i<argsSize; i++) {
                      		members.add(args[i]);
                      	}
                      	RoomInfo new_room = new RoomInfo(RoomNum,size,members); //클라이언트에 새로운 방 정보 저장
                      	Room_list.add(new_room); //벡터에 저장
                          System.out.println("채팅방 생성");
                          chat_Frame chatting = new chat_Frame(RoomNum, members, socket);
                    }
                    
                    if(args[0].matches("/502")) {
                    	String message = args[1];
                    	System.out.println("RECV message : "+ message);
        				if(message.equals("(등장)") || message.equals("(떼쓰기)") || message.equals("(우와)")|| message.equals("(축하)") || message.equals("(충성)") || message.equals("(ㅋㅋ)"))
        					chattingListGUI.getChatting().printEmoticon_Left(message);
        				else
                    	chattingListGUI.getChatting().makeLeftBubble(message);
                    }
                    
                    if (args.length > 1) {
                        if (args[1].equals("/101")||args[1].equals("/103")) {
                            userName = args[0];
                            chattingListGUI = new ChattingListGUI(socket, args[0]);
                            chattingListGUI.setVisible(true);
                            setVisible(false);
                        }
//                        if(args[1].equals("/103")) {
//                        	int answer = JOptionPane.showConfirmDialog(frame(), "종료하시겠습니까?", "confirm",JOptionPane.YES_NO_OPTION );
//                        }

                        if (args[1].equals("pass")) {
                            System.out.println("msg:" + args[0]);
//							user_info = new User(args[0]);


                        }
                        if (args[1].equals("/login")) {

                            System.out.println("msg:" + args[0]);
//							User new_user = new User(args[0]);
//							User_list.add(new_user);
                            for (int i = 0; i < User_list.size(); i++) {
                                System.out.println("list:" + User_list.get(i).id);
                                System.out.println("->");
                            }

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


    public static byte[] MakePacket(String msg) {
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

    public static void SendMessage(String msg) {
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

    public static void main(String[] args) {
        Login_Frame frame = new Login_Frame();

        //Profile_Frame frame = new Profile_Frame();
        frame.setVisible(true);
    }
}
