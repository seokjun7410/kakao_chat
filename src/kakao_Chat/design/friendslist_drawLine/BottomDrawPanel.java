package kakao_Chat.design.friendslist_drawLine;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BottomDrawPanel extends JPanel{
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(225,225,225));
        g.drawLine(25, 75, 290, 75);
    }
}
