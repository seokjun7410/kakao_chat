package kakao_Chat;
import javax.swing.*;

import kakao_Chat.design.mini_profile.chatroom.MiniProfileDesign_chatroom;
import kakao_Chat.design.mini_profile.chatroom.MiniProfileManager_chatroom;
import kakao_Chat.design.mini_profile.chatroom.profile.TwoPeople;
import kakao_Chat.design.pictureEdit.PictureRound;
import kakao_Chat.design.pictureEdit.editImage;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Vector;

import static kakao_Chat.FriendsListGUI.ChatRoomEntered;
import static kakao_Chat.Login_Frame.sendImage;
import static kakao_Chat.Login_Frame.userName;
import static kakao_Chat.Profile_Frame.profile_filename;
import static kakao_Chat.design.pictureEdit.FileSelector.ImageSeletor;
import static kakao_Chat.design.pictureEdit.FileSelector.ImageSeletorByLink;
import static kakao_Chat.design.pictureEdit.PictureRound.setImageRound;
import static kakao_Chat.design.pictureEdit.editImage.EditImage;

public class chat_Frame extends JFrame implements MouseListener, MouseMotionListener, ActionListener, KeyListener {
    private Point initialClick;
    private JPanel title_bar;
    private JPanel Panel_1;
    private JLabel chat_text;
    private JButton btn_exit, btn_send, image_send;
    public JScrollPane pane;
    private Point comPoint;
    private int room_number;
    private String members;
    private JTextArea text_area;
    private JPanel chat_panel;
    private int chat_panel_height;
    private static final int BUF_LEN = 128;
    public static ArrayList<User> User_list;
    public int Size_list;
    private int numOfPeople;
    private JPanel chattingScrollPanel;
    private ArrayList<String> members_array;
    private String[] imgNames = new String[4];
    private ArrayList<Message> messages = new ArrayList<>();
    public static ArrayList<JPanel> chat_list = new ArrayList<>();
    private JPanel Profile_panel;

    private MiniProfileManager_chatroom manager;
    private ArrayList<String> userName;

    public int getRoom_number() {
        return room_number;
    }
    public MiniProfileManager_chatroom getDesignManager(){
        return manager;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }
    public ArrayList getUserName (){
        return userName;
    }

    public ArrayList<String> getMembers(){
        return members_array;
    }

    private RoomInfo roomInfo;

    public chat_Frame(int roomNum, int numOfPeople, ArrayList<String> un, Socket s) {

        System.out.println("??????????????? ??????????????? ?????????.");
        this.room_number = roomNum;
        this.numOfPeople = numOfPeople;
        this.userName = un;
        this.members_array = un;

        System.out.println("room_number = " + room_number);
        roomInfo = Login_Frame.ListenNetwork.getRoomInfoByRoomId(String.valueOf(roomNum));

        System.out.println(roomInfo.getRoomNum() + "??? ??? ????????? ??????????????????.");



        StringBuffer names = new StringBuffer();
        for (int i = 0; i < un.size(); i++) {
            names.append(un.get(i) + " ");
            imgNames[i] = "img/UserProfile/" + un.get(i) + ".png";
            System.out.println("imgNames[i] = " + imgNames[i]);
        }
        this.members = names.toString();
//		System.out.println("un = " + un);
//		System.out.println("user_names = " + user_names);
//		System.out.println("chat_number = " + chat_number);
        setResizable(false);
        setUndecorated(true);
//		setTitle("");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        //????????? ??????
        title_bar = new JPanel();
        title_bar.setPreferredSize(new Dimension(380, 34));
        title_bar.setLayout(null);
        title_bar.setBackground(new Color(186, 206, 224));
        title_bar.addMouseListener(this);
        title_bar.addMouseMotionListener(this);

        //????????? X??????
        btn_exit = new JButton("x");
        btn_exit.setBounds(338, 2, 40, 35);
        btn_exit.setBackground(new Color(186, 206, 224));
        btn_exit.setBorderPainted(false);
        btn_exit.setFont(new Font("Arial", Font.PLAIN, 14));
        btn_exit.setPreferredSize(new Dimension(30, 30));
        btn_exit.addActionListener(this);
        title_bar.add(btn_exit);

        //???????????? ????????? ?????? ??????
        Panel_1 = new JPanel();
        Panel_1.setPreferredSize(new Dimension(380, 30));
        Panel_1.setLayout(null);
        Panel_1.setBackground(new Color(186, 206, 224));
        Panel_1.setLayout(new BorderLayout());

        //?????? ????????? ??????
        Profile_panel = new JPanel();
        Profile_panel.setToolTipText("profile_panel");
        Profile_panel = new JPanel();
        Profile_panel.setPreferredSize(new Dimension(380, 50));
        Profile_panel.setLayout(new BorderLayout());
        Profile_panel.setBackground(new Color(186, 206, 224));

        //?????? ????????? ??????
        manager = MiniProfileManager_chatroom.getInstance();
        manager.setMiniProfileDesign_Chat(un.size() + 1, imgNames);
        manager.makeMiniProfile(Profile_panel);


        Profile_panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        //Profile_panel.add(imgPanel,BorderLayout.WEST);
        //?????? ??????, ?????? ?????? ?????? ??????
        JPanel user_info = new JPanel();
        user_info.setPreferredSize(new Dimension(380, 50));
        user_info.setLayout(new BorderLayout());
        user_info.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        Profile_panel.add(user_info, BorderLayout.CENTER);
        //?????? ?????? ??????
        JPanel user_name = new JPanel();
        user_name.setPreferredSize(new Dimension(380, 20));
        user_name.setBackground(new Color(186, 206, 224));
        user_name.setLayout(new BorderLayout());
        //???????????? Label ??????
        JLabel name = new JLabel(members);
        name.setToolTipText("name");
        name.setFont(new Font("?????? ??????", Font.BOLD, 14));
        user_name.add(name, BorderLayout.WEST);

        //?????? ??? ??????
        JPanel user_num = new JPanel();
        user_num.setPreferredSize(new Dimension(380, 20));
        user_num.setBackground(new Color(186, 206, 224));
        user_num.setLayout(new BorderLayout());
        JLabel num = new JLabel(String.valueOf(numOfPeople));
        num.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
        user_num.add(num, BorderLayout.CENTER);
        ImageIcon user_icon_s = new ImageIcon("img/user_s.png");
        Image user_s = user_icon_s.getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT);
        ImageIcon user_icon_s2 = new ImageIcon(user_s);
        JLabel img_2 = new JLabel(user_icon_s2);
        user_num.add(img_2, BorderLayout.WEST);

