package p.gordenyou.plugin;

import android.content.Intent;
import android.widget.Toast;

public class TestService extends BaseServie {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(proxyServie, "服务创建！", Toast.LENGTH_SHORT).show();
        return proxyServie.onStartCommand(intent, flags, startId);
    }
}
