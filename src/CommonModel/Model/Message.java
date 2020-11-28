package CommonModel.Model;

import java.io.Serializable;

public class Message implements Serializable {

    private String message;

    public Message(String message) {
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
