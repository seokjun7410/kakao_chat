package kakao_Chat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

public class chat_Frame extends JFrame implements MouseListener, MouseMotionListener,ActionListener
{	
	private Point initialClick;
	private JPanel title_bar;
	private JPanel Panel_1;
	private JButton btn_exit;
	private Point comPoint;
	
	public chat_Frame()
	{
		setResizable(false);
		setUndecorated(true);		
//		setTitle("");
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		//상단바 구성
		title_bar = new JPanel();
		title_bar.setPreferredSize(new Dimension(380,34));
		title_bar.setLayout(null);
		title_bar.setBackground(new Color(186,206,224));
		title_bar.addMouseListener(this);
		title_bar.addMouseMotionListener(this);
		
		//상단바 X버튼
		btn_exit = new JButton("x");
		btn_exit.setBounds(338,2, 40, 30);
		btn_exit.setPreferredSize(new Dimension(30,30));
		btn_exit.addActionListener(this);
		title_bar.add(btn_exit);
		
		//상단바를 제외한 부분 패널
		Panel_1 = new JPanel();
		Panel_1.setPreferredSize(new Dimension(380,30));
		Panel_1.setLayout(null);
		Panel_1.setBackground(new Color(186,206,224));
		Panel_1.setLayout(new BorderLayout());
		
		//상단 프로필 패널
		JPanel Profile_panel = new JPanel();
		Profile_panel.setPreferredSize(new Dimension(380,70));
		Panel_1.add(Profile_panel,BorderLayout.NORTH);
		
		//중간 채팅창 패널
		JPanel chat_panel = new JPanel();
		chat_panel.setPreferredSize(new Dimension(380,70));
		chat_panel.setBackground(new Color(186,206,224));
		Panel_1.add(chat_panel,BorderLayout.CENTER);
		
		//하단 텍스트 패널
		JPanel text_panel = new JPanel();
		text_panel.setPreferredSize(new Dimension(380,150));
		text_panel.setLayout(new BorderLayout());
		JTextArea text_area = new JTextArea(7,13);
		text_area.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		text_panel.add(text_area,BorderLayout.CENTER);
		Panel_1.add(text_panel,BorderLayout.SOUTH);
		
		//최하단 전송, 이모티콘 패널
		JPanel send_panel = new JPanel();
		send_panel.setPreferredSize(new Dimension(380,50));
		send_panel.setBackground(new Color(186,206,224));
		text_panel.add(send_panel,BorderLayout.SOUTH);
		
		add(title_bar,BorderLayout.NORTH);
		add(Panel_1,BorderLayout.CENTER);
		
		setBounds(100, 100, 380, 650);
		setVisible(true);

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



		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btn_exit)) {
				this.dispose();
			}
			
		}


	
}