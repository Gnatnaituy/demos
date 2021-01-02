package proxy;

public class MailService {

    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
