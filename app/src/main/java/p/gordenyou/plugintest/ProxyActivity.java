package p.gordenyou.plugintest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;

import p.gordenyou.standard.ActivityStandard;


/**
 * 代理Activity （占位）
 */
public class ProxyActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 在这里真正的加载插件 Activity
        String className = getIntent().getStringExtra("className");
        // 我们这里需要通过 Activity 的类名动态获取。而类名我们需要在跳转的时候就得到。
        try {
            Class pluginClass = getClassLoader().loadClass(className);

            // 开始实例化
            Constructor constructor = pluginClass.getConstructor();
            Object instance = constructor.newInstance();
            ActivityStandard activityStandard = (ActivityStandard) instance;

            // 将我们代理的 Activity 的环境注入
            activityStandard.insertAppContext(this);
            Bundle bundle = new Bundle();
            bundle.putString("appName", "宿主的信息");

            activityStandard.onCreate(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance(this).getClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance(this).getResources();
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        super.startActivity(new Intent(this, ProxyActivity.class).putExtra("className", className));
    }

    @Override
    public ComponentName startService(Intent service) {
        String className = service.getStringExtra("className");

        return super.startService(new Intent(this, ProxyService.class).putExtra("className", className));
    }
}
