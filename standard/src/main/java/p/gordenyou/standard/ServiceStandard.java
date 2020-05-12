package p.gordenyou.standard;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public interface ServiceStandard {
    void insertAppContext(Service proxyServie);

    IBinder onBind(Intent intent);

    void onCreate();

    int onStartCommand(Intent intent, int flags, int startId);

    void onDestroy();
}
