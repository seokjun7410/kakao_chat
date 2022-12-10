package kakao_Chat;

import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.pictureEdit.PictureRound;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.*;

import static kakao_Chat.FriendsListGUI.profileButton;
import static kakao_Chat.Profile_Frame.profile_filename;
import static kakao_Chat.chat_Frame.chat_list;
import static kakao_Chat.design.pictureEdit.PictureRound.saveImageIcon;

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
    public static Vector<RoomInfo> Room_list = new Vector();
    public Vector<chat_Frame> Chatting_List = new Vector();
    public int Size_list;
    public static Vector<String> Friends_List = new Vector<>();
    private static int file_num = 0;
    public static RoomInfo getRoomInfoByRoomId(String roomId) {
        for (RoomInfo roomInfo : Room_list) {
            if (roomInfo.getRoomNum() == Integer.parseInt(roomId)) {
                return roomInfo;
            }
        }
        return null;
    }
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
        id_text.setMargin(new Insets(0, 10, 0, 0));
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

        pw_text.setBounds(100, 110, 380, 26);
        pw_text.setPreferredSize(new Dimension(380, 30));
        pw_text.setMargin(new Insets(0, 10, 0, 0));
        pw_text.setColumns(17);
        pw_panel.add(pw_area);
        pw_panel.add(pw_text);
        pw_panel.setBackground(new Color(254, 229, 0));

        JPanel user_panel = new JPanel();
        user_panel.setBounds(-22, 220, 380, 100);
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
        btn_login.setBackground(new Color(66, 54, 48));
        btn_login.setBounds(100, 320, 185, 35);
        btn_login.setForeground(Color.white);
        panel.add(btn_login);
        title_bar = new JPanel();
        title_bar.setPreferredSize(new Dimension(380, 40));
        title_bar.setLayout(null);
        title_bar.setBackground(new Color(254, 229, 0));
        title_bar.addMouseListener(this);
        title_bar.addMouseMotionListener(this);

        //상단바 X버튼
        btn_exit = new JButton("x");
        btn_exit.setFont(new Font("Arial", Font.PLAIN, 17));
        btn_exit.setBorderPainted(false);
        btn_exit.setBackground(new Color(254, 229, 0));
        btn_exit.setBounds(338, 2, 45, 40);
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

    //채팅방 있으면 해당 채팅방 번호, 없으면 -1 반환
    public static int check_RoomNum(String name) {
        int result = -1;
        for (int i = 0; i < Room_list.size(); i++) {
            if (((RoomInfo) Room_list.get(i)).getMembersToString().equals(name)) {
                result = i;
            }
        }
        return result;
    }

    public static int ChattingExist(String roomNum) {
        return 0;
    }

    class ListenNetwork extends Thread {
        private ChattingListGUI chattingListGUI;
        private FriendsListGUI friendsListGUI;
        private addFriendGUI addFriendGUI;
        private chat_Frame chatting;
        public static Vector<RoomInfo> Room_list = new Vector();


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
                    //System.out.println("ClientRcev:" + msg); // server ȭ�鿡 ���


                    if (args[0].matches("/201")) { //친구추가 지시 msg : /201 id imageURL

                        //나 자신을 친구추가 했다면 return
                        if(args[1].equals(userName)){
                            System.out.println("나 자신은 추가할 수 없습니다.");
                            return;
                        }

                        //친구가 이미 존재한다면 return
                        for (String friend : Friends_List) {
                            if (friend.equals(args[1])) {
                                System.out.println("이미 존재하는 친구입니다...");
                                return;
                            }
                        }

                        System.out.println("Recv: 유저 \"" + args[1] + "\"의 프로필 이미지는 " + args[2]);
                        String id = args[1];
                        String userProfile = args[2];
                        chattingListGUI.getFriendsListGUI().addFriend(id, userProfile);
                        Friends_List.add(args[1]);

                    } else if (args[0].matches("/202")) {
                        System.out.println("해당 id를 가진 USER가 없습니다.");
                    }
                    /* 채팅방 생성 당함 */
                    if (args[0].matches("/301")) { //msg : /301 방번호 이름1 이름2 ...

                        System.out.println("RECV: 채팅방 생성 지시를 받았습니다.");
                        System.out.print("RECV MSG : ");
                        for (String arg : args) {
                            System.out.print(arg + " ");
                        }
                        System.out.println("");

                        int argsSize = args.length; //인자 크기
                        int size = argsSize - 2; //인원수
                        int RoomNum = Integer.parseInt(args[1]); //방번호

                        //members에 member 이름 추가
                        ArrayList<String> members = new ArrayList<String>();
                        ArrayList<String> membersNoCurrentUser = new ArrayList<>();
                        for (int i = 2; i < argsSize; i++) {
                            members.add(args[i]);
//                            if(i>2)
//                                membersNoCurrentUser.add(args[i]); //나자신 뺴고 members
                        }

                        //새로운 채팅방 생성
                        RoomInfo new_room = new RoomInfo(RoomNum, size, members, ""); //클라이언트에 새로운 방 정보 저장
                        System.out.println(RoomNum + "번 방에 대한 RoomInfo 가 생성되었습니다.");


                        Room_list.add(new_room); //벡터에 저장
                        System.out.print("방번호 : " + RoomNum + " 인원수 : " + size);
                        System.out.print(" 멤버 : ");
                        for (String s : members) {
                            System.out.print(s + ", ");
                        }

                        System.out.println("의 채팅방이 목록에 생성되었습니다");
                        chattingListGUI.addChatting(new_room);  //GUI 채팅방 생성


                    }
                    /* 채팅방 생성 요청한 User */
                    if (args[0].matches("/302")) { //msg : /302 방번호 이름1 이름2 ...
                        System.out.println("RECV: 1:1채팅을 요청한 나는 방띄우기를 지시받음");

                        RoomInfo roominfo = getRoomInfoByRoomId(args[1]);
                        int RoomNum = roominfo.getRoomNum();
                        ArrayList<String> members = roominfo.getMembers();

                        //채팅방 타이틀에는 나를 제외한 멤버들 이름만 존재함.
                        ArrayList<String> chatTitleMembers = new ArrayList<>();
                        for (String member : members) {
                            if (!member.equals(Login_Frame.userName)) {
                                chatTitleMembers.add(member);
                                System.out.println("Login_Frame.this = " + Login_Frame.userName);
                                System.out.println("member = " + member);
                            }

                        }

                        chatting = new chat_Frame(RoomNum, roominfo.getMembers().size(), chatTitleMembers, socket);

                        System.out.print("방번호 : " + RoomNum + " 인원수 : " + roominfo.getMembers().size());
                        System.out.print("멤버 : ");
                        for (String s : members) {
                            System.out.print(s + ", ");
                        }
                        Chatting_List.add(chatting);
                        System.out.print("현재 열린 채팅창은 " + Chatting_List.size() + "개 입니다. 방 번호는 ");
                        for (int i = 0; i < Chatting_List.size(); i++) {
                            System.out.println(Chatting_List.get(i).getRoom_number() + ", ");
                        }
                        System.out.println(RoomNum + "번의 채팅 프레임이 관리됩니다.");

                        System.out.println("채팅방 생성 완료");


                    }/* 채팅리스트에서 채팅방을 열때 */
                    if (args[0].matches("/310")) { //msg: "/310 " + roomNum
                        System.out.println("RECV 방번호 :" + args[1] + "번 채팅방 오픈 지시를 받음");
//                        int RoomNum = Integer.parseInt(args[1]);
//
//                        chatting = new chat_Frame(RoomNum, membersNoCurrentUser, socket);
//                        Chatting_List.add(chatting);
//                        System.out.println("Chatting_List.size() = " + Chatting_List.size());
//                        for (int i = 0; i < Chatting_List.size(); i++) {
//                            System.out.println("Chatting_List.get(i) = " + Chatting_List.get(i));
//                        }
                        //채팅창 띄우기
                        RoomInfo roomInfo = getRoomInfoByRoomId(args[1]);
                        int RoomNum = roomInfo.getRoomNum();
                        ArrayList<String> members = roomInfo.getMembers();

                        //채팅방 타이틀에는 나를 제외한 멤버들 이름만 존재함.
                        ArrayList<String> chatTitleMembers = new ArrayList<>();
                        for (String member : members) {
                            if (!member.equals(Login_Frame.userName))
                                chatTitleMembers.add(member);
                        }

                        chatting = new chat_Frame(RoomNum, roomInfo.getMembers().size(), chatTitleMembers, socket);

                        System.out.print("방번호 : " + RoomNum + " 인원수 : " + roomInfo.getMembers().size());
                        System.out.print("멤버 : ");
                        for (String s : roomInfo.getMembers()) {
                            System.out.print(s + ", ");
                        }
                        System.out.println("의 채팅방이 목록에 오픈되었습니다");

                        Chatting_List.add(chatting);
                        System.out.print("현재 열린 채팅창은 " + Chatting_List.size() + "개 입니다. 방 번호는 ");
                        for (int i = 0; i < Chatting_List.size(); i++) {
                            System.out.println(Chatting_List.get(i).getRoom_number() + ", ");
                        }

                    }/* 채팅방 제거 지시 */ else if (args[0].matches("/320")) { //msg :  /320 " +room_number"
                        System.out.print("채팅방 ");
                        for (int i = 0; i < Chatting_List.size(); i++) {
                            System.out.print(Chatting_List.get(i).getRoom_number() + "번 ");
                            if (Chatting_List.get(i).getRoom_number() == Integer.parseInt(args[1])) {
                                System.out.print("중에서 ");
                                System.out.println(Chatting_List.get(i).getRoom_number() + "번이 제거되었습니다.");
                                Chatting_List.remove(i);
                            }
                        }
                    }
                    /* 메세지 수신 */
                    if (args[0].matches("/502")) { //502  roomid message username
                        String message = args[2];
                        System.out.println("RECV message : " + message);
                        RoomInfo roomInfoByRoomId = getRoomInfoByRoomId(args[1]);

                        /* 클라이언트 단 메시지 저장 */
                        /* 현재시간 */
                        LocalTime time = LocalTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                        String currentTime = time.format(formatter);
                        String currentTime12;
                        if(Integer.parseInt(currentTime.substring(0,2)) >= 13)
                            currentTime12 = "오후"+(Integer.parseInt(currentTime.substring(0,2))-12)+":"+currentTime.substring(3,5);
                        else
                            currentTime12 = "오전"+currentTime;
                        Message m = new Message(message,args[3],currentTime12);
                       roomInfoByRoomId.setMessages(m);
                        System.out.println("방번호 : "+roomInfoByRoomId.getRoomNum()+"에 "+roomInfoByRoomId.getMessages().get(0).getBody()+" 메시지가 저장되었습니다.");

                        /* 마지막 메시지 */
                        int index = Room_list.indexOf(getRoomInfoByRoomId(String.valueOf(roomInfoByRoomId.getRoomNum())));
                        ArrayList<JPanel> chattingButtonList = chattingListGUI.getChattingButtonList();
                        JPanel jPanel = chattingButtonList.get(index);
                        Component[] components = jPanel.getComponents();
                        for (Component component : components) {
                            if(component instanceof JLabel) {
                                try {
                                    JLabel component1 = (JLabel) component;
                                    if (component1.getToolTipText().equals("lastMessageLabel"))
                                        component1.setText(roomInfoByRoomId.getLastMessage());
                                }catch (NullPointerException e){

                                }
                            }
                        }
                        chattingListGUI.getContentPane().revalidate();
                        chattingListGUI.revalidate();
                        chattingListGUI.repaint();
//                        for (User user : User_list) {
//                            System.out.println("user.id = " + user.getId());
//                        }
//                        for (RoomInfo roomInfo : Room_list) {
//                            System.out.println("roomInfo.getRoomNum() = " + roomInfo.getRoomNum());
//                        }
//                        for (chat_Frame chat_frame : Chatting_List) {
//                            System.out.println("chat_frame = " + chat_frame.getRoom_number());
//                        }
//                        System.out.println("Chatting_List.size() = " + Chatting_List.size());
                        chat_Frame chatting = getChatFrameByRoomNum(args[1]);
//                        System.out.println("args[1] = " + args[1]);
                        try {
                            if(message.equals("(등장)") || message.equals("(떼쓰기)") || message.equals("(우와)")|| message.equals("(축하)") || message.equals("(충성)") || message.equals("(ㅋㅋ)"))
                                chatting.printEmoticon_Left(message, args[3]);
                            else chatting.makeLeftBubble(message, args[3]);
                            JScrollBar vertical = chatting.pane.getVerticalScrollBar();
                            vertical.setValue( vertical.getMaximum() );
                        }catch (NullPointerException e){
                            System.out.println(e.getMessage());
                        }

                    }
                    if (args[0].matches("/555")) { // msg : /555 rommid
                        /* 마지막 메시지 */

                        int roomid = Integer.parseInt(args[1]);
                        int index = Room_list.indexOf(getRoomInfoByRoomId(String.valueOf(roomid)));
                        RoomInfo roomInfoByRoomId = getRoomInfoByRoomId(String.valueOf(roomid));
                        ArrayList<JPanel> chattingButtonList = chattingListGUI.getChattingButtonList();
                        JPanel jPanel = chattingButtonList.get(index);
                        Component[] components = jPanel.getComponents();
                        for (Component component : components) {
                            if(component instanceof JLabel) {
                                try {
                                    JLabel component1 = (JLabel) component;
                                    if (component1.getToolTipText().equals("lastMessageLabel"))
                                        component1.setText(roomInfoByRoomId.getLastMessage());
                                }catch (NullPointerException e){

                                }
                            }
                        }
                        chattingListGUI.getContentPane().revalidate();
                        chattingListGUI.revalidate();
                        chattingListGUI.repaint();
                    }
                    if (args[0].matches("/503")) { // RoomNum UserName
                        System.out.println(msg);
                        System.out.println("/503");
                        int arrlen = dis.readInt();
                        byte[] bytes = new byte[arrlen];
                        dis.readFully(bytes);
                        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                        BufferedImage bufferedImage = ImageIO.read(inputStream);
                        System.out.println("파일 받음");

//                        ObjectInputStream ois = new ObjectInputStream(is);
                        ImageIcon img = new ImageIcon(bufferedImage);
//                        while(img ==null){
//                             img = (ImageIcon)ois.readObject();
//                        }
                        chatting.printImage_Left(img, args[1]);
                        JScrollBar vertical = chatting.pane.getVerticalScrollBar();
                        vertical.setValue( vertical.getMaximum() );
//                        ImageIcon img = (ImageIcon)ois.readObject();
//                        if(img != null){
//                            JFrame jf = new JFrame();
//                          503  JLabel jl = new JLabel();
//                            jf.add(jl);
//                            jl.setIcon(img);
//                            jf.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
//                            jf.setVisible(true);
//                        }
                    }

                    if (args[0].equals("/601")) { //프로필 변경지시msg: 601 id
                        System.out.println(args[1] + "의 프로필 변경지시 받았습니다");
                        profile_filename = "img/UserProfile/" + args[1] + ".png";
                        try {
                            /* 친구목록에 프로필 변경 */
                            ImageIcon pi = PictureRound.setImageRound(profile_filename, 60);
                            //profileButton.setIcon(pi);
                            System.out.println("args[1] = " + args[1]);
                            ArrayList<JPanel> friendsButtonList = chattingListGUI.getFriendsListGUI().getChattingButtonList();
                            ArrayList<JPanel> chattingButtonList = chattingListGUI.getChattingButtonList();

                            /* 친구목록에 프로필 변경 */
                            for (JPanel jPanel : friendsButtonList) {
                                JLabel name = (JLabel) jPanel.getComponent(0);
                                if (name.getText().equals(args[1])) {
                                    JButton profileButton = new RoundedButton("", pi);
                                    profileButton.setBounds(10, 8, 55, 56);
                                    jPanel.remove(1);
                                    jPanel.add(profileButton);
                                    jPanel.revalidate();
                                    jPanel.repaint();
                                    chattingListGUI.getFriendsListGUI().getContentPane().revalidate();
                                    chattingListGUI.getFriendsListGUI().revalidate();
                                    chattingListGUI.getFriendsListGUI().repaint();
                                }
                            }

                            /* 채팅목록 프로필 변경 */
                            for (JPanel jPanel : chattingButtonList) { //모든 채팅 리스트 중에서

                                //채팅방 이름 Label에 text를 추출한다
                                JLabel namePan = (JLabel) jPanel.getComponent(0);
                                String nameLableString = namePan.getText();

                                //채팅방 이름을 통해 미니프로필 index 구하기
                                int index = -1;
                                System.out.println("nameLableString = " + nameLableString.replace(" ",""));
                                String peoplestr = nameLableString.replace(" ","");
                                String[] split = peoplestr.split(",");
                                for(int i =0; i<split.length; i++){
                                    if(split[i].equals(args[1])) index =i;
                                }
                                int numOfpeople = split.length+1;
                                System.out.println("numOfpeople = " + numOfpeople);

                                switch (numOfpeople){
                                    case 2: pi = PictureRound.setImageRound(profile_filename, 60); break;
                                    case 3: pi = PictureRound.setImageRound(profile_filename, 40); break;
                                    case 4: pi = PictureRound.setImageRound(profile_filename, 33); break;
                                    case 5: pi = PictureRound.setImageRound(profile_filename, 30); break;
                                }

                                System.out.println("index = " + index);

                                if (index != -1) { //해당 프로필이 있는 채팅방이 있다면
                                    System.out.println("채팅목록 변경중");

                                    Component[] components = jPanel.getComponents();  //모든 채팅방 Panel에서
                                    for (Component component : components) {
                                        if (component instanceof RoundedButton) { //RoundButton인걸 찾고

                                            Optional<String> toolTipText = Optional.ofNullable(((RoundedButton) component).getToolTipText()); //nullable 함.
                                            if(toolTipText.isPresent()){ //null이 아니라면
                                                System.out.println(" 진입");
                                                String text = toolTipText.get(); //해당 button의 toolTipText를 확인하고
                                                System.out.println("text = " + text);
                                                if(text.equals(String.valueOf(index))) { // tooTipText와 index가 일치한다면
                                                    System.out.println("toolTipText = " + toolTipText);
                                                    ((RoundedButton) component).setIcon(pi); //아이콘을 변경한다.
                                                    System.out.println("change profile");
                                                }
                                            }


                                        }
                                    }



                                    chattingListGUI.getContentPane().revalidate();
                                    chattingListGUI.revalidate();
                                    chattingListGUI.repaint();

                                }
                            }


                            for(chat_Frame cf : Chatting_List) {
                                int index = -1;
                                ArrayList<String> ma = cf.getMembers();
                                for(int i=0; i<ma.size(); i++){
                                    if(ma.get(i).equals(args[1])) {
                                        index = i;
                                    }
                                }

                                if(index != -1){
                                    ArrayList<Integer> user_chatlist = cf.findChatPanel(args[1]);
                                    cf.refreshProfile(user_chatlist,args[1]);
                                }
                            }

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        revalidate();
                        repaint();
                    }

                    if (args[0].equals("/601")) { //프로필 변경지시
                        System.out.println(args[1] + "의 프로필 변경지시 받았습니다");
                        profile_filename = "img/UserProfile/" + Login_Frame.userName + ".png";
                        ImageIcon pi = PictureRound.setImageRound(profile_filename, 60);
                        profileButton.setIcon(pi);
                        System.out.println("프로필 변경완료");
                        chattingListGUI.friendsListGUI.getFriendsListGUI().repaint();
                        chattingListGUI.friendsListGUI.getFriendsListGUI().revalidate();

                        revalidate();
                        repaint();
                    }

                    if (args.length > 1) {
                        if (args[1].equals("/101")) {
                            System.out.println("Recv: 서버로부터 로그인승인 받음");
                            userName = args[0];
                            chattingListGUI = new ChattingListGUI(socket, args[0], this);
                            chattingListGUI.setVisible(true);
                            setVisible(false);
                        }
                        if (args[1].equals("/103")) {
                            System.out.println("Recv: 서버로부터 로그인승인 받음");
                            userName = args[0];
                            saveImageIcon(userName, "img/dp.jpg");
                            chattingListGUI = new ChattingListGUI(socket, args[0], this);
                            chattingListGUI.setVisible(true);
                            setVisible(false);
                        }

                        if (args[1].equals("pass")) {
                            System.out.println("msg:" + args[0]);
//							user_info = new User(args[0]);
                        }
                        if (args[1].equals("/login")) {

                            System.out.println("msg:" + args[0]);
//							User new_user = new User(args[0]);
//							User_list.add(new_user);
                            for (int i = 0; i < User_list.size(); i++) {
                                System.out.println("list:" + User_list.get(i).getId());
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

        public static RoomInfo getRoomInfoByRoomId(String roomId) {
            for (RoomInfo roomInfo : Room_list) {
                if (roomInfo.getRoomNum() == Integer.parseInt(roomId)) {
                    return roomInfo;
                }
            }
            return null;
        }

        private chat_Frame getChatFrameByRoomNum(String roomNum) {
            System.out.println("방번호로 ChatFrame이 조회됨");
            for (chat_Frame chat_frame : Chatting_List) {
                System.out.println("모든 chat_frame 방번호 = " + chat_frame.getRoom_number());
                System.out.println("입력 방번호 = " + roomNum);
                if (chat_frame.getRoom_number() == Integer.parseInt(roomNum))
                    return chat_frame;
            }

            return null;
        }

        //갠톡방 있으면 해당 채팅방 번호, 없으면 -1 반환
        public int check_RoomNum(String name) {
            int result = -1;
            System.out.println("Room_list.size() = " + Room_list.size());
            for (int i = 0; i < Room_list.size(); i++) {
                System.out.println("Room_list " + i + "번째 멤버들 : " + Room_list.get(i).getMembersToString());
                System.out.println("친구 name = " + name);
                if (((RoomInfo) Room_list.get(i)).getMembersToString().equals(name + " ") && Room_list.get(i).getMembers().size() == 2) {
                    result = i;
                }
            }
            return result;
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

    //이미지 전송 , "/501 방번호 사용자이름"을 보내준 뒤 파일을 보냄
    public static void sendImage(String path) throws IOException {
//        ObjectOutputStream oos = new ObjectOutputStream(os);
//        //String file_name = userName+"_"+String.valueOf(file_num++)+".png";
//        System.out.println("sendImage:" + path);
//        BufferedImage img = ImageIO.read(new File(path));
//        ImageIcon ic =  new ImageIcon(img);
//        oos.writeObject(ic);
//        oos.flush();

        File file = new File(path);
        byte[] b = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(b);
        fis.close();dos.writeInt((int) file.length());
        dos.flush();
        dos.write(b, 0, b.length);
        dos.flush();
    }

    public static void main(String[] args) {
        Login_Frame frame = new Login_Frame();

        //Profile_Frame frame = new Profile_Frame();
        frame.setVisible(true);
    }


}
