package kakao_Chat;

import kakao_Chat.design.RoundedButton;
import kakao_Chat.design.mini_profile.chatroom.MiniProfileManager_chatroom;
import kakao_Chat.design.mini_profile.chatroom.profile.FourPeople;
import kakao_Chat.design.mini_profile.chatroom.profile.MoreThanFivePeople;
import kakao_Chat.design.mini_profile.chatroom.profile.ThreePeople;
import kakao_Chat.design.mini_profile.chatroom.profile.TwoPeople;
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
        id_area.setText(" ?????????  ");
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
        pw_area.setText("????????????");
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
        btn_login = new JButton("?????????");
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

        //????????? X??????
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
        // TODO ?????? ????????? ????????? ??????

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO ?????? ????????? ????????? ??????

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO ?????? ????????? ????????? ??????

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO ?????? ????????? ????????? ??????
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
        // TODO ?????? ????????? ????????? ??????

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO ?????? ????????? ????????? ??????

    }


    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO ?????? ????????? ????????? ??????

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO ?????? ????????? ????????? ??????

    }

    //????????? ????????? ?????? ????????? ??????, ????????? -1 ??????
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
                        }// catch?????? ??????
                    }
                    String msg = new String(b, "euc-kr");
                    msg = msg.trim(); // ???????? blank NULL, \n ????????? ????????????

                    String[] args = msg.split(" ");
                    //System.out.println("ClientRcev:" + msg); // server ???????? ?????????


                    if (args[0].matches("/201")) { //???????????? ?????? msg : /201 id imageURL

                        //??? ????????? ???????????? ????????? return
                        if (args[1].equals(userName)) {
                            System.out.println("??? ????????? ????????? ??? ????????????.");
                            return;
                        }

                        //????????? ?????? ??????????????? return
                        for (String friend : Friends_List) {
                            if (friend.equals(args[1])) {
                                System.out.println("?????? ???????????? ???????????????...");
                                return;
                            }
                        }

                        System.out.println("Recv: ?????? \"" + args[1] + "\"??? ????????? ???????????? " + args[2]);
                        String id = args[1];
                        String userProfile = args[2];
                        chattingListGUI.getFriendsListGUI().addFriend(id, userProfile);
                        Friends_List.add(args[1]);

                    } else if (args[0].matches("/202")) {
                        System.out.println("?????? id??? ?????? USER??? ????????????.");
                    }
                    /* ????????? ?????? ?????? */
                    if (args[0].matches("/301")) { //msg : /301 ????????? ??????1 ??????2 ...

                        System.out.println("RECV: ????????? ?????? ????????? ???????????????.");
                        System.out.print("RECV MSG : ");
                        for (String arg : args) {
                            System.out.print(arg + " ");
                        }
                        System.out.println("");

                        int argsSize = args.length; //?????? ??????
                        int size = argsSize - 2; //?????????
                        int RoomNum = Integer.parseInt(args[1]); //?????????

                        //members??? member ?????? ??????
                        ArrayList<String> members = new ArrayList<String>();
                        ArrayList<String> membersNoCurrentUser = new ArrayList<>();
                        for (int i = 2; i < argsSize; i++) {
                            members.add(args[i]);
//                            if(i>2)
//                                membersNoCurrentUser.add(args[i]); //????????? ?????? members
                        }

                        //????????? ????????? ??????
                        RoomInfo new_room = new RoomInfo(RoomNum, size, members, ""); //?????????????????? ????????? ??? ?????? ??????
                        System.out.println(RoomNum + "??? ?????? ?????? RoomInfo ??? ?????????????????????.");


                        Room_list.add(new_room); //????????? ??????
                        System.out.print("????????? : " + RoomNum + " ????????? : " + size);
                        System.out.print(" ?????? : ");
                        for (String s : members) {
                            System.out.print(s + ", ");
                        }

                        System.out.println("??? ???????????? ????????? ?????????????????????");
                        chattingListGUI.addChatting(new_room);  //GUI ????????? ??????


                    }
                    /* ????????? ?????? ????????? User */
                    if (args[0].matches("/302")) { //msg : /302 ????????? ??????1 ??????2 ...
                        System.out.println("RECV: 1:1????????? ????????? ?????? ??????????????? ????????????");

                        RoomInfo roominfo = getRoomInfoByRoomId(args[1]);
                        int RoomNum = roominfo.getRoomNum();
                        ArrayList<String> members = roominfo.getMembers();

                        //????????? ??????????????? ?????? ????????? ????????? ????????? ?????????.
                        ArrayList<String> chatTitleMembers = new ArrayList<>();
                        for (String member : members) {
                            if (!member.equals(Login_Frame.userName)) {
                                chatTitleMembers.add(member);
                                System.out.println("Login_Frame.this = " + Login_Frame.userName);
                                System.out.println("member = " + member);
                            }

                        }

                        chatting = new chat_Frame(RoomNum, roominfo.getMembers().size(), chatTitleMembers, socket);

                        System.out.print("????????? : " + RoomNum + " ????????? : " + roominfo.getMembers().size());
                        System.out.print("?????? : ");
                        for (String s : members) {
                            System.out.print(s + ", ");
                        }
                        Chatting_List.add(chatting);
                        System.out.print("?????? ?????? ???????????? " + Chatting_List.size() + "??? ?????????. ??? ????????? ");
                        for (int i = 0; i < Chatting_List.size(); i++) {
                            System.out.println(Chatting_List.get(i).getRoom_number() + ", ");
                        }
                        System.out.println(RoomNum + "?????? ?????? ???????????? ???????????????.");

                        System.out.println("????????? ?????? ??????");


                    }/* ????????????????????? ???????????? ?????? */
                    if (args[0].matches("/310")) { //msg: "/310 " + roomNum
                        System.out.println("RECV ????????? :" + args[1] + "??? ????????? ?????? ????????? ??????");
//                        int RoomNum = Integer.parseInt(args[1]);
//
//                        chatting = new chat_Frame(RoomNum, membersNoCurrentUser, socket);
//                        Chatting_List.add(chatting);
//                        System.out.println("Chatting_List.size() = " + Chatting_List.size());
//                        for (int i = 0; i < Chatting_List.size(); i++) {
//                            System.out.println("Chatting_List.get(i) = " + Chatting_List.get(i));
//                        }
                        //????????? ?????????
                        RoomInfo roomInfo = getRoomInfoByRoomId(args[1]);
                        int RoomNum = roomInfo.getRoomNum();
                        ArrayList<String> members = roomInfo.getMembers();

                        //????????? ??????????????? ?????? ????????? ????????? ????????? ?????????.
                        ArrayList<String> chatTitleMembers = new ArrayList<>();
                        for (String member : members) {
                            if (!member.equals(Login_Frame.userName))
                                chatTitleMembers.add(member);
                        }

                        chatting = new chat_Frame(RoomNum, roomInfo.getMembers().size(), chatTitleMembers, socket);

                        System.out.print("????????? : " + RoomNum + " ????????? : " + roomInfo.getMembers().size());
                        System.out.print("?????? : ");
                        for (String s : roomInfo.getMembers()) {
                            System.out.print(s + ", ");
                        }
                        System.out.println("??? ???????????? ????????? ?????????????????????");

                        Chatting_List.add(chatting);
                        System.out.print("?????? ?????? ???????????? " + Chatting_List.size() + "??? ?????????. ??? ????????? ");
                        for (int i = 0; i < Chatting_List.size(); i++) {
                            System.out.println(Chatting_List.get(i).getRoom_number() + ", ");
                        }

                    }/* ????????? ?????? ?????? */ else if (args[0].matches("/320")) { //msg :  /320 " +room_number"
                        System.out.print("????????? ");
                        for (int i = 0; i < Chatting_List.size(); i++) {
                            System.out.print(Chatting_List.get(i).getRoom_number() + "??? ");
                            if (Chatting_List.get(i).getRoom_number() == Integer.parseInt(args[1])) {
                                System.out.print("????????? ");
                                System.out.println(Chatting_List.get(i).getRoom_number() + "?????? ?????????????????????.");
                                Chatting_List.remove(i);
                            }
                        }
                    }
                    /* ????????? ?????? */
                    if (args[0].matches("/502")) { //502  roomid message username
                        String message = args[2];
                        System.out.println("RECV message : " + message);
                        RoomInfo roomInfoByRoomId = getRoomInfoByRoomId(args[1]);

                        /* ??????????????? ??? ????????? ?????? */
                        /* ???????????? */
                        LocalTime time = LocalTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                        String currentTime = time.format(formatter);
                        String currentTime12;
                        if (Integer.parseInt(currentTime.substring(0, 2)) >= 13)
                            currentTime12 = "??????" + (Integer.parseInt(currentTime.substring(0, 2)) - 12) + ":" + currentTime.substring(3, 5);
                        else
                            currentTime12 = "??????" + currentTime;
                        Message m = new Message(message, args[3], currentTime12);
                        roomInfoByRoomId.setMessages(m);
                        System.out.println("????????? : " + roomInfoByRoomId.getRoomNum() + "??? " + roomInfoByRoomId.getMessages().get(0).getBody() + " ???????????? ?????????????????????.");

                        /* ????????? ????????? */
                        int index = Room_list.indexOf(getRoomInfoByRoomId(String.valueOf(roomInfoByRoomId.getRoomNum())));
                        ArrayList<JPanel> chattingButtonList = chattingListGUI.getChattingButtonList();
                        JPanel jPanel = chattingButtonList.get(index);
                        Component[] components = jPanel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JLabel) {
                                try {
                                    JLabel component1 = (JLabel) component;
                                    if (component1.getToolTipText().equals("lastMessageLabel"))
                                        component1.setText(roomInfoByRoomId.getLastMessage());
                                } catch (NullPointerException e) {

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
                            if (message.equals("(??????)") || message.equals("(?????????)") || message.equals("(??????)") || message.equals("(??????)") || message.equals("(??????)") || message.equals("(??????)"))
                                chatting.printEmoticon_Left(message, args[3]);
                            else chatting.makeLeftBubble(message, args[3]);
                            JScrollBar vertical = chatting.pane.getVerticalScrollBar();
                            vertical.setValue(vertical.getMaximum());
                        } catch (NullPointerException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    if (args[0].matches("/555")) { // msg : /555 rommid
                        /* ????????? ????????? */

                        int roomid = Integer.parseInt(args[1]);
                        int index = Room_list.indexOf(getRoomInfoByRoomId(String.valueOf(roomid)));
                        RoomInfo roomInfoByRoomId = getRoomInfoByRoomId(String.valueOf(roomid));
                        ArrayList<JPanel> chattingButtonList = chattingListGUI.getChattingButtonList();
                        JPanel jPanel = chattingButtonList.get(index);
                        Component[] components = jPanel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JLabel) {
                                try {
                                    JLabel component1 = (JLabel) component;
                                    if (component1.getToolTipText().equals("lastMessageLabel"))
                                        component1.setText(roomInfoByRoomId.getLastMessage());
                                } catch (NullPointerException e) {

                                }
                            }
                        }
                        chattingListGUI.getContentPane().revalidate();
                        chattingListGUI.revalidate();
                        chattingListGUI.repaint();
                    }
                    if (args[0].matches("/503")) { // UserName RoomNum
                        System.out.println(msg);
                        System.out.println("/503");
                        int arrlen = dis.readInt();
                        byte[] bytes = new byte[arrlen];
                        dis.readFully(bytes);
                        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                        BufferedImage bufferedImage = ImageIO.read(inputStream);
                        System.out.println("?????? ??????");

                        RoomInfo roomInfoByRoomId = getRoomInfoByRoomId(String.valueOf(args[2]));

                        LocalTime time = LocalTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                        String currentTime = time.format(formatter);
                        String currentTime12;
                        if (Integer.parseInt(currentTime.substring(0, 2)) >= 13)
                            currentTime12 = "??????" + (Integer.parseInt(currentTime.substring(0, 2)) - 12) + ":" + currentTime.substring(3, 5);
                        else
                            currentTime12 = "??????" + currentTime;

                        int index = Room_list.indexOf(getRoomInfoByRoomId(String.valueOf(roomInfoByRoomId.getRoomNum())));
                        ArrayList<JPanel> chattingButtonList = chattingListGUI.getChattingButtonList();
                        JPanel jPanel = chattingButtonList.get(index);
                        Component[] components = jPanel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JLabel) {
                                try {
                                    JLabel component1 = (JLabel) component;
                                    if (component1.getToolTipText().equals("lastMessageLabel"))
                                        component1.setText(roomInfoByRoomId.getLastMessage());
                                    System.out.println(roomInfoByRoomId.getLastMessage());
                                } catch (NullPointerException e) {

                                }
                            }
                        }
