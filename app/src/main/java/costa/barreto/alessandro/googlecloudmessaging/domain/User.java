package costa.barreto.alessandro.googlecloudmessaging.domain;

/**
 * Created by Alessandro on 17/08/2015.
 */
public class User {
    private String registrationId;

    public User() {}
    public User(String registrationId) {
        this.registrationId = registrationId;
    }


    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}
