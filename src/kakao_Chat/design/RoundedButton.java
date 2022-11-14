package kakao_Chat.design;

//import java.awt.Color;
//import java.awt.FontMetrics;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Rectangle;
//import java.awt.RenderingHints;
//
//import javax.swing.Action;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JPanel;
//
//public class RoundedButton extends JButton {
//
//
//    public RoundedButton() {
//        super();
//        decorate();
//    }
//
//    public RoundedButton(String text) {
//        super(text);
//        decorate();
//    }
//
//    public RoundedButton(Action action) {
//        super(action);
//        decorate();
//    }
//
//    public RoundedButton(Icon icon) {
//        super(icon);
//        decorate();
//    }
//
//    public RoundedButton(String text, Icon icon) {
//        super(text, icon);
//        decorate();
//    }
//
//    protected void decorate() {
//        setBorderPainted(false);
//        setOpaque(false);
//    }
//    
//    @Override
//    protected void paintComponent(Graphics g) {
//    	super.paintComponent(g);
//    	
//        int width = getWidth();
//        int height = getHeight();
//
//        Graphics2D graphics = (Graphics2D) g;
//
//        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
////        if (getModel().isArmed()) {
////            graphics.setColor(getBackground().darker());
////        } else if (getModel().isRollover()) {
////            graphics.setColor(getBackground().brighter());
////        } else {
////            graphics.setColor(getBackground());
////        }
////        
//        graphics.drawRoundRect(0, 0, width, height, 30, 30);
//        
////        FontMetrics fontMetrics = graphics.getFontMetrics();
////        Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
//
////        int textX = (width - stringBounds.width) / 2;
////        int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
//
////        graphics.setColor(getForeground());
////        graphics.setFont(getFont());
////        graphics.drawString(getText(), textX, textY);
//        graphics.dispose();
//
//        
//    }
//}

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
 
public class RoundedButton extends JButton {
 
  public RoundedButton(String label,ImageIcon icon) {
    super(label,icon);
 
    //setBackground(Color.lightGray);
    setFocusable(false);
 
    /*
     These statements enlarge the button so that it 
     becomes a circle rather than an oval.
    */
    Dimension size = getPreferredSize();
    size.width = size.height = Math.max(size.width, size.height);
    setPreferredSize(size);
 
    /*
     This call causes the JButton not to paint the background.
     This allows us to paint a round background.
    */
    setContentAreaFilled(false);
  }
 
  protected void paintComponent(Graphics g) {
    if (getModel().isArmed()) {
      g.setColor(Color.gray);
    } else {
      g.setColor(getBackground());
    }
    g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
 
    super.paintComponent(g);
  }
 
  protected void paintBorder(Graphics g) {
    
    g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
  }
 
  // Hit detection.
  Shape shape;
 
  public boolean contains(int x, int y) {
    // If the button has changed size,  make a new shape object.
    if (shape == null || !shape.getBounds().equals(getBounds())) {
      shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
    }
    return shape.contains(x, y);
  }
}
 