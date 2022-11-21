package kakao_Chat;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

public class Profile_Frame extends JFrame implements MouseListener, MouseMotionListener,ActionListener{
	private TranslucentLabel title_bar;
	private JButton btn_exit;
	private Point comPoint;
	private JPanel menu_bar;

	public Profile_Frame()
	{
	
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
		add(background_panel,BorderLayout.CENTER);
		
		//상단바 구성
		title_bar = new TranslucentLabel();
		title_bar.setPreferredSize(new Dimension(300,34));
		title_bar.setLayout(null);
		title_bar.setBackground(new Color(132, 139, 145));
		title_bar.addMouseListener(this);
		title_bar.addMouseMotionListener(this);
		
		//상단바 X버튼
		Image x = new ImageIcon("img/x.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		ImageIcon x_button = new ImageIcon(x);
		btn_exit = new JButton(x_button);
		btn_exit.setBounds(277,4, 20, 20);
		btn_exit.setPreferredSize(new Dimension(20,20));
		btn_exit.setBackground(new Color(132, 139, 145));
		btn_exit.addActionListener(this);
		title_bar.add(btn_exit);
		
		JPanel center_panel = new JPanel();
		center_panel.setBackground(new Color(132, 139, 145));
		center_panel.setLayout(new BorderLayout());
		background_panel.add(center_panel,BorderLayout.CENTER);
		
		JPanel Profile_panel = new JPanel();
		Profile_panel.setPreferredSize(new Dimension(300,170));
		Profile_panel.setBackground(new Color(132, 139, 145));
		center_panel.add(Profile_panel,BorderLayout.SOUTH);
		
		JPanel Profile_panel2 = new JPanel();
		Profile_panel2.setPreferredSize(new Dimension(130,170));
		Profile_panel2.setBackground(new Color(132, 139, 145));
		Profile_panel.add(Profile_panel2);
		
		Image p = new ImageIcon("img/user_default2.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
		ImageIcon p_img = new ImageIcon(p);
		JLabel profile_label = new JLabel(p_img);
		profile_label.setBackground(new Color(132, 139, 145));
		Profile_panel2.add(profile_label);
		
		JLabel name_label = new JLabel("이름");
		name_label.setForeground(Color.white);
		name_label.setFont(new Font("맑은 고딕",Font.BOLD, 15));
		Profile_panel2.add(name_label);
		
		
		//하단 메뉴바
		menu_bar = new JPanel();
		menu_bar.setPreferredSize(new Dimension(380,100));
		menu_bar.setLayout(new GridLayout(1,3));
		menu_bar.setBackground(new Color(132, 139, 145));
		menu_bar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(156,162,167)));
		
		//empty_menu_bar[1].setText("test");
		Image bg_img = new ImageIcon("img/chat_1.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
		ImageIcon bg = new ImageIcon(bg_img);
		JLabel img = new JLabel(bg);
		//empty_menu_bar[1].setPreferredSize(new Dimension(200,200));
		//empty_menu_bar[1].add(img);
		menu_bar.add(img);
		menu_bar.addMouseListener(this);

	
		add(menu_bar,BorderLayout.SOUTH);
		
		background_panel.add(title_bar,BorderLayout.NORTH);
		
		setBounds(100, 100, 300, 600);
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
		else if (e.getSource().equals(menu_bar)) {
//			menu_bar.setBackground(new Color(0,0,0,124));
//			revalidate();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if(e.getSource().equals(title_bar)) {
			comPoint = null;
		}
		else if (e.getSource().equals(menu_bar)) {
			chat_Frame chatting = new chat_Frame(1,"chatName");
			
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
