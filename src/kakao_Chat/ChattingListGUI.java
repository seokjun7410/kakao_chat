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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import java.awt.Image;

import javax.swing.plaf.basic.BasicMenuBarUI;

import kakao_Chat.design.mini_profile.MiniProfileManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import static kakao_Chat.FriendsListGUI.ChatRoomEntered;

public class ChattingListGUI extends JFrame {
    int chattingListHeight = 71; // 채팅방 버튼을 담는 list의 크기
    static int chattingListIndex = -1; //채팅방이 생성될 때마다 +1
    private MiniProfileManager miniProfileManager; //미니 프로필 디자인 동적 선택 생성 매니저
    private JPanel chatPanel;
    JButton moveFriendsList;
    JButton moveChattingList;
    private Socket socket;
    FriendsListGUI friendsListGUI;// = new FriendsListGUI(socket);
    chat_Frame chatting;
    private InputStream is;
    private OutputStream os;
    private DataInputStream dis;
    private DataOutputStream dos;
    private JLabel lblUserName;
    private static final int BUF_LEN = 128;
    public static String userName;
    public static ArrayList<User> User_list;
    public Vector RoomVec = new Vector();
    public int Size_list;
    private ArrayList<JPanel> chattingButtonList = new ArrayList<JPanel>();
    private JPanel chattingListPanel;
    private String currentUserName = "null";
    private  Vector<RoomInfo> room_list;
    private String lastMsg = "";


    public FriendsListGUI getFriendsListGUI() {
        return friendsListGUI;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public ChattingListGUI(Socket s, String currentUserName, Login_Frame.ListenNetwork listenNetwork) throws IOException {
        this.socket = s;
        this.currentUserName = currentUserName;
        this.room_list = listenNetwork.Room_list;
        setBackground(new Color(255, 255, 255));
        setBounds(900, 100, 400, 690);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);


//		JPanel friends = friendsListGUI.get();

        chatPanel = new JPanel();
        chatPanel.setBackground(Color.WHITE);
        chatPanel.setBounds(67, 0, 317, 650);

        chattingListPanel = new JPanel();
        chattingListPanel.setBackground(Color.WHITE);
        chattingListPanel.setBounds(67, 50, 317, 590);
        chattingListPanel.setLayout(new FlowLayout());
        JScrollPane pane = new JScrollPane(chattingListPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setBounds(0, 61, 317, 589);
        pane.setPreferredSize(new Dimension(317, 590));
        pane.getVerticalScrollBar().setUnitIncrement(14);
        chatPanel.setLayout(null);
        pane.setBorder(null);
        chatPanel.add(pane);
        getContentPane().add(chatPanel);

        JPanel topBar = new JPanel();
        topBar.setBounds(0, 0, 317, 60);
        chatPanel.add(topBar);
        topBar.setBackground(new Color(255, 255, 255));
        topBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        topBar.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(255, 255, 255));
        menuBar.setBounds(12, 29, 50, 35);
        menuBar.setUI(new BasicMenuBarUI() {
            public void paint(Graphics g, JComponent c) {
                g.setColor(Color.white);
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });
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
                System.out.println("채팅방 생성창 오픈합니다.");
                try {
                    new addChattingGUI(socket, currentUserName);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
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
        Image f = new ImageIcon("img/side_btn_1_1.png").getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT);
        ImageIcon f_button = new ImageIcon(f);
        moveFriendsList.setBackground(new Color(236, 236, 237));
        moveFriendsList.setIcon(f_button);
        moveFriendsList.setBorderPainted(false);
        moveFriendsList.setFocusPainted(false);
        moveFriendsList.setBounds(22, 35, 22, 22);
        moveFriendsList.setBorderPainted(false);
        sideMenuPane.add(moveFriendsList);

        moveChattingList = new JButton("");
        Image c = new ImageIcon("img/side_btn_2_2.png").getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT);
        ImageIcon c_button = new ImageIcon(c);
        moveChattingList.setBackground(new Color(236, 236, 237));
        moveChattingList.setIcon(c_button);
        moveChattingList.setBounds(22, 95, 22, 22);
        moveChattingList.setBorderPainted(false);
        sideMenuPane.add(moveChattingList);

        JButton moveMore = new JButton("");
        Image m = new ImageIcon("img/side_btn_3_2.png").getImage().getScaledInstance(22, 6, Image.SCALE_DEFAULT);
        ImageIcon m_button = new ImageIcon(m);
        moveMore.setBackground(new Color(236, 236, 237));
        moveMore.setIcon(m_button);
        moveMore.setBorderPainted(false);
        moveMore.setBounds(22, 155, 22, 7);
        sideMenuPane.add(moveMore);

        friendsListGUI = new FriendsListGUI(socket, currentUserName, listenNetwork);
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
                Image f = new ImageIcon("img/side_btn_1_1.png").getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT);
                ImageIcon f_button = new ImageIcon(f);
                moveFriendsList.setIcon(f_button);

