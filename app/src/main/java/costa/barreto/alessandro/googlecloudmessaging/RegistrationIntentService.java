package costa.barreto.alessandro.googlecloudmessaging;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import costa.barreto.alessandro.googlecloudmessaging.domain.User;
import costa.barreto.alessandro.googlecloudmessaging.domain.WrapObjToNetwork;
import costa.barreto.alessandro.googlecloudmessaging.network.NetworkConnection;

/**
 * Created by Alessandro on 17/08/2015.
 * Class responsavel para enviar o RegistrationID para servidor.
 */
public class RegistrationIntentService extends IntentService{
    public static final String LOG = "LOG";

    public RegistrationIntentService() {
        super(LOG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean status = preferences.getBoolean("status", false);

        synchronized (LOG){
            InstanceID instanceID = InstanceID.getInstance(this);

            try {

                if( !status ){
                    String token = instanceID.getToken("954207819889",GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);

                    Log.i(LOG, "TOKEN: " + token);

                    preferences.edit().putBoolean("status", token != null && token.trim().length() > 0 ).apply();

                    //sendRegistrationId(token);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void sendRegistrationId( String token ){
        User user = new User();
        user.setRegistrationId( token );

        NetworkConnection
                .getInstance(this)
                .execute( new WrapObjToNetwork(user, "save-user"), RegistrationIntentService.class.getName() );
    }

}
