package costa.barreto.alessandro.googlecloudmessaging.appaux;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Alessandro on 21/08/2015.
 */
public class Application extends android.app.Application {

    private RequestQueue mRequestQueue;
    private static Application sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mRequestQueue = Volley.newRequestQueue(this);

        sInstance = this;
    }

    public synchronized static Application getInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}
