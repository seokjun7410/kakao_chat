package kakao_Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Profile_Frame extends JFrame implements MouseListener, MouseMotionListener,ActionListener{
	private TranslucentLabel title_bar;
	private JButton btn_exit;
	private Point comPoint;
	private JPanel menu_bar;
	private JLabel empty_menu_bar[];
	public Profile_Frame()
	{
	
		setResizable(false);
		setUndecorated(true);		
//		setTitle("");
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		
		JPanel background_panel = new JPanel();
		background_panel.setLayout(new BorderLayout());
		Image bg_img = new ImageIcon("img/default_back.jpg").getImage().getScaledInstance(380,650,Image.SCALE_DEFAULT);
		ImageIcon bg = new ImageIcon(bg_img);
		JLabel img = new JLabel(bg);
		background_panel.add(img);
		add(background_panel,BorderLayout.CENTER);
		
		//상단바 구성
		title_bar = new TranslucentLabel();
		title_bar.setPreferredSize(new Dimension(380,34));
		title_bar.setLayout(null);
		title_bar.setBackground(new Color(0, 0, 0, 0));
		title_bar.addMouseListener(this);
		title_bar.addMouseMotionListener(this);
		
		//상단바 X버튼
		btn_exit = new JButton("x");
		btn_exit.setBounds(338,2, 40, 30);
		btn_exit.setPreferredSize(new Dimension(30,30));
		btn_exit.addActionListener(this);
		title_bar.add(btn_exit);
		
		//하단 메뉴바
		menu_bar = new JPanel();
		menu_bar.setPreferredSize(new Dimension(380,120));
		menu_bar.setLayout(new GridLayout(1,3));
		menu_bar.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		
		empty_menu_bar = new JLabel[3];
		for(int i=0;i<3;i++){ 
			empty_menu_bar[i] = new JLabel();
		    menu_bar.add(empty_menu_bar[i]);
		}
		empty_menu_bar[1].setText("test");
		empty_menu_bar[1].addMouseListener(this);

	
		add(menu_bar,BorderLayout.SOUTH);
		
		background_panel.add(title_bar,BorderLayout.NORTH);
		
		setBounds(100, 100, 380, 635);
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
		else if (e.getSource().equals(empty_menu_bar[1])) {
			empty_menu_bar[1].setBackground(new Color(0,0,0,124));
			revalidate();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if(e.getSource().equals(title_bar)) {
			comPoint = null;
		}
		else if (e.getSource().equals(empty_menu_bar[1])) {
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