package kakao_Chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class Chatting {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	
	public Chatting(String name,int mainX,int mainY) {
		initialize(name,mainX,mainY);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name,int mainX,int mainY) {
		frame = new JFrame();
		frame.setBounds(100, 100, 405, 639);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(mainX+50, mainY+50);
		//frame.setLocationRelativeTo(frame);
		
		JLabel chattingName = new JLabel(name);
		frame.getContentPane().add(chattingName, BorderLayout.NORTH);
		
		frame.setVisible(true);
	}

}
