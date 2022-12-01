package kakao_Chat.design.pictureEdit;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
 
public class FileSelector {

	public static ImageIcon ImageSeletor(int size){
        
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
				double div = master.getHeight()/(double)master.getWidth() ;
	            Image ap = new ImageIcon(master).getImage().getScaledInstance( size,(int) Math.round(size*div), Image.SCALE_DEFAULT);
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

 
    public static ImageIcon ImageSeletor(){
        
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
 	            ImageIcon ic =  new ImageIcon(master);
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
}
