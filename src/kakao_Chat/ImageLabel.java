package kakao_Chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import static kakao_Chat.design.pictureEdit.FileSelector.ImageSeletorByLink;

public class ImageLabel extends JLabel implements ActionListener, MouseListener {
    private String path;
    public ImageLabel(ImageIcon img, String path) {
        setIcon(img);
        this.path = path;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JFrame ImageFrame = new JFrame();
        JLabel ImageLabel = new JLabel();
        ImageIcon img;
        try {
            img = ImageSeletorByLink(path);
            ImageLabel.setIcon(img);
            ImageFrame.add(ImageLabel);
            setBounds(900, 100, img.getIconWidth(), img.getIconHeight());
            setVisible(true);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
