package max.com.client.model;

import java.util.List;

/**
 * Created by Maxim on 9/27/2017.
 */

public class Message {
    private String userPhone;
    private String senderPhone;
    private String message;
    private String time;

    public Message(String message) {
        this.message = message;
    }

    public Message(String userPhone, String senderPhone, String message, String time) {
        this.userPhone = userPhone;
        this.senderPhone = senderPhone;
        this.message = message;
        this.time = time;
    }

    private List<Message> messages;


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        userPhone = userPhone;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        senderPhone = senderPhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
