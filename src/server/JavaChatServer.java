package server;

// Java Chatting Server

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import kakao_Chat.RoomInfo;
import kakao_Chat.User;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class JavaChatServer extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    JTextArea textArea;
    private JTextField txtPortNumber;
    private Vector UserLoginInfo = new Vector();
    private ServerSocket socket;
    private Socket client_socket;
    private Vector UserVec = new Vector();
    private Vector RoomVec = new Vector();
    private static final int BUF_LEN = 128;
    public int recent_Num = 0;
    public static int room_Num = -1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JavaChatServer frame = new JavaChatServer();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public JavaChatServer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 338, 386);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 10, 300, 244);
        contentPane.add(scrollPane);

        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);

        JLabel lblNewLabel = new JLabel("Port Number");
        lblNewLabel.setBounds(12, 264, 87, 26);
        contentPane.add(lblNewLabel);

        txtPortNumber = new JTextField();
        txtPortNumber.setHorizontalAlignment(SwingConstants.CENTER);
        txtPortNumber.setText("30000");
        txtPortNumber.setBounds(111, 264, 199, 26);
        contentPane.add(txtPortNumber);
        txtPortNumber.setColumns(10);

        JButton btnServerStart = new JButton("Server Start");
        btnServerStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    socket = new ServerSocket(Integer.parseInt(txtPortNumber.getText()));
                } catch (NumberFormatException | IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                AppendText("Chat Server Running..");
                btnServerStart.setText("Chat Server Running..");
                btnServerStart.setEnabled(false);
                txtPortNumber.setEnabled(false);
                AcceptServer accept_server = new AcceptServer();
                accept_server.start();
            }
        });
        btnServerStart.setBounds(12, 300, 300, 35);
        contentPane.add(btnServerStart);
    }

    class AcceptServer extends Thread {
        @SuppressWarnings("unchecked")
        public void run() {
            while (true) {
                try {
                    AppendText("Waiting new clients ...");
                    client_socket = socket.accept();
                    AppendText("??????: " + client_socket);
                    UserService new_user = new UserService(client_socket);
                    new_user.start();

                } catch (IOException e) {
                    AppendText("accept() error");
                    // System.exit(0);
                }
            }
        }

    }

    public void AppendText(String str) {

        textArea.append(str + "\n");
        textArea.setCaretPosition(textArea.getText().length());
    }

    class UserService extends Thread {
        private InputStream is;
        private OutputStream os;
        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket client_socket;
        private Vector user_vc;
        public String UserName = "";

        public UserService(Socket client_socket) {
            // TODO Auto-generated constructor stub

            this.client_socket = client_socket;
            this.user_vc = UserVec;
            try {
                is = client_socket.getInputStream();
                dis = new DataInputStream(is);
                os = client_socket.getOutputStream();
                dos = new DataOutputStream(os);
                // line1 = dis.readUTF();
                // /login user1 ==> msg[0] msg[1]
//				byte[] b = new byte[BUF_LEN];
//				dis.read(b);
//				
//				String line1 = new String(b);
//				AppendText(line1);
//				String[] msg = line1.split(" ");
//				UserName = msg[1].trim();
//				UserStatus = "O"; 
//				Login();
            } catch (Exception e) {
                AppendText("userService error");
            }
        }

        public String getUserName() {
            return this.UserName;
        }

        public void Login() {
            AppendText(UserName + " ?????? ?????????????????????");
            UserVec.add(this);
            WriteOne(UserName + " /101");
            String msg = UserName + " /login" + " /101";
            WriteAll(msg);
            AppendText("?????? ??????: " + UserVec.size());
        }

        public void Logout() {
            String msg = "[" + UserName + "]???????????? ???????????? ??????????????????????.\n";
            UserVec.removeElement(this);
            WriteAll(msg);
            AppendText("[" + UserName + "] ?????? ???????????? ???????????????. ??????: " + UserVec.size());
        }

        public void LoginFail() {
            AppendText(UserName + " ?????? ???????????? ?????????????????????");
            // WriteOne("Welcome to Java chat server\n");
            WriteOne(UserName + " /102");
        }

        public void SignUp() {
            AppendText(UserName + " ?????? ?????? ?????????????????????");
            // WriteOne("Welcome to Java chat server\n");
            UserVec.add(this);
            WriteOne(UserName + " /103");
            AppendText("?????? ??????: " + UserVec.size());
        }

        public void WriteAll(String str) {
            for (int i = 0; i < user_vc.size(); i++) {
                UserService user = (UserService) user_vc.elementAt(i);

                user.WriteOne(str);
//				if (user.UserStatus == "O")
//					user.WriteOne(str);
            }
        }

        public void WriteOthers(String str) {
            for (int i = 0; i < user_vc.size(); i++) {
                UserService user = (UserService) user_vc.elementAt(i);
//				if (user!=this && user.UserStatus == "O")
//					user.WriteOne(str);
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
            }
            for (i = 0; i < bb.length; i++)
                packet[i] = bb[i];
            return packet;
        }

        public void sendTo(String name, String msg) {
            for (int i = 0; i < UserVec.size(); i++) {
                UserService user = (UserService) user_vc.elementAt(i);
                if (user.getUserName().equals(name)) {
                    user.WriteOne(msg);
                    break;
                }
            }
        }

        public void sendImage(String name, byte[] bytes) throws IOException {
            for (int i = 0; i < UserVec.size(); i++) {
                UserService user = (UserService) user_vc.elementAt(i);
                if (user.getUserName().equals(name)) {
                    user.sendOneImg(bytes);
                    break;
                }
            }
        }

        public void sendOneImg(byte[] bytes) throws IOException {
//        ObjectOutputStream oos = new ObjectOutputStream(os);
//        //String file_name = userName+"_"+String.valueOf(file_num++)+".png";
//        System.out.println("sendImage:" + path);
//        BufferedImage img = ImageIO.read(new File(path));
//        ImageIcon ic =  new ImageIcon(img);
//        oos.writeObject(ic);
//        oos.flush();
            byte[] b = bytes;
            dos.writeInt(b.length);
            dos.flush();
            dos.write(b, 0, b.length);
            dos.flush();
        }

        public void WriteOne(String msg) {
            try {
                // dos.writeUTF(msg);
                byte[] bb;
                bb = MakePacket(msg);
                dos.write(bb, 0, bb.length);
            } catch (IOException e) {
                AppendText("dos.write() error");
                try {
                    dos.close();
                    dis.close();
                    client_socket.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                Logout();
            }
        }

        @SuppressWarnings("unchecked")
        public void run() {
            while (true) {
                try {
                    // String msg = dis.readUTF();
                    byte[] b = new byte[BUF_LEN];
                    int ret;
                    ret = dis.read(b);
                    if (ret < 0) {
                        AppendText("dis.read() < 0 error");
                        try {
                            dos.close();
                            dis.close();
                            client_socket.close();
                            Logout();
                            break;
                        } catch (Exception ee) {
                            ee.printStackTrace();
                            break;
                        } // catch?????? ??????
                    }
                    String msg = new String(b, "euc-kr");
                    msg = msg.trim();
                    AppendText(msg);
                    //System.out.println("ServcerRecv = " + msg);

                    String[] args = msg.split(" ");

                    if (args[0].matches("/100") && args.length >= 3) { // ????????? "/100 id pw"
                        System.out.println("Recv: ????????? " + args[1] + ", ???????????? " + args[2]);
                        String id = args[1].trim();
                        String pw = args[2].trim();
                        if (SearchUserInfo(id, pw) == 1) { // ????????? ??????
                            UserName = id;
                            AppendText("login");
                            Login();
                        } else if (SearchUserInfo(id, pw) == -2) { // ????????? ??????
                            UserName = id;
                            AppendText("fail");
                            LoginFail();
                        } else if (SearchUserInfo(id, pw) == -1) { // ?????? ?????? ??????
                            UserName = id;
                            User new_user = new User(id, pw, assign_UserNum());
                            AppendText("new");
                            UserLoginInfo.add(new_user);
                            SignUp();
                        }
                    } else if (args[0].matches("/200")) { // ?????? ?????? /200 " + id
                        System.out.println("Recv: ?????? \"" + args[1] + "\"??? ???????????? ????????????");
                        String id = args[1].trim();
                        if (SearchUserInfo(id) == -1) { // ?????? ?????? ??????
                            WriteOne("/202");
                        } else { // ?????? ?????? ??????
                            int userNum = SearchUserInfo(id);
                            WriteOne("/201 " + ((User) UserLoginInfo.get(userNum)).getId() + " "
                                    + ((User) UserLoginInfo.get(userNum)).getImage());
                        }
                        /* ????????? ????????????  */
                    } else if (args[0].matches("/300")) { //msg: /300 2 " + User_name
                        System.out.println("Recv: ????????? ??????????????? ??????");
                        System.out.print("RECV MSG: ");
                        for (String arg : args) {
                            System.out.print(arg + " ");
                        }
                        System.out.println("");
                        RoomInfo new_Room; // ??? ??????
                        int room_num = assign_RoomNum(); //??? ?????? ??????
                        System.out.println("????????? ???????????? " + room_num);
                        int size = Integer.parseInt(args[1]); //??? ??????
                        ArrayList<String> members = new ArrayList<String>();

                        // SendMessage ??????
                        String send_msg = String.valueOf(room_num);
                        StringBuffer temp = new StringBuffer(); //?????? ?????????
                        temp.append(send_msg);

                        for (int i = 0; i < size; i++) {
                            String name = args[i + 2];
                            members.add(name);
                            temp.append(" ").append(name);

                        }
                        send_msg = temp.toString(); //= "roomID currentUser user1 user2 ..."

                        //????????? ?????? ?????? => ?????????, ?????????, ???????????????, ??????????????????
                        new_Room = new RoomInfo(room_num, size, members, "");
                        RoomVec.add(new_Room);

                        //???????????? ???????????? sendMessage
                        for (int i = 0; i < Integer.parseInt(args[1]); i++) { //args[1] = ?????????
                            sendTo(args[i + 2], "/301 " + send_msg); //send_msg : /301 ????????? ??????1 ??????2 ...
                            System.out.println("TO : " + args[i + 2] + "?????? ???????????? ???????????? ????????? SEND ");
                            User user = UserFindByName(args[i + 2]);
                            user.setChattingCount(user.getChattingCount() + 1);
                            System.out.println(args[i + 2] + "??? ?????????????????? " + user.getChattingCount() + "??? ????????????");
                        }

                        //????????? ???????????? sendMessage
                        //WriteOne("/302 " + send_msg); //send_msg : /302 ????????? ??????1 ??????2 ...
                        System.out.println("????????? ????????? " + args[2] + " ?????? "+ room_num+"??? ????????? ??????");
                        System.out.println("SEND MSG: /302 "+send_msg);
                        sendTo(args[2], "/302 " + send_msg);
//						WriteAll(send_msg);


                    }/* LoginFrame?????? ????????? ?????? ?????? */
                    else if (args[0].matches("/310")) {//msg: "/310 " + roomNum + " " + chatName
                        System.out.println("Recv: ?????????????????? ???????????? ?????? ????????? ????????? ???????????????.");
                        System.out.println("SEND " + args[0] + " " + args[1] + " " + args[2]);
                        //WriteOne(args[0]+" "+args[1]+" "+args[2]);
                        sendTo(args[2], args[0] + " " + args[1]);
                    }/* ????????? ???????????? */
                    else if (args[0].matches("/320")) {//msg : /320 " +room_number+" "+Login_Frame.userName"
                        System.out.println("????????? ??????????????? ??????");
                        sendTo(args[2],"/320 "+args[1]);
                        System.out.println(args[2]+"?????? ????????? "+args[1]+"??? ?????? ??????");

                    } else if (args[0].matches("/500")) { // ????????? ?????? //500 roomid message Senduser
                        String send_msg = "/502 " + args[1] + " " + args[2]+" "+ args[3]; // 502  roomid message
                        System.out.println("RECV: ?????????" + args[1] + "?????? " + args[2] +" "+ args[3]+" ??? ????????????");

                        //???????????? ???????????? USER?????? ????????? ??????
                        ArrayList<String> recvUsers = getRecvUser(args[1]);

                        System.out.print(args[3]+"??? ");
                        for (String recvUser : recvUsers) {
                            if (!args[3].equals(recvUser)) {//??????????????? ?????? ????????? ??????
                                sendTo(recvUser, send_msg);
                                System.out.print(recvUser + ", ");
                            }else{ //????????????????????? lastmessage ????????????
                                sendTo(recvUser, "/555 "+args[1]);
                            }
                        }
                        System.out.println("?????? ???????????????.");


                    } else if (args[0].matches("/501")) { // ??????, ?????? ??????
                        int arrlen = dis.readInt();
                        byte[] bytes = new byte[arrlen];
                        dis.readFully(bytes);
                        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                        BufferedImage bufferedImage = ImageIO.read(inputStream);
                        System.out.println("?????? ??????");

//                        ObjectInputStream ois = new ObjectInputStream(is);
                        ImageIcon img = new ImageIcon(bufferedImage);
//                        while(img ==null){
//                             img = (ImageIcon)ois.readObject();
//                        }

                        String send_msg = "/503 " + args[2] + " "+args[1];
                        ArrayList<String> recvUsers = getRecvUser(args[1]);
                        ArrayList<String> recvUsers2 = getRecvUser(args[1]);
                        for (String recvUser : recvUsers) { // ???????????? ?????????????????? ????????? ?????? ??????
                            if (!args[2].equals(recvUser)) {//??????????????? ?????? ????????? ??????
                                sendTo(recvUser, send_msg);
                                System.out.print(recvUser + "img send !!");
                            }
                        }

                        //oos.writeObject(img);
//
                        for (String recvUser : recvUsers2) { // ?????? ??????
                            if (!args[2].equals(recvUser)) {//??????????????? ?????? ????????? ??????
                                sendImage(recvUser, bytes);
                                System.out.print(recvUser + ", ");
                            }
                            else sendTo(recvUser, "/555 "+args[1]);
                        }

                    }

                        else if (args[0].matches("/600")) { // ????????? ?????? ??????
                        System.out.println("????????? ???????????? =");
                        String send_msg = "/601 " + getUserName();
                        System.out.println("SEND : "+send_msg); //msg : 601 username
                        WriteOne(send_msg); // ???????????? ??????????????? ?????? ??????????????? ??????
                        WriteAll(send_msg);
                    }

//					else if (args[1].matches("/exit")) {
//						Logout();
//						break;
//					} else if (args[1].matches("/list")) {
//						WriteOne("User list\n");
//						WriteOne("Name\tStatus\n");
//						WriteOne("-----------------------------\n");
//						for (int i = 0; i < user_vc.size(); i++) {
//							UserService user = (UserService) user_vc.elementAt(i);
////							WriteOne(user.UserName + "\t" + user.UserStatus + "\n");
//						}
//						WriteOne("-----------------------------\n");
//					} else if (args[1].matches("/sleep")) {
//						UserStatus = "S";
//					} else if (args[1].matches("/wakeup")) {
//						UserStatus = "O";
                    else if (args[1].matches("/to")) {
                        for (int i = 0; i < user_vc.size(); i++) {
                            UserService user = (UserService) user_vc.elementAt(i);
//							if (user.UserName.matches(args[2]) && user.UserStatus.matches("O")) {
//								String msg2 = "";
//								for (int j = 3;j<args.length;j++) {
//									msg2 += args[j];
//									if (j < args.length - 1)
//										msg2 += " ";
//								}
//								user.WriteOne("[??????????] " + args[0] + " " + msg2 + "\n");
//								break;
//							}		
                        }
                    } else {
//						UserStatus = "O";
                        WriteAll(msg + "\n"); // Write All
                    }
                } catch (IOException e) {
                    AppendText("dis.read() error");
                    System.out.println("************************");
                    e.printStackTrace();
                    System.out.println("************************");

                    try {
                        dos.close();
                        dis.close();
                        client_socket.close();
                        Logout();
                        break;
                    } catch (Exception ee) {
                        break;
                    }
                }
            }
        }

        public int assign_UserNum() {
            int num = recent_Num + 1;
            recent_Num = num;
            return num;
        }

        public int assign_RoomNum() {

            return ++room_Num;
        }

        private User UserFindByName(String userName) {
            for (Object userObj : UserLoginInfo) {
                User user = (User) userObj;
                if (user.getId().equals(userName))
                    return user;
            }
            return null;
        }

        public int SearchUserInfo(String user_id, String user_pw) {
            int found = -1;
            for (int i = 0; i < UserLoginInfo.size(); i++) {
                if (((User) UserLoginInfo.get(i)).getId().equals(user_id)) {
                    if (((User) UserLoginInfo.get(i)).getPw().equals(user_pw)) { // ?????????, ???????????? ??????
                        found = 1;
                        break;
                    } else { // ???????????? ??????, ????????? ??????
                        found = -2;
                        break;
                    }
                }
            }
            return found; // ???????????? ???????????? ?????? ?????? 0 ??????
        }

        public int SearchUserInfo(String user_id) {
            int found = -1;
            for (int i = 0; i < UserLoginInfo.size(); i++) {
                if (((User) UserLoginInfo.get(i)).getId().equals(user_id)) {
                    found = i;
                    break;
                }
            }
            return found; // ???????????? ???????????? ?????? ?????? -1 ??????
        }
    }

    /* ??????????????? ?????? ROOM??? ????????? ?????? ????????? ??????, ????????? null */
    private ArrayList<String> getRecvUser(String roomNum) {

        for (Object roomObj : RoomVec) {
            RoomInfo room = (RoomInfo) roomObj;
            if (room.getRoomNum() == Integer.parseInt(roomNum)) {
                return room.getMembers();
            }
        }
        return null;
    }
}