                Image c = new ImageIcon("img/side_btn_2_2.png").getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT);
                ImageIcon c_button = new ImageIcon(c);
                moveChattingList.setIcon(c_button);
            }
        });
        moveChattingList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chatPanel.setVisible(true);
                friendPanel.setVisible(false);
                Image f = new ImageIcon("img/side_btn_1_2.png").getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT);
                ImageIcon f_button = new ImageIcon(f);
                moveFriendsList.setIcon(f_button);

                Image c = new ImageIcon("img/side_btn_2_1.png").getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT);
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

    public ArrayList<JPanel> getChattingButtonList() {
        return chattingButtonList;
    }


    public void addChatting(RoomInfo roominfo) throws IOException {
        if (chattingListIndex < 20) {

            createChattingRoom(roominfo, chattingButtonList, chattingListPanel); //chatingRoom 생성
            //chattingListPanel.setSize(317,chattingListHeight);
            chattingListPanel.setPreferredSize(new Dimension(317, chattingListHeight));
            revalidate();
            repaint();
        } else {
            System.out.println("최대 방 개수를 초과했습니다.");
        }
    }

    public void setlastMsg(String msg){
        this.lastMsg = msg;
        chattingListPanel.repaint();
        chattingListPanel.revalidate();
    }

    private void createChattingRoom(RoomInfo roominfo, ArrayList<JPanel> chattingButtonList, JPanel chattingListPanel) throws IOException {

        JPanel chattingPanel = new JPanel();
        chattingPanel.setAutoscrolls(true);
        chattingPanel.setBounds(67, 50, 317, chattingListHeight);
        chattingPanel.setBackground(Color.white);
        chattingPanel.setLayout(null);
        chattingPanel.setPreferredSize(new Dimension(317, 71));

        lastMsg = roominfo.getLastMessage();

        String[] imgNames = new String[4];
        int size = roominfo.getSize();
        ArrayList<String> members = roominfo.getMembers();

        //채팅방 타이틀에는 나를 제외한 멤버들 이름만 존재함.
        int i = 0;
        ArrayList<String> chatTitleMembers = new ArrayList<>();
        for (String member : members) {
            if(!member.equals(currentUserName)) {
                chatTitleMembers.add(member);
                imgNames[i++] = "img/UserProfile/"+member+".png";
                System.out.println("imgNames = " + imgNames[i]);
            }
        }

        //멤버들 프로필



        /**** 채팅방 생성 ****/
        int random = (int) ((Math.random() * (6 - 2)) + 2); //Random한 DummyData 생성
        int DUMMY_NumberOfPeople = random;

        miniProfileManager = MiniProfileManager.getInstance();
        miniProfileManager.setMiniProfileDesign_Chat(roominfo.getSize(),imgNames); //룸 크기 설정

        String chatName = miniProfileManager.makeMiniProfile(size, chatTitleMembers, chattingPanel, chattingListHeight, chattingListIndex, lastMsg);

        /*******************/


        chattingButtonList.add(chattingPanel);
        chattingListPanel.setBounds(0, 0, 317, chattingListHeight);
        chattingListPanel.add(chattingButtonList.get(++chattingListIndex));
        chattingListHeight += 71;


        chattingPanel.addMouseListener(new MouseAdapter() {
            int index = -1;
            @Override
            public void mouseClicked(MouseEvent e) {


                    System.out.println("clicked [" + chatName + "]");
                    for (int i = 0; i < chattingButtonList.size(); i++) {
                        if(chattingButtonList.get(i) == (JPanel) e.getSource()){
                            index = i;
                        }
                    }
                    int roomNum = ((RoomInfo) room_list.get(index)).getRoomNum();
                    System.out.println("눌린 index = " + index);

                    for (int i = 0; i < room_list.size(); i++) {
                        System.out.println("Room_List의 index:"+i+" 의 RoomNum은 "+((RoomInfo) room_list.get(i)).getRoomNum());
                    }

                    ArrayList<String> members = ((RoomInfo) room_list.get(index)).getMembers();
//				chatting = new chat_Frame(roomNum,members,socket);
//				chatting.setVisible(true);
                //if(!ChatRoomEntered.contains(roomNum)) {
                    //채팅방 생성 serverListener에게 위임
                    System.out.println("서버에게 방번호 : "+roomNum+" 오픈 요청을 합니다.");
                    Login_Frame.SendMessage("/310 " + roomNum + " " + currentUserName);
                    System.out.println("SEND : " + "/310 " + roomNum + " " + currentUserName);
                    ChatRoomEntered.add(roomNum);
                //}


            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JPanel b = (JPanel) e.getSource();
                b.setBackground(new Color(245, 245, 245));
            }

            @Override//마우스가 버튼 밖으로 나가면 노란색으로 바뀜
            public void mouseExited(MouseEvent e) {
                JPanel b = (JPanel) e.getSource();
                b.setBackground(Color.white);
            }
        });
    }

}
