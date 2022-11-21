package kakao_Chat.design.friendslist_drawLine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

import javax.swing.JTextField;

public class BottomLineJTextField extends JTextField {

	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawLine(0, 30, 266, 30);
    }
}