//                        ObjectInputStream ois = new ObjectInputStream(is);
                        ImageIcon img = new ImageIcon(bufferedImage);
//                        while(img ==null){
//                             img = (ImageIcon)ois.readObject();
//                        }
                        chat_Frame chatting = getChatFrameByRoomNum(args[2]);
                        chatting.printImage_Left(img, args[1],currentTime12);
                        Message m = new Message("/img", args[1], currentTime12,img);
                        roomInfoByRoomId.setMessages(m);
                        JScrollBar vertical = chatting.pane.getVerticalScrollBar();
                        vertical.setValue(vertical.getMaximum());
//                        ImageIcon img = (ImageIcon)ois.readObject();
//                        if(img != null){
//                            JFrame jf = new JFrame();
//                          503  JLabel jl = new JLabel();
//                            jf.add(jl);
//                            jl.setIcon(img);
//                            jf.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
//                            jf.setVisible(true);
//                        }
                        chattingListGUI.getContentPane().revalidate();
                        chattingListGUI.revalidate();
                        chattingListGUI.repaint();
                    }

                    if (args[0].equals("/601")) { //????????? ????????????msg: 601 id
                        System.out.println(args[1] + "??? ????????? ???????????? ???????????????");
                        profile_filename = "img/UserProfile/" + args[1] + ".png";
                        /* ??????????????? ????????? ?????? */
                        ImageIcon pi = PictureRound.setImageRound(profile_filename, 60);
                        //profileButton.setIcon(pi);
                        System.out.println("args[1] = " + args[1]);
                        ArrayList<JPanel> friendsButtonList = chattingListGUI.getFriendsListGUI().getChattingButtonList();
                        ArrayList<JPanel> chattingButtonList = chattingListGUI.getChattingButtonList();


                        /* ??????????????? ????????? ?????? */
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

                        /* ???????????? ????????? ?????? */
                        for (JPanel jPanel : chattingButtonList) { //?????? ?????? ????????? ?????????

                            //????????? ?????? Label??? text??? ????????????
                            JLabel namePan = (JLabel) jPanel.getComponent(0);
                            String nameLableString = namePan.getText();

                            //????????? ????????? ?????? ??????????????? index ?????????
                            int index = -1;
                            System.out.println("nameLableString = " + nameLableString.replace(" ", ""));
                            String peoplestr = nameLableString.replace(" ", "");
                            String[] split = peoplestr.split(",");
                            for(int i =0; i<split.length; i++){
                                if(split[i].equals(args[1])) index =i;
                            }
                            int numOfpeople = split.length + 1;
                            System.out.println("numOfpeople = " + numOfpeople);

                            switch (numOfpeople) {
                                case 2:
                                    pi = PictureRound.setImageRound(profile_filename, 60);
                                    break;
                                case 3:
                                    pi = PictureRound.setImageRound(profile_filename, 40);
                                    break;
                                case 4:
                                    pi = PictureRound.setImageRound(profile_filename, 33);
                                    break;
                                case 5:
                                    pi = PictureRound.setImageRound(profile_filename, 30);
                                    break;
                            }

                            System.out.println("index = " + index);

                            if (index != -1) { //?????? ???????????? ?????? ???????????? ?????????
                                System.out.println("???????????? ?????????");

                                Component[] components = jPanel.getComponents();  //?????? ????????? Panel??????
                                for (Component component : components) {
                                    if (component instanceof RoundedButton) { //RoundButton?????? ??????

                                        Optional<String> toolTipText = Optional.ofNullable(((RoundedButton) component).getToolTipText()); //nullable ???.
                                        if (toolTipText.isPresent()) { //null??? ????????????
                                            System.out.println(" ??????");
                                            String text = toolTipText.get(); //?????? button??? toolTipText??? ????????????
                                            System.out.println("text = " + text);
                                            if (text.equals(String.valueOf(index))) { // tooTipText??? index??? ???????????????
                                                System.out.println("toolTipText = " + toolTipText);
                                                ((RoundedButton) component).setIcon(pi); //???????????? ????????????.
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
                                    //cf.refreshTopProfile();
                                }
                            }

                        for (chat_Frame chat_frame : Chatting_List) {
                            MiniProfileManager_chatroom designManager = chat_frame.getDesignManager();
                            ArrayList userNames = chat_frame.getUserName();
                            int numOfPeople = chat_frame.getNumOfPeople();
                            String name = args[1];
                            switch (numOfPeople) {
                                case 2:
                                    pi = PictureRound.setImageRound(profile_filename, 50);
                                    break;
                                case 3:
                                    pi = PictureRound.setImageRound(profile_filename, 30);
                                    break;
                                case 4:
                                    pi = PictureRound.setImageRound(profile_filename, 23);
                                    break;
                                case 5:
                                    pi = PictureRound.setImageRound(profile_filename, 20);
                                    break;
                            }


                            int index2 = -1;

                            for (int i = 0; i < userNames.size(); i++) {
                                String userName = (String) userNames.get(i);
                                if (userName.equals(name)) {
                                    index2 = i;
                                }
                            }

                            System.out.println("******************");
                            System.out.println("index 2 = " + index2);
                            System.out.println("numOfPeople = " + numOfPeople);
                            System.out.println("******************");
                            JLabel changeProfileButton = null;
                            if(numOfPeople == 2) {
                                changeProfileButton = designManager.getMiniProfileDesign().getProfileButton();
                            }else if(numOfPeople == 3) {

                                if (index2 == 1) {
                                    changeProfileButton = designManager.getMiniProfileDesign().getProfileButton1();
                                } else if (index2 == 0) {
                                    changeProfileButton = designManager.getMiniProfileDesign().getProfileButton();
                                }
                            }else if(numOfPeople == 4) {
                                if (index2 == 0) {
                                    changeProfileButton = designManager.getMiniProfileDesign().getProfileButton();
                                } else if (index2 == 1) {
                                    changeProfileButton = designManager.getMiniProfileDesign().getProfileButton1();
                                } else if (index2 == 2) {
                                    changeProfileButton = designManager.getMiniProfileDesign().getProfileButton2();
                                }
                            }else if(numOfPeople>=5) {
                                if (index2 == 0) {
                                    changeProfileButton = designManager.getMiniProfileDesign().getProfileButton();
                                } else if (index2 == 1) {
                                    changeProfileButton = designManager.getMiniProfileDesign().getProfileButton1();
                                } else if (index2 == 2) {
                                    changeProfileButton = designManager.getMiniProfileDesign().getProfileButton2();
                                } else if (index2 == 3) {
                                    changeProfileButton = designManager.getMiniProfileDesign().getProfileButton3();
                                }
                            }
                            if(index2 != -1) {
                                changeProfileButton.setIcon(pi);
                                chat_frame.getContentPane().revalidate();
                                chat_frame.getContentPane().repaint();
                                chat_frame.invalidate();
                                chat_frame.revalidate();
                                chat_frame.repaint();
                            }
                        }

                        revalidate();
                        repaint();
                    }

                    if (args[0].equals("/601")) { //????????? ????????????
                        System.out.println(args[1] + "??? ????????? ???????????? ???????????????");
                        profile_filename = "img/UserProfile/" + Login_Frame.userName + ".png";
                        ImageIcon pi = PictureRound.setImageRound(profile_filename, 60);
                        profileButton.setIcon(pi);
                        System.out.println("????????? ????????????");
                        chattingListGUI.friendsListGUI.getFriendsListGUI().repaint();
                        chattingListGUI.friendsListGUI.getFriendsListGUI().revalidate();

                        revalidate();
                        repaint();
                    }

                    if (args.length > 1) {
                        if (args[1].equals("/101")) {
                            System.out.println("Recv: ??????????????? ??????????????? ??????");
                            userName = args[0];
                            chattingListGUI = new ChattingListGUI(socket, args[0], this);
                            chattingListGUI.setVisible(true);
                            setVisible(false);
                        }
                        if (args[1].equals("/103")) {
                            System.out.println("Recv: ??????????????? ??????????????? ??????");
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
                    } // catch?????? ??????
                } // ???????? catch????????????

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
            System.out.println("???????????? ChatFrame??? ?????????");
            for (chat_Frame chat_frame : Chatting_List) {
                System.out.println("?????? chat_frame ????????? = " + chat_frame.getRoom_number());
                System.out.println("?????? ????????? = " + roomNum);
                if (chat_frame.getRoom_number() == Integer.parseInt(roomNum))
                    return chat_frame;
            }

            return null;
        }

        //????????? ????????? ?????? ????????? ??????, ????????? -1 ??????
        public int check_RoomNum(String name) {
            int result = -1;
            System.out.println("Room_list.size() = " + Room_list.size());
            for (int i = 0; i < Room_list.size(); i++) {
                System.out.println("Room_list " + i + "?????? ????????? : " + Room_list.get(i).getMembersToString());
                System.out.println("?????? name = " + name);
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

    //????????? ?????? , "/501 ????????? ???????????????"??? ????????? ??? ????????? ??????
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
        fis.close();
        dos.writeInt((int) file.length());
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
