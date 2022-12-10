package kakao_Chat;

import javax.swing.*;
import java.awt.*;

public class Message {
    private String body;
    private String sendFrom;
    private String time;
    private ImageIcon img;
    public Message(String body, String sendFrom, String time) {
        this.body = body;
        this.sendFrom = sendFrom;
        this.time = time;
    }
    public Message(String body, String sendFrom, String time,ImageIcon img) {
        this.body = body;
        this.sendFrom = sendFrom;
        this.time = time;
        this.img = img;
    }

    public ImageIcon getImg(){
        return img;
    }
    public String getBody() {
        return body;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public String getTime() {
        return time;
    }
}
