package kakao_Chat.design.pictureEdit;

import javax.swing.JFrame;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class PictureRound {

//    public static void main (String [] args) {
//    	ImageIcon name = jFileChooserUtil(100);
//    	
//    	JFrame jframe = new JFrame();
//    	jframe.add(new JLabel(name));
//        jframe.setBounds(900, 100, 380, 635);
//        jframe.setVisible(true);
//    }
  public static ImageIcon jFileChooserUtil(int size){
        
        String folderPath = "";
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // 디렉토리 설정
        chooser.setCurrentDirectory(new File("/")); // 현재 사용 디렉토리를 지정
        chooser.setAcceptAllFileFilterUsed(true);   // Fileter 모든 파일 적용 
        chooser.setDialogTitle("타이틀"); // 창의 제목
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 파일 선택 모드
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("png", "jpg"); // filter 확장자 추가
        chooser.setFileFilter(filter); // 파일 필터를 추가
        
        int returnVal = chooser.showOpenDialog(null); // 열기용 창 오픈
        
        if(returnVal == JFileChooser.APPROVE_OPTION) { // 열기를 클릭 
            File file = chooser.getSelectedFile();
            String sname = file.getAbsolutePath();
            
            
            BufferedImage master;
			try {
				master = ImageIO.read(file);
				int diameter = Math.min(master.getWidth(), master.getHeight());
	            BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);
	
	            Graphics2D g2d = mask.createGraphics();
	            applyQualityRenderingHints(g2d);
	            g2d.fillOval(0, 0, diameter - 1, diameter - 1);
	            g2d.dispose();
	
	            BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
	            g2d = masked.createGraphics();
	            applyQualityRenderingHints(g2d);
	            int x = (diameter - master.getWidth()) / 2;
	            int y = (diameter - master.getHeight()) / 2;
	            g2d.drawImage(master, x, y, null);
	            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
	            g2d.drawImage(mask, 0, 0, null);
	            g2d.dispose();
	            Image ap = new ImageIcon(masked).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
	            ImageIcon ic =  new ImageIcon(ap);
	            return ic;
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}

          
            
        }else if(returnVal == JFileChooser.CANCEL_OPTION){ // 취소를 클릭
            System.out.println("cancel"); 
            folderPath = "";
        }
		return null;   
    }
  
  public static ImageIcon setImageRound(String image, int size) throws IOException {
	  BufferedImage master = ImageIO.read(new File(image));
	  int diameter = Math.min(master.getWidth(), master.getHeight());
      BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);
  	
      Graphics2D g2d = mask.createGraphics();
      applyQualityRenderingHints(g2d);
      g2d.fillOval(0, 0, diameter - 1, diameter - 1);
      g2d.dispose();

      BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
      g2d = masked.createGraphics();
      applyQualityRenderingHints(g2d);
      int x = (diameter - master.getWidth()) / 2;
      int y = (diameter - master.getHeight()) / 2;
      g2d.drawImage(master, x, y, null);
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
      g2d.drawImage(mask, 0, 0, null);
      g2d.dispose();
      Image ap = new ImageIcon(masked).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
      ImageIcon ic =  new ImageIcon(ap);
      return ic;
  }
  
private static void applyQualityRenderingHints(Graphics2D g2d) {
	g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	
}
}

