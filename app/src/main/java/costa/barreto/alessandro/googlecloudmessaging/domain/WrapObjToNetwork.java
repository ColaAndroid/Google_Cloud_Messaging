package costa.barreto.alessandro.googlecloudmessaging.domain;

/**
 * Created by Alessandro on 17/08/2015.
 */
public class WrapObjToNetwork {
    private User user;
    private String method;


    public WrapObjToNetwork(User user, String method) {
        this.user = user;
        this.method = method;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
