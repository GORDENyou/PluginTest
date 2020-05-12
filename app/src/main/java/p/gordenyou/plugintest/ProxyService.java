package p.gordenyou.plugintest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;

import p.gordenyou.standard.ServiceStandard;

public class ProxyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String className = intent.getStringExtra("className");

        try {
            Class pluginService = PluginManager.getInstance(this).getClassLoader().loadClass(className);

            Constructor constructor = pluginService.getConstructor();
            Object newInstance = constructor.newInstance();

            ServiceStandard serviceStandard = (ServiceStandard) newInstance;

            serviceStandard.insertAppContext(this);
            return serviceStandard.onStartCommand(intent, flags, startId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
