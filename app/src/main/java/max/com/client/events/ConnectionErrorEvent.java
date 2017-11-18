package max.com.client.events;

/**
 * Created by Maxim on 10/20/2017.
 */

public class ConnectionErrorEvent {
    private String error;

    public ConnectionErrorEvent(String error) {
        this.error = error;
    }
}
