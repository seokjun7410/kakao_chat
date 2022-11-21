package kakao_Chat.design.friendslist_drawLine;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BottomDrawPanel extends JPanel{
	private int x1,y1,x2,y2;
	private Color color;
	
	public BottomDrawPanel(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		color = new Color(225,225,225);
	}
	
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}