        user_info.setBackground(new Color(186, 206, 224));
        user_info.add(user_name, BorderLayout.NORTH);
        user_info.add(user_num, BorderLayout.CENTER);


        Panel_1.add(Profile_panel, BorderLayout.NORTH);

        //?????? ????????? ??????

        chattingScrollPanel = new JPanel();
        chattingScrollPanel.setPreferredSize(new Dimension(380, 70));
        chattingScrollPanel.setBackground(new Color(186, 206, 224));
        chattingScrollPanel.setLayout(new FlowLayout());
        chattingScrollPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));

        //?????????
        chat_panel_height = 0;
        chat_panel = new JPanel();
        chat_panel.setPreferredSize(new Dimension(380, 10));
        chat_panel.setBackground(new Color(186, 206, 224));
        chat_panel.setLayout(new FlowLayout());
        chat_panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));

        pane = new JScrollPane(chat_panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setBounds(0, 0, 380, 401);
        pane.setPreferredSize(new Dimension(380, 10));
        pane.getVerticalScrollBar().setUnitIncrement(14);
        chattingScrollPanel.setLayout(null);
        pane.setBorder(null);
        chattingScrollPanel.add(pane);

        //JScrollPane scrollPane = new JScrollPane(chat_view, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //scrollPane.setBounds(12, 10, 352, 340);
        //chat_panel.add(scrollPane,BorderLayout.SOUTH);
//		chat_text = new JLabel("test");
//		chat_text.setPreferredSize(new Dimension(100,300));
//		chat_text.setBackground(new Color(255,235,51));
//		chat_text.setOpaque(true);
//		chat_panel.add(chat_text);

        Panel_1.add(chattingScrollPanel, BorderLayout.CENTER);

        //?????? ????????? ??????
        JPanel text_panel = new JPanel();
        text_panel.setPreferredSize(new Dimension(380, 150));
        text_panel.setLayout(new BorderLayout());
        text_area = new JTextArea(7, 13);
        text_area.addKeyListener(this);
        text_area.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        text_area.setLineWrap(true);
        text_panel.add(text_area, BorderLayout.CENTER);
        Panel_1.add(text_panel, BorderLayout.SOUTH);

        //????????? ??????, ???????????? ??????
        //?????? ?????? ?????? ??????
        JPanel send_panel = new JPanel();
        send_panel.setPreferredSize(new Dimension(380, 50));
        send_panel.setLayout(new BorderLayout());
        ImageIcon send_img = new ImageIcon("img/btn_send.png");
        Image send_img2 = send_img.getImage().getScaledInstance(50, 30, Image.SCALE_SMOOTH);
        ImageIcon send_img3 = new ImageIcon(send_img2);
        btn_send = new JButton(send_img3);
        btn_send.setBackground(Color.white);
        btn_send.setBorderPainted(false);
        btn_send.setPreferredSize(new Dimension(50, 30));
        btn_send.addActionListener(this);
        send_panel.add(btn_send, BorderLayout.EAST);


        ImageIcon img_send = new ImageIcon("img/clip.png");
        Image img_send1 = img_send.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon img_send2 = new ImageIcon(img_send1);
        image_send = new JButton(img_send2);
        image_send.setBackground(Color.white);
        image_send.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        image_send.setBorderPainted(false);
        image_send.setPreferredSize(new Dimension(40, 30));
        image_send.addActionListener(this);
        send_panel.setBackground(Color.white);
        send_panel.add(image_send, BorderLayout.WEST);


        text_panel.add(send_panel, BorderLayout.SOUTH);


		add(title_bar, BorderLayout.NORTH);
		add(Panel_1, BorderLayout.CENTER);

		setBounds(510, 130, 380, 635);

		try {
			Optional<ArrayList<Message>> messages = Optional.ofNullable(this.roomInfo.getMessages());
			System.out.println("???????????? messages??? ?????????????????????");
			if (messages.isPresent()) {
				System.out.println("messages??? ???????????????.");
				for (Message message : messages.get()) {
					System.out.println("messages??? ????????? ???????????????.");
					System.out.println("message.getBody() = " + message.getBody());
					System.out.println("message.getSendFrom() = " + message.getSendFrom());
					System.out.println("Login_Frame.userName = " + Login_Frame.userName);
					if (message.getSendFrom().equals(Login_Frame.userName)) {
						if (message.getBody().equals("(??????)")
								|| message.getBody().equals("(?????????)")
								|| message.getBody().equals("(??????)")
								|| message.getBody().equals("(??????)")
								|| message.getBody().equals("(??????)")
								|| message.getBody().equals("(??????)"))
							printEmoticonInHistory(message.getBody(),message.getTime());
                        else if(message.getBody().equals("/img")){
                            printImage(message.getImg(),message.getTime());
                        }
						else
							makeRightBubbleInHistory(message.getBody(), message.getTime());

					} else {
                        System.out.println(message.getBody());
						if (message.getBody().equals("(??????)")
								|| message.getBody().equals("(?????????)")
								|| message.getBody().equals("(??????)")
								|| message.getBody().equals("(??????)")
								|| message.getBody().equals("(??????)")
								|| message.getBody().equals("(??????)"))
							printEmoticonInHistory_Left(message.getBody(),message.getSendFrom(),message.getTime());
                        else if(message.getBody().equals("/img")){

                            printImage_Left_InHistory(message.getImg(),message.getSendFrom(),message.getTime());
                        }
						else
							makeLeftBubbleInHistory(message.getBody(), message.getSendFrom(),message.getTime());

					}
				}
				revalidate();
			}
		} catch (NullPointerException e) {
			System.out.println("???????????? ???????????? ????????????.");
		} catch (IOException e) {
            throw new RuntimeException(e);
        }

        setVisible(true);
	}


	public void makeLeftBubble (String value, String Username) throws IOException {
		if(!value.equals("")) {
			JPanel profile_panel = new JPanel();
			profile_panel.setBackground(new Color(186,206,224));
			profile_panel.setLayout(null);
			ImageIcon user_profile = setImageRound("img/UserProfile/"+Username+".png",50);
			JLabel user_icon = new JLabel(user_profile);
			user_icon.setBounds(0,0,50,50);
			profile_panel.setPreferredSize(new Dimension(50,60));
			profile_panel.add(user_icon);

			JPanel parent_panel = new JPanel();
            parent_panel.setName(Username);
			parent_panel.setLayout(new BorderLayout());
			JPanel chat_view2 = new JPanel(); // ????????? ????????? ????????? ??????
			parent_panel.add(chat_view2,BorderLayout.CENTER);
			parent_panel.add(profile_panel,BorderLayout.WEST);
			chat_view2.setLayout(new BorderLayout());
			JLabel name_label = new JLabel(Username);
			name_label.setFont(new Font("????????????", Font.PLAIN, 12));
			name_label.setBackground(new Color(186,206,224));
			chat_view2.add(name_label,BorderLayout.NORTH);
			name_label.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
			JPanel chat_view = new JPanel(); // ????????? + ?????? ??????
			chat_view2.add(chat_view,BorderLayout.CENTER);
			chat_view.setLayout(new FlowLayout(FlowLayout.LEFT));
			chat_view.setBackground(new Color(186,206,224));
			chat_view2.setBackground(new Color(186,206,224));
			parent_panel.setBackground(new Color(186,206,224));
			parent_panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,5));
			JLabel label = new JLabel(value);
			label.setFont(new Font("????????????", Font.PLAIN, 12));
			label.setBorder(BorderFactory.createEmptyBorder(0,8,0,0));
			LeftArrowBubble LeftArrowBubble = new LeftArrowBubble();
			int width =(int) label.getPreferredSize().getWidth()+15;
			int height = 27;
			if(width > 208) {
				width = 208;
				height = (int)((width /208) +1)*27;
			}
			parent_panel.setPreferredSize(new Dimension(380,60));
			LeftArrowBubble.setPreferredSize(new Dimension(width,height));
			LeftArrowBubble.setBackground(new Color(255,255,255));
			System.out.println(label.getPreferredSize()+""+height);
			LeftArrowBubble.add(label);
			chat_view.add(LeftArrowBubble);
			chat_panel_height += 68;
			chat_panel.setPreferredSize(new Dimension(380,chat_panel_height));
			chat_panel.add(parent_panel);
            chat_list.add(parent_panel);

            /* ?????? ?????? */
            LocalTime time = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String currentTime = time.format(formatter);
            String currentTime12;
            if (Integer.parseInt(currentTime.substring(0, 2)) >= 13)
                currentTime12 = "??????" + (Integer.parseInt(currentTime.substring(0, 2)) - 12) + ":" + currentTime.substring(3, 5);
            else
                currentTime12 = "??????" + currentTime;

			JLabel timeLabel = new JLabel(currentTime12);
			timeLabel.setBounds(0,5,50,50);
			timeLabel.setFont(new Font("????????????", Font.PLAIN, 10));
			chat_view.add(timeLabel);

            revalidate();
        }
    }

    public void printEmoticon(String text) {

        ImageIcon emoticon;
        switch (text) {
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            case "(?????????)":
                emoticon = new ImageIcon("img/?????????.png");
                break;
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            default:
                emoticon = new ImageIcon();
                break;
        }
        ImageIcon edit = EditImage(emoticon, 70);
        int height = edit.getIconHeight();
        JPanel chat_view = new JPanel();

        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = time.format(formatter);
        String currentTime12;
        if (Integer.parseInt(currentTime.substring(0, 2)) >= 13)
            currentTime12 = "??????" + (Integer.parseInt(currentTime.substring(0, 2)) - 12) + ":" + currentTime.substring(3, 5);
        else
            currentTime12 = "??????" + currentTime;

        JLabel timeLabel = new JLabel(currentTime12);
        timeLabel.setBounds(0, 5, 50, 50);
        timeLabel.setFont(new Font("????????????", Font.PLAIN, 10));
        JPanel time_view = new JPanel();
        time_view.setLayout(new BorderLayout());
        time_view.setBackground(new Color(186, 206, 224));
        time_view.setPreferredSize(new Dimension(timeLabel.getWidth(), height));
        time_view.add(timeLabel, BorderLayout.SOUTH);
        chat_view.add(time_view);


        chat_view.setLayout(new FlowLayout(FlowLayout.RIGHT));
        chat_view.setBackground(new Color(186, 206, 224));
        chat_view.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        chat_view.setPreferredSize(new Dimension(380, height + 5));
        JLabel label = new JLabel(edit);
        chat_view.add(label);
        chat_panel_height += (height + 17);
        chat_panel.setPreferredSize(new Dimension(380, chat_panel_height));
        chat_panel.add(chat_view);



			revalidate();
		}
    public void printEmoticonInHistory(String text,String time) {

        ImageIcon emoticon;
        switch (text) {
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            case "(?????????)":
                emoticon = new ImageIcon("img/?????????.png");
                break;
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            default:
                emoticon = new ImageIcon();
                break;
        }
        ImageIcon edit = EditImage(emoticon, 70);
        int height = edit.getIconHeight();
        JPanel chat_view = new JPanel();

        JLabel timeLabel = new JLabel(time);
        timeLabel.setBounds(0, 5, 50, 50);
        timeLabel.setFont(new Font("????????????", Font.PLAIN, 10));
        JPanel time_view = new JPanel();
        time_view.setLayout(new BorderLayout());
        time_view.setBackground(new Color(186, 206, 224));
        time_view.setPreferredSize(new Dimension(timeLabel.getWidth(), height));
        time_view.add(timeLabel, BorderLayout.SOUTH);
        chat_view.add(time_view);


        chat_view.setLayout(new FlowLayout(FlowLayout.RIGHT));
        chat_view.setBackground(new Color(186, 206, 224));
        chat_view.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        chat_view.setPreferredSize(new Dimension(380, height + 5));
        JLabel label = new JLabel(edit);
        chat_view.add(label);
        chat_panel_height += (height + 17);
        chat_panel.setPreferredSize(new Dimension(380, chat_panel_height));
        chat_panel.add(chat_view);


        revalidate();
    }

    public void printEmoticon_Left(String text, String Username) throws IOException {
			ImageIcon emoticon;
			switch (text) {
				case "(??????)":
					emoticon = new ImageIcon("img/??????.png");
					break;
				case "(??????)":
					emoticon = new ImageIcon("img/??????.png");
					break;
				case "(?????????)":
					emoticon = new ImageIcon("img/?????????.png");
					break;
				case "(??????)":
					emoticon = new ImageIcon("img/??????.png");
					break;
				case "(??????)":
					emoticon = new ImageIcon("img/??????.png");
					break;
				case "(??????)":
					emoticon = new ImageIcon("img/??????.png");
					break;
				default:
					emoticon = new ImageIcon();
					break;
			}
			//????????? ?????? ??????
			JPanel profile_panel = new JPanel();
			profile_panel.setBackground(new Color(186,206,224));
			profile_panel.setLayout(null);
			ImageIcon user_profile = setImageRound("img/UserProfile/"+Username+".png",50);
			JLabel user_icon = new JLabel(user_profile);
			user_icon.setBounds(0,0,50,50);
			profile_panel.setPreferredSize(new Dimension(50,60));
			profile_panel.add(user_icon);

			//????????? ???????????? ??????
			JPanel parent_panel = new JPanel();
            parent_panel.setName(Username);
			parent_panel.setLayout(new BorderLayout());
			JPanel chat_view2 = new JPanel(); // ????????? ????????? ????????? ??????
			parent_panel.add(chat_view2,BorderLayout.CENTER);
			parent_panel.add(profile_panel,BorderLayout.WEST);
			chat_view2.setLayout(new BorderLayout());
			JLabel name_label = new JLabel(Username);
			name_label.setFont(new Font("????????????", Font.PLAIN, 12));
			name_label.setBackground(new Color(186,206,224));
			chat_view2.add(name_label,BorderLayout.NORTH);
			name_label.setBorder(BorderFactory.createEmptyBorder(0,10,5,0));
			JPanel chat_view = new JPanel(); // ????????? + ?????? ??????
			chat_view2.add(chat_view,BorderLayout.CENTER);
			chat_view.setLayout(new BorderLayout());
			chat_view.setBackground(new Color(186,206,224));
			chat_view2.setBackground(new Color(186,206,224));
			parent_panel.setBackground(new Color(186,206,224));
			parent_panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,5));

			ImageIcon edit = EditImage( emoticon,70);
			int height = edit.getIconHeight();
			chat_view.setLayout(new BorderLayout());
			chat_view.setBackground(new Color(186,206,224));
			chat_view.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
			parent_panel.setPreferredSize(new Dimension(380,height+18));
			JLabel imoticon_label = new JLabel(edit);
			chat_view.add(imoticon_label,BorderLayout.WEST);
			JPanel time_panel = new JPanel();
			time_panel.setLayout(new BorderLayout());
			chat_panel_height += (height +28);
			chat_panel.setPreferredSize(new Dimension(380,chat_panel_height));
			chat_panel.add(parent_panel);
            chat_list.add(parent_panel);
        /* ?????? ?????? */
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = time.format(formatter);
        String currentTime12;
        if (Integer.parseInt(currentTime.substring(0, 2)) >= 13)
            currentTime12 = "??????" + (Integer.parseInt(currentTime.substring(0, 2)) - 12) + ":" + currentTime.substring(3, 5);
        else
            currentTime12 = "??????" + currentTime;

			JLabel timeLabel = new JLabel(currentTime12);
			timeLabel.setBounds(0,5,50,50);
			timeLabel.setFont(new Font("????????????", Font.PLAIN, 10));
			JPanel time_view = new JPanel();
			time_view.setLayout(new BorderLayout());
			time_view.setBackground(new Color(186,206,224));
			time_view.setPreferredSize(new Dimension(timeLabel.getWidth(),height));
			time_view.add(timeLabel,BorderLayout.SOUTH);
			chat_view.add(time_view,BorderLayout.CENTER);

        revalidate();
    }

    public void printEmoticonInHistory_Left(String text,String Username,String time) throws IOException {
        ImageIcon emoticon;
        switch (text) {
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            case "(?????????)":
                emoticon = new ImageIcon("img/?????????.png");
                break;
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            case "(??????)":
                emoticon = new ImageIcon("img/??????.png");
                break;
            default:
                emoticon = new ImageIcon();
                break;
        }
        JPanel profile_panel = new JPanel();
        profile_panel.setBackground(new Color(186,206,224));
        profile_panel.setLayout(null);
        ImageIcon user_profile = setImageRound("img/UserProfile/"+Username+".png",50);
        JLabel user_icon = new JLabel(user_profile);
        user_icon.setBounds(0,0,50,50);
        profile_panel.setPreferredSize(new Dimension(50,60));
        profile_panel.add(user_icon);

        //????????? ???????????? ??????
        JPanel parent_panel = new JPanel();
        parent_panel.setName(Username);
        parent_panel.setLayout(new BorderLayout());
        JPanel chat_view2 = new JPanel(); // ????????? ????????? ????????? ??????
        parent_panel.add(chat_view2,BorderLayout.CENTER);
        parent_panel.add(profile_panel,BorderLayout.WEST);
        chat_view2.setLayout(new BorderLayout());
        JLabel name_label = new JLabel(Username);
        name_label.setFont(new Font("????????????", Font.PLAIN, 12));
        name_label.setBackground(new Color(186,206,224));
        chat_view2.add(name_label,BorderLayout.NORTH);
        name_label.setBorder(BorderFactory.createEmptyBorder(0,10,5,0));
        JPanel chat_view = new JPanel(); // ????????? + ?????? ??????
        chat_view2.add(chat_view,BorderLayout.CENTER);
        chat_view.setLayout(new BorderLayout());
        chat_view.setBackground(new Color(186,206,224));
        chat_view2.setBackground(new Color(186,206,224));
        parent_panel.setBackground(new Color(186,206,224));
        parent_panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,5));

        ImageIcon edit = EditImage( emoticon,70);
        int height = edit.getIconHeight();
        chat_view.setLayout(new BorderLayout());
        chat_view.setBackground(new Color(186,206,224));
        chat_view.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
        parent_panel.setPreferredSize(new Dimension(380,height+18));
        JLabel imoticon_label = new JLabel(edit);
        chat_view.add(imoticon_label,BorderLayout.WEST);
        JPanel time_panel = new JPanel();
        time_panel.setLayout(new BorderLayout());
        chat_panel_height += (height +28);
        chat_panel.setPreferredSize(new Dimension(380,chat_panel_height));
        chat_panel.add(parent_panel);
        chat_list.add(parent_panel);

        JLabel timeLabel = new JLabel(time);
        timeLabel.setBounds(0,5,50,50);
        timeLabel.setFont(new Font("????????????", Font.PLAIN, 10));
        JPanel time_view = new JPanel();
        time_view.setLayout(new BorderLayout());
        time_view.setBackground(new Color(186,206,224));
        time_view.setPreferredSize(new Dimension(timeLabel.getWidth(),height));
        time_view.add(timeLabel,BorderLayout.SOUTH);
        chat_view.add(time_view,BorderLayout.CENTER);

        revalidate();
    }

    public void makeRightBubbleInHistory(String value, String time) {
        JPanel chat_view = new JPanel();
        chat_view.setBounds(0, 0, 300, 30);
        chat_view.setLayout(new FlowLayout(FlowLayout.RIGHT));
        chat_view.setBackground(new Color(186, 206, 224));
        chat_view.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));


        JLabel timeLabel = new JLabel(time);
        timeLabel.setBounds(0, 5, 50, 50);
        timeLabel.setFont(new Font("????????????", Font.PLAIN, 10));
        chat_view.add(timeLabel);

        JLabel label = new JLabel(value);
        label.setFont(new Font("????????????", Font.PLAIN, 12));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        RightArrowBubble RightArrowBubble = new RightArrowBubble();
        int width = (int) label.getPreferredSize().getWidth() + 15;
        int height = 27;
        if (width > 208) {
            width = 208;
            height = (int) ((width / 208) + 1) * 27;
        }
        chat_view.setPreferredSize(new Dimension(380, height + 5));
        RightArrowBubble.setPreferredSize(new Dimension(width, height));
        RightArrowBubble.setBackground(new Color(255, 235, 51));
        System.out.print(label.getPreferredSize() + "" + height);
        RightArrowBubble.add(label);
        chat_view.add(RightArrowBubble);
        chat_panel_height += (height + 12);
        chat_panel.setPreferredSize(new Dimension(380, chat_panel_height));
        chat_panel.add(chat_view);

        revalidate();
    }

    public void makeLeftBubbleInHistory(String value,String Username, String time) throws IOException {
        System.out.println("make");
        System.out.println("value = " + value);
        if (!value.equals("")) {
            JPanel profile_panel = new JPanel();
            profile_panel.setBackground(new Color(186,206,224));
            profile_panel.setLayout(null);
            ImageIcon user_profile = setImageRound("img/UserProfile/"+Username+".png",50);
            JLabel user_icon = new JLabel(user_profile);
            user_icon.setBounds(0,0,50,50);
            profile_panel.setPreferredSize(new Dimension(50,60));
            profile_panel.add(user_icon);

            JPanel parent_panel = new JPanel();
            parent_panel.setName(Username);
            parent_panel.setLayout(new BorderLayout());
            JPanel chat_view2 = new JPanel(); // ????????? ????????? ????????? ??????
            parent_panel.add(chat_view2,BorderLayout.CENTER);
            parent_panel.add(profile_panel,BorderLayout.WEST);
            chat_view2.setLayout(new BorderLayout());
            JLabel name_label = new JLabel(Username);
            name_label.setFont(new Font("????????????", Font.PLAIN, 12));
            name_label.setBackground(new Color(186,206,224));
            chat_view2.add(name_label,BorderLayout.NORTH);
            name_label.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
            JPanel chat_view = new JPanel(); // ????????? + ?????? ??????
            chat_view2.add(chat_view,BorderLayout.CENTER);
            chat_view.setLayout(new FlowLayout(FlowLayout.LEFT));
            chat_view.setBackground(new Color(186,206,224));
            chat_view2.setBackground(new Color(186,206,224));
            parent_panel.setBackground(new Color(186,206,224));
            parent_panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,5));
            JLabel label = new JLabel(value);
            label.setFont(new Font("????????????", Font.PLAIN, 12));
            label.setBorder(BorderFactory.createEmptyBorder(0,8,0,0));
            LeftArrowBubble LeftArrowBubble = new LeftArrowBubble();
            int width =(int) label.getPreferredSize().getWidth()+15;
            int height = 27;
            if(width > 208) {
                width = 208;
                height = (int)((width /208) +1)*27;
            }
            parent_panel.setPreferredSize(new Dimension(380,60));
            LeftArrowBubble.setPreferredSize(new Dimension(width,height));
            LeftArrowBubble.setBackground(new Color(255,255,255));
            System.out.println(label.getPreferredSize()+""+height);
            LeftArrowBubble.add(label);
            chat_view.add(LeftArrowBubble);
            chat_panel_height += 68;
            chat_panel.setPreferredSize(new Dimension(380,chat_panel_height));
            chat_panel.add(parent_panel);
            chat_list.add(parent_panel);

            JLabel timeLabel = new JLabel(time);
            timeLabel.setBounds(0, 5, 50, 50);
            timeLabel.setFont(new Font("????????????", Font.PLAIN, 10));
            JPanel time_view = new JPanel();
            time_view.setLayout(new BorderLayout());
            time_view.setBackground(new Color(186, 206, 224));
            time_view.setPreferredSize(new Dimension(timeLabel.getWidth(), height));
            time_view.add(timeLabel, BorderLayout.SOUTH);
            chat_view.add(time_view);

            revalidate();
        }

    }

    public void makeRightBubble(String value) {
        if (!value.equals("")) {
            JPanel chat_view = new JPanel();
            chat_view.setBounds(0, 0, 300, 30);
            chat_view.setLayout(new FlowLayout(FlowLayout.RIGHT));
            chat_view.setBackground(new Color(186, 206, 224));
            chat_view.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

            /* ?????? ?????? */
            LocalTime time = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String currentTime = time.format(formatter);
            String currentTime12;
            if (Integer.parseInt(currentTime.substring(0, 2)) >= 13)
                currentTime12 = "??????" + (Integer.parseInt(currentTime.substring(0, 2)) - 12) + ":" + currentTime.substring(3, 5);
            else
                currentTime12 = "??????" + currentTime;

            JLabel timeLabel = new JLabel(currentTime12);
            timeLabel.setBounds(0, 5, 50, 50);
            timeLabel.setFont(new Font("????????????", Font.PLAIN, 10));
            chat_view.add(timeLabel);

            JLabel label = new JLabel(value);
            label.setFont(new Font("????????????", Font.PLAIN, 12));
            label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            RightArrowBubble RightArrowBubble = new RightArrowBubble();
            int width = (int) label.getPreferredSize().getWidth() + 15;
            int height = 27;
            if (width > 208) {
                width = 208;
                height = (int) ((width / 208) + 1) * 27;
            }
            chat_view.setPreferredSize(new Dimension(380, height + 5));
            RightArrowBubble.setPreferredSize(new Dimension(width, height));
            RightArrowBubble.setBackground(new Color(255, 235, 51));
            System.out.print(label.getPreferredSize() + "" + height);
            RightArrowBubble.add(label);
            chat_view.add(RightArrowBubble);
            chat_panel_height += (height + 12);
            chat_panel.setPreferredSize(new Dimension(380, chat_panel_height));
            chat_panel.add(chat_view);


            revalidate();
        }
    }
    public void printImage_Left_InHistory(ImageIcon img, String Username,String time) throws IOException {
        ImageIcon Image = EditImage(img,200);
        //????????? ?????? ??????
        JPanel profile_panel = new JPanel();
        profile_panel.setBackground(new Color(186,206,224));
        profile_panel.setLayout(null);
        ImageIcon user_profile = setImageRound("img/UserProfile/"+Username+".png",50);
        JLabel user_icon = new JLabel(user_profile);
        user_icon.setBounds(0,0,50,50);
        profile_panel.setPreferredSize(new Dimension(50,60));
        profile_panel.add(user_icon);

        //????????? ???????????? ??????
        JPanel parent_panel = new JPanel();
        parent_panel.setLayout(new BorderLayout());
        JPanel chat_view2 = new JPanel(); // ????????? ????????? ????????? ??????
        parent_panel.setName(Username);
        parent_panel.add(chat_view2,BorderLayout.CENTER);
        parent_panel.add(profile_panel,BorderLayout.WEST);
        chat_view2.setLayout(new BorderLayout());
        JLabel name_label = new JLabel(Username);
        name_label.setFont(new Font("????????????", Font.PLAIN, 12));
        name_label.setBackground(new Color(186,206,224));
        chat_view2.add(name_label,BorderLayout.NORTH);
        name_label.setBorder(BorderFactory.createEmptyBorder(0,10,5,0));
        JPanel chat_view = new JPanel(); // ????????? + ?????? ??????
        chat_view2.add(chat_view,BorderLayout.CENTER);
        chat_view.setLayout(new BorderLayout());
        chat_view.setBackground(new Color(186,206,224));
        chat_view2.setBackground(new Color(186,206,224));
        parent_panel.setBackground(new Color(186,206,224));
        parent_panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,5));

        int height = Image.getIconHeight();
        chat_view.setLayout(new BorderLayout());
        chat_view.setBackground(new Color(186,206,224));
        chat_view.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
        parent_panel.setPreferredSize(new Dimension(380,height+25));
        JLabel imoticon_label = new JLabel(Image);
        imoticon_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                ImageFrame image_frame = new ImageFrame(img);
                JLabel ImageLabel = new JLabel();
                ImageLabel.setIcon(img);
                image_frame.add(ImageLabel,BorderLayout.CENTER);
                image_frame.setVisible(true);
            }
        });
        chat_view.add(imoticon_label,BorderLayout.WEST);
        JPanel time_panel = new JPanel();
        time_panel.setLayout(new BorderLayout());
        chat_panel_height += (height +33);
        chat_panel.setPreferredSize(new Dimension(380,chat_panel_height));
        chat_panel.add(parent_panel);
        chat_list.add(parent_panel);

        JLabel timeLabel = new JLabel(time);
        timeLabel.setBounds(0,5,50,50);
        timeLabel.setFont(new Font("????????????", Font.PLAIN, 10));
        JPanel time_view = new JPanel();
        time_view.setLayout(new BorderLayout());
        time_view.setBackground(new Color(186,206,224));
        time_view.setPreferredSize(new Dimension(timeLabel.getWidth(),height));
        time_view.add(timeLabel,BorderLayout.SOUTH);
        chat_view.add(time_view,BorderLayout.CENTER);

        revalidate();
    }

    public void printImage_Left(ImageIcon img, String Username, String time) throws IOException {
        ImageIcon Image = EditImage(img,200);
        //????????? ?????? ??????
        JPanel profile_panel = new JPanel();
        profile_panel.setBackground(new Color(186,206,224));
        profile_panel.setLayout(null);
        ImageIcon user_profile = setImageRound("img/UserProfile/"+Username+".png",50);
        JLabel user_icon = new JLabel(user_profile);
        user_icon.setBounds(0,0,50,50);
        profile_panel.setPreferredSize(new Dimension(50,60));
        profile_panel.add(user_icon);

        //????????? ???????????? ??????
        JPanel parent_panel = new JPanel();
        parent_panel.setLayout(new BorderLayout());
        JPanel chat_view2 = new JPanel(); // ????????? ????????? ????????? ??????
        parent_panel.setName(Username);
        parent_panel.add(chat_view2,BorderLayout.CENTER);
        parent_panel.add(profile_panel,BorderLayout.WEST);
        chat_view2.setLayout(new BorderLayout());
        JLabel name_label = new JLabel(Username);
        name_label.setFont(new Font("????????????", Font.PLAIN, 12));
        name_label.setBackground(new Color(186,206,224));
        chat_view2.add(name_label,BorderLayout.NORTH);
        name_label.setBorder(BorderFactory.createEmptyBorder(0,10,5,0));
        JPanel chat_view = new JPanel(); // ????????? + ?????? ??????
        chat_view2.add(chat_view,BorderLayout.CENTER);
        chat_view.setLayout(new BorderLayout());
        chat_view.setBackground(new Color(186,206,224));
        chat_view2.setBackground(new Color(186,206,224));
        parent_panel.setBackground(new Color(186,206,224));
        parent_panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,5));


        int height = Image.getIconHeight();
        chat_view.setLayout(new BorderLayout());
        chat_view.setBackground(new Color(186,206,224));
        chat_view.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
        parent_panel.setPreferredSize(new Dimension(380,height+25));
        JLabel imoticon_label = new JLabel(Image);
        imoticon_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                ImageFrame image_frame = new ImageFrame(img);
                JLabel ImageLabel = new JLabel();
                ImageLabel.setIcon(img);
                image_frame.add(ImageLabel,BorderLayout.CENTER);
                image_frame.setVisible(true);
            }
        });
        chat_view.add(imoticon_label,BorderLayout.WEST);
        JPanel time_panel = new JPanel();
        time_panel.setLayout(new BorderLayout());
        chat_panel_height += (height +40);
        chat_panel.setPreferredSize(new Dimension(380,chat_panel_height));
        chat_panel.add(parent_panel);
        chat_list.add(parent_panel);
        /* ?????? ?????? */

        JLabel timeLabel = new JLabel(time);
        timeLabel.setBounds(0,5,50,50);
        timeLabel.setFont(new Font("????????????", Font.PLAIN, 10));
        JPanel time_view = new JPanel();
        time_view.setLayout(new BorderLayout());
        time_view.setBackground(new Color(186,206,224));
        time_view.setPreferredSize(new Dimension(timeLabel.getWidth(),height));
        time_view.add(timeLabel,BorderLayout.SOUTH);
        chat_view.add(time_view,BorderLayout.CENTER);

        revalidate();
    }


    public void printImage(ImageIcon img, String time){
        ImageIcon edit = EditImage(img,200);
        int height = edit.getIconHeight();
        JPanel chat_view = new JPanel();

        JLabel timeLabel = new JLabel(time);
        timeLabel.setBounds(0, 5, 50, 50);
        timeLabel.setFont(new Font("????????????", Font.PLAIN, 10));
        JPanel time_view = new JPanel();
        time_view.setLayout(new BorderLayout());
        time_view.setBackground(new Color(186, 206, 224));
        time_view.setPreferredSize(new Dimension(timeLabel.getWidth(), height));
        time_view.add(timeLabel, BorderLayout.SOUTH);
        chat_view.add(time_view);


        chat_view.setLayout(new FlowLayout(FlowLayout.RIGHT));
        chat_view.setBackground(new Color(186, 206, 224));
        chat_view.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        chat_view.setPreferredSize(new Dimension(380, height + 5));
        JLabel label = new JLabel(edit);
        label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                ImageFrame image_frame = new ImageFrame(img);
                JLabel ImageLabel = new JLabel();
                ImageLabel.setIcon(img);
                image_frame.add(ImageLabel,BorderLayout.CENTER);
                image_frame.setVisible(true);
            }
        });
        chat_view.add(label);
        chat_panel_height += (height + 25);
        chat_panel.setPreferredSize(new Dimension(380, chat_panel_height));
        chat_panel.add(chat_view);


        revalidate();
    }

		public class LeftArrowBubble extends JPanel {
			   private int radius = 10;
			   private int arrowSize = 3;
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


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btn_exit)) {
            System.out.println("???????????? ???????????????. Chatting_List ?????? ????????? " + room_number + "??? ???????????? ?????????.");
            Login_Frame.SendMessage("/320 " + room_number + " " + Login_Frame.userName);
            System.out.println("SEND /320 " + room_number + " " + Login_Frame.userName);
            ChatRoomEntered.remove((Object) room_number);
            this.dispose();
        } else if (e.getSource().equals(btn_send)) {
            int RoomNum;
            String value = text_area.getText();
            text_area.setText(""); // ???????????????????? ?????????????????? ???????????? ?????????????? ???????????????????? ????????????.
            text_area.requestFocus(); // ???????????????????? ?????????????????? ?????????????? ???????? ?????????? ??????????? ??????????????????????
            if (value.equals("(??????)") || value.equals("(?????????)") || value.equals("(??????)") || value.equals("(??????)") || value.equals("(??????)") || value.equals("(??????)"))
                printEmoticon(value);

            else makeRightBubble(value);

            /* ???????????? */
            LocalTime time = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String currentTime = time.format(formatter);
            String currentTime12;
            if (Integer.parseInt(currentTime.substring(0, 2)) >= 13)
                currentTime12 = "??????" + (Integer.parseInt(currentTime.substring(0, 2)) - 12) + ":" + currentTime.substring(3, 5);
            else
                currentTime12 = "??????" + currentTime;

            Message m = new Message(value, Login_Frame.userName, currentTime12);
            Login_Frame.ListenNetwork.getRoomInfoByRoomId(String.valueOf(room_number)).setMessages(m);

            Login_Frame.SendMessage("/500 " + room_number + " " + value + " " + Login_Frame.userName);
            System.out.println("\n" + Login_Frame.userName + "???(???) " + room_number + "????????? " + value + "??? ???????????? ???????????????.");




            // same value as
//				text_area.setCaretPosition(len); // place caret at the end (with no selection)
//		 		textArea.replaceSelection(msg + "\n");
            JScrollBar vertical = pane.getVerticalScrollBar();
            vertical.setValue( vertical.getMaximum() );

        }
			else if(e.getSource().equals(image_send)) {  //????????? ?????? ????????? ?????? ??????
				try {
					String path =ImageSeletor();   // ?????? ???????????? ?????? ????????? ??????
                    if(path != null) {
                        ImageIcon img = ImageSeletorByLink(path);  //?????? 200 ????????? ??????
                        /* ???????????? */
                        LocalTime time = LocalTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                        String currentTime = time.format(formatter);
                        String currentTime12;
                        if (Integer.parseInt(currentTime.substring(0, 2)) >= 13)
                            currentTime12 = "??????" + (Integer.parseInt(currentTime.substring(0, 2)) - 12) + ":" + currentTime.substring(3, 5);
                        else
                            currentTime12 = "??????" + currentTime;
                        printImage(img, currentTime12);

                        ImageIcon ic = ImageSeletorByLink(path);
                        Message m = new Message("/img", Login_Frame.userName, currentTime12, ic);
                        Login_Frame.ListenNetwork.getRoomInfoByRoomId(String.valueOf(room_number)).setMessages(m);

                        Login_Frame.SendMessage("/501 " + room_number + " " + Login_Frame.userName);
                        JScrollBar vertical = pane.getVerticalScrollBar();
                        vertical.setValue(vertical.getMaximum());
                        sendImage(path);
                    }
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}

    }

    public void refreshTopProfile(){
        manager = MiniProfileManager_chatroom.getInstance();
        manager.setMiniProfileDesign_Chat(members_array.size() + 1, imgNames);
        manager.makeMiniProfile(Profile_panel);
        revalidate();
        repaint();
    }
    public ArrayList<Integer> findChatPanel(String userName){
        ArrayList<Integer> chat = new ArrayList<>();
        for(int i=0; i < chat_list.size(); i++){
            JPanel jp =  chat_list.get(i);
            if(jp.getName().equals(userName)) {
                chat.add(i);
            }
        }
        return chat;
    }

    public void refreshProfile(ArrayList <Integer> chat,String userName) throws IOException {
        String profile_filename = "img/UserProfile/" + userName + ".png";
        ImageIcon pi = PictureRound.setImageRound(profile_filename, 50);
        for(int i=0; i<chat.size(); i++ ){
            Component component = chat_list.get(chat.get(i)).getComponent(1);
            if(component instanceof JPanel){
                Component profile_img = ((JPanel) component).getComponent(0);
                if(profile_img instanceof JLabel){
                    ((JLabel) profile_img).setIcon(pi);
                    revalidate();
                    repaint();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO ?????? ????????? ????????? ??????

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            btn_send.doClick();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO ?????? ????????? ????????? ??????


    }


}