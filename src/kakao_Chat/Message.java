package kakao_Chat;

public class Message {
    private String body;
    private String sendFrom;
    private String time;

    public Message(String body, String sendFrom, String time) {
        this.body = body;
        this.sendFrom = sendFrom;
        this.time = time;
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
