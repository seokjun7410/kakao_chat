package kakao_Chat.design.pictureEdit;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class editImage {
 public static ImageIcon EditImage(ImageIcon image, int size) {
	 double div = image.getIconHeight()/(double)image.getIconWidth();
     Image ap = image.getImage().getScaledInstance( size,(int) Math.round(size*div), Image.SCALE_SMOOTH);
     ImageIcon ic =  new ImageIcon(ap);
     return ic;
 }
}
