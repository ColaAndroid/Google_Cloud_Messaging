package costa.barreto.alessandro.googlecloudmessaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by Alessandro on 17/08/2015.
 * permite que seja feita a atualização do token referente a app
 */
public class MyInstanceIDListenerService extends InstanceIDListenerService{
    private static final String LOG = "LOG";

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putBoolean("status", false ).apply();

        Intent it = new Intent(this, RegistrationIntentService.class);
        startService(it);
    }
}
