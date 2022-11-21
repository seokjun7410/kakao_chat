package kakao_Chat.design.friendslist_drawLine;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BottomDoubleDrawPanel extends JPanel{

	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(225,225,225));
        g.drawLine(0, 90, 290, 90);
        g.setColor(Color.black);
        g.drawLine(10, 90, 90, 90);
    }
}
