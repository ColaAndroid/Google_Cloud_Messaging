package costa.barreto.alessandro.googlecloudmessaging.domain;

/**
 * Created by Alessandro on 17/08/2015.
 */
public class PushMessage {
    private String title;
    private String message;


    public PushMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}