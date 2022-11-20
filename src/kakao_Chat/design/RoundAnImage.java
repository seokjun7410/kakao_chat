package kakao_Chat.design;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RoundAnImage {

	public static void main(String[] args) {
		
		System.out.println("========= " + RoundAnImage.class.getSimpleName() + " START ===================!!");

		long t1=System.currentTimeMillis();
		try {
			start(new File("d:\\temp\\bean.jpg"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long t2=System.currentTimeMillis();
		int duration=(int)((t2-t1)/1000);
		
		System.out.println("============================ DONE (duration= " + duration + "s) ============================");
	}	
	
	public static void start(File file) throws IOException {
		
	    BufferedImage originalImg = ImageIO.read(file);

        int width = originalImg.getWidth();
        int height = originalImg.getHeight();

        BufferedImage bim = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = bim.createGraphics();

        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);

        g2.setClip(new RoundRectangle2D.Double(0, 0, width, height, width / 4, height / 4));

        g2.drawImage(originalImg, 0, 0, null);

        g2.dispose();

        ImageIO.write(bim, "PNG", new File( file.getParentFile(), getBaseName(file.getName()) + "-rounded.png"));		
	}
	
	public static String getBaseName(String name) {
		int idx=name.lastIndexOf(".");
		if (idx>=0) {
			return name.substring(0, idx);
		}
		else {
			return name;
		}		
	}
}
