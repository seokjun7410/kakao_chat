package kakao_Chat;

import kakao_Chat.design.pictureEdit.PictureRound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Profile_Frame extends JFrame implements MouseListener, MouseMotionListener, ActionListener {
    private JPanel title_bar;
    private JButton btn_exit;
    private Point comPoint;
    private JButton menu_bar;
    private String User_name;
    private JLabel pl;
    private JLabel profile_label;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String currentName;
    private String profile_filename;
    private static final int BUF_LEN = 128;
    private Login_Frame.ListenNetwork listenNetwork;
    public Profile_Frame(String user_name, Socket s, String name, Login_Frame.ListenNetwork listenNetwork) throws IOException {
        currentName = name;
        this.socket = s;
        this.User_name = user_name;
        this.listenNetwork = listenNetwork;

        profile_filename = "img/UserProfile/"+User_name+".png";
        setResizable(false);
        setUndecorated(true);
//		setTitle("");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        JPanel background_panel = new JPanel();
        background_panel.setLayout(new BorderLayout());
//		Image bg_img = new ImageIcon("img/default_back.jpg").getImage().getScaledInstance(380,650,Image.SCALE_DEFAULT);
//		ImageIcon bg = new ImageIcon(bg_img);
//		JLabel img = new JLabel(bg);
        background_panel.setBackground(new Color(132, 139, 145));
        add(background_panel, BorderLayout.CENTER);

        //상단바 구성
        title_bar = new JPanel();
        title_bar.setPreferredSize(new Dimension(300, 34));
        title_bar.setLayout(null);
        title_bar.setBackground(new Color(132, 139, 145));
        title_bar.addMouseListener(this);
        title_bar.addMouseMotionListener(this);

        

        //상단바 X버튼
        Image x = new ImageIcon("img/x.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon x_button = new ImageIcon(x);

        btn_exit = new JButton(x_button);
        btn_exit.setBounds(277, 4, 25, 25);
        btn_exit.setBackground(new Color(132, 139, 145));
        btn_exit.addActionListener(this);
        title_bar.add(btn_exit);

        JPanel center_panel = new JPanel();
        center_panel.setBackground(new Color(132, 139, 145));
        center_panel.setLayout(new BorderLayout());
        background_panel.add(center_panel, BorderLayout.CENTER);

        JPanel Profile_panel = new JPanel();
        Profile_panel.setPreferredSize(new Dimension(300, 170));
        Profile_panel.setBackground(new Color(132, 139, 145));
        center_panel.add(Profile_panel, BorderLayout.SOUTH);

        JPanel Profile_panel2 = new JPanel();
        Profile_panel2.setPreferredSize(new Dimension(130, 170));
        Profile_panel2.setBackground(new Color(132, 139, 145));
        Profile_panel.add(Profile_panel2);
        
        //TODO 프로필 사진 이름 불러오기
        ImageIcon pi = PictureRound.setImageRound(profile_filename,80);
        profile_label = new JLabel(pi);
        profile_label.setBackground(new Color(132, 139, 145));
        Profile_panel2.add(profile_label);

        JLabel name_label = new JLabel(user_name);
        name_label.setForeground(Color.white);
        name_label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        Profile_panel2.add(name_label);


        //하단 메뉴바
        menu_bar = new JButton();
        menu_bar.setPreferredSize(new Dimension(380, 100));
        menu_bar.setLayout(new GridLayout(1, 3));
        menu_bar.setBackground(new Color(132, 139, 145));
        menu_bar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(156, 162, 167)));

        //empty_menu_bar[1].setText("test");
        Image bg_img = new ImageIcon("img/chat_1.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon bg = new ImageIcon(bg_img);
        JLabel img = new JLabel(bg);
        //empty_menu_bar[1].setPreferredSize(new Dimension(200,200));
        //empty_menu_bar[1].add(img);
        menu_bar.add(img);
//        menu_bar.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("1:1 채팅 버튼이 눌렸습니다.");
//                try {
//                    is = socket.getInputStream();
//                    dis = new DataInputStream(is);
//                    os = socket.getOutputStream();
//                    dos = new DataOutputStream(os);
//
//                    //SendMessage("/300 2 "+ User_name+" "+ currentName);
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        });
//		SendMessage("/300 2 "+ User_name+" "+ currentName);
        menu_bar.addMouseListener(this);


        add(menu_bar, BorderLayout.SOUTH);

        background_panel.add(title_bar, BorderLayout.NORTH);

        setBounds(550, 130, 300, 600);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btn_exit)) {
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

    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(title_bar)) {
            comPoint = e.getPoint();
        } else if (e.getSource().equals(menu_bar)) {
//			menu_bar.setBackground(new Color(0,0,0,124));
//			revalidate();
            System.out.println("1:1 채팅버튼이 눌렸습니다.");

            //채팅방 생성
            int roomNum = listenNetwork.check_RoomNum(User_name+" "+Login_Frame.userName);
            if (roomNum < 0) { //채팅방이 없을경우
                System.out.println("상대방과 존재하는 채팅이 없어 채팅방 오픈을 요청합니다");
                Login_Frame.SendMessage("/300 2 " + User_name + " "+currentName);
                System.out.println("SEND :"+" 인원수 2 " + "TO "+ User_name + " " + "FROM"+ currentName);
            }
        	else {//채팅방이 있을경우
        		ArrayList<String> name = new ArrayList<String>();
            	name.add(User_name);


                System.out.println("상대방과의 채팅이 이미 존재하여 "+roomNum+"번 방 클라이언트가 직접 오픈합니다.");
        		chat_Frame chatting = new chat_Frame(roomNum,2, name, socket);
            }
            //chat_Frame 생성을 server에게 요청

            dispose();

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
    public void mouseEntered(MouseEvent e) {
        // TODO 자동 생성된 메소드 스텁

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO 자동 생성된 메소드 스텁

    }

}
