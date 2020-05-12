package p.gordenyou.plugin;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import p.gordenyou.standard.ServiceStandard;

public class BaseServie extends Service implements ServiceStandard {

    public Service proxyServie;

    @Override
    public void insertAppContext(Service proxyServie) {
        this.proxyServie = proxyServie;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 0;
    }

    @Override
    public void onDestroy() {
    }
}
