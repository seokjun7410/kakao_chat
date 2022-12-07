package kakao_Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static kakao_Chat.FriendsListGUI.ChatRoomEntered;

public class ImageFrame extends JFrame implements MouseListener, MouseMotionListener, ActionListener {
    private JButton btn_exit;
    private JPanel title_bar;
    private Point comPoint;
    public ImageFrame(ImageIcon img){
        setLayout(new BorderLayout());
        title_bar = new JPanel();
        title_bar.setPreferredSize(new Dimension(380, 35));
        title_bar.setLayout(new BorderLayout());
        title_bar.setBackground(Color.white);
        title_bar.addMouseListener(this);
        title_bar.addMouseMotionListener(this);
        btn_exit = new JButton("x");
        btn_exit.setFont(new Font("Arial", Font.PLAIN, 12));
        btn_exit.setPreferredSize(new Dimension(40, 35));
        btn_exit.setBackground(Color.white);
        btn_exit.setBorderPainted(false);
        btn_exit.addActionListener(this);
        title_bar.add(btn_exit);
        add(title_bar,BorderLayout.NORTH);
        title_bar.add(btn_exit,BorderLayout.EAST);
        setBounds(900, 100, img.getIconWidth(), img.getIconHeight()+35);
        setResizable(false);
        setUndecorated(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(title_bar)) {
            comPoint = e.getPoint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getSource().equals(title_bar)) {
            comPoint = null;
        }
    }

    public void mouseDragged(MouseEvent e) {
        Point current = e.getLocationOnScreen();
        this.setLocation(current.x - comPoint.x, current.y - comPoint.y);
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }



    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btn_exit)) {
            this.dispose();
        }
    }
}
