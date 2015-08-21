package costa.barreto.alessandro.googlecloudmessaging;

import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;

import costa.barreto.alessandro.googlecloudmessaging.domain.PushMessage;
import de.greenrobot.event.EventBus;

/**
 * Created by Alessandro on 17/08/2015.
 *  Class que serve o data do servidor
 */
public class MyGcmListenerService extends GcmListenerService {
    public static final String LOG = "LOG";

    @Override
    public void onMessageReceived(String sendID, Bundle data) {
        //super.onMessageReceived(from, data);
        //String title = data.getString("title");
        String message = data.getString("mensagem");
        EventBus.getDefault().post(new PushMessage("sem titulo",message));
    }
}
