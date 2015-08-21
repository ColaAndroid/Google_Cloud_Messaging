package costa.barreto.alessandro.googlecloudmessaging;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import costa.barreto.alessandro.googlecloudmessaging.appaux.Application;
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
                    enviarRegistro(token);
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

    /**
     * Metodo para enviar token e registrar no servidor.
     * @param token
     */
    private void enviarRegistro(final String token){


        StringRequest mStringRequest = new StringRequest(Request.Method.POST, "http://localizarcar.pe.hu/gcm_server.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(LOG,"resposta: "+response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(LOG,"resposta erro: "+error.toString());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params;
                params = new HashMap<>();
                params.put("acao","registar");
                params.put("token",token);
                return(params);
            }
        };

        Application.getInstance().getRequestQueue().add(mStringRequest);

    }


}
