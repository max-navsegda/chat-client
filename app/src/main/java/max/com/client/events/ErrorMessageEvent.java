package max.com.client.events;

/**
 * Created by Maxim on 10/19/2017.
 */

public class ErrorMessageEvent {
    private String message;

    public ErrorMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}