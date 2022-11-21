package kakao_Chat;

import java.awt.EventQueue;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kakao_Chat.Profile_Frame.TranslucentLabel;
import kakao_Chat.design.friendslist_drawLine.BottomDoubleDrawPanel;
import kakao_Chat.design.friendslist_drawLine.BottomDrawPanel;
import kakao_Chat.design.friendslist_drawLine.BottomLineJTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class addFriendGUI extends Frame implements MouseListener, MouseMotionListener,ActionListener{
	private TranslucentLabel title_bar;
	private JButton btn_exit;
	private Point comPoint;
	private JPanel menu_bar;
	private JLabel empty_menu_bar[];
	private JTextField textField;
	private boolean editFrist;
	public addFriendGUI()
	{
		editFrist = true;
		
		setResizable(false);
		setUndecorated(true);		
//		setTitle("");
		setLayout(new BorderLayout());
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
		
		JLabel lblNewLabel = new JLabel("친구 추가");
		lblNewLabel.setBounds(12, 23, 69, 20);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 17));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 103, 300, 297);
		background_panel.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new BottomLineJTextField(){
			public void setBorder(Border bodrder){
				
			}
		};
		textField.setText("친구 이름");
		textField.setBounds(12, 41, 276, 34);
		panel_1.add(textField);
		textField.setColumns(10);
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(editFrist == true){
					editFrist = false;
					textField.setText("");
				}
				}
			});
		
		JButton btnNewButton = new JButton("친구 추가");
		btnNewButton.setBounds(195, 253, 93, 34);
		panel_1.add(btnNewButton);
	
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
