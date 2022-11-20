package kakao_Chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login_Frame extends JFrame implements MouseListener, MouseMotionListener,ActionListener, KeyListener{
	private JPanel title_bar;
	private JButton btn_exit,btn_login;
	private Point comPoint;
	private JTextField id_text;
	private JTextField pw_text;
	private JTextArea id_area, pw_area;
	ChattingListGUI window;
	
	public Login_Frame () {
		setResizable(false);
		setUndecorated(true);		
//		setTitle("");
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setBounds(100, 100, 380, 635);
		setVisible(true);
		
		
		JPanel id_panel = new JPanel();
		id_area = new JTextArea();
		id_area.setText(" 아이디  ");
		id_area.setEditable(false);
		id_area.setBackground(new Color(254,229,0));
		id_panel.setPreferredSize(new Dimension(380,40));
		id_text = new JTextField();
		id_text.setEditable(true);
		id_text.setBounds(100, 100, 380, 26);
		id_text.setPreferredSize(new Dimension(380,30));
		id_text.setColumns(20);
		id_panel.add(id_area);
		id_panel.add(id_text);
		id_panel.setBackground(new Color(254,229,0));
	
		
		JPanel pw_panel = new JPanel();
		pw_area = new JTextArea();
		pw_area.setText("비밀번호");
		pw_area.setEditable(false);
		pw_area.setBackground(new Color(254,229,0));
		pw_panel.setPreferredSize(new Dimension(380,40));
		pw_text = new JTextField();
		pw_text.setEditable(true);
		
		pw_text.setBounds(100, 100, 380, 26);
		pw_text.setPreferredSize(new Dimension(380,30));
		pw_text.setColumns(20);
		pw_panel.add(pw_area);
		pw_panel.add(pw_text);
		pw_panel.setBackground(new Color(254,229,0));
	
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(380,30));
		panel.setLayout(new FlowLayout());
		panel.setBackground(new Color(254,229,0));
		panel.add(id_panel);
		panel.add(pw_panel);
		add(panel,BorderLayout.CENTER);
		btn_login = new JButton("로그인");
		btn_login.addActionListener(this);
		panel.add(btn_login);
		title_bar = new JPanel();
		title_bar.setPreferredSize(new Dimension(380,34));
		title_bar.setLayout(null);
		title_bar.setBackground(new Color(254,229,0));
		title_bar.addMouseListener(this);
		title_bar.addMouseMotionListener(this);
		
		//상단바 X버튼
		btn_exit = new JButton("x");
		btn_exit.setBounds(338,2, 40, 30);
		btn_exit.setPreferredSize(new Dimension(30,30));
		btn_exit.addActionListener(this);
		title_bar.add(btn_exit);
		add(title_bar,BorderLayout.NORTH);
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
		if(e.getSource().equals(btn_exit)) {
			this.dispose();
		}
		else if(e.getSource().equals(btn_login)) {
			window = new ChattingListGUI(id_area.getText());
			window.setVisible(true);
			setVisible(false);

		}
		
	}

	public void mousePressed(MouseEvent e) 
	{ 
		if(e.getSource().equals(title_bar)) {
			comPoint = e.getPoint();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if(e.getSource().equals(title_bar)) {
			comPoint = null;
		}
	}

	public void mouseDragged(MouseEvent e) 
	{
		Point current = e.getLocationOnScreen();
		this.setLocation(current.x - comPoint.x,current.y - comPoint.y);
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
	public static void main(String[] args) {
		Login_Frame frame = new Login_Frame();
		frame.setVisible(true);
	}
}
