package p.gordenyou.plugintest;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 加载插件
    public void loadPlugin(View view) {
        // 1. 加载 Class   2.加载资源文件
        PluginManager.getInstance(this).loadPlugin();
    }

    // 跳转插件的 Activity
    public void startPluginActivity(View view) {

        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "plugin-debug.apk");
        String pluginPath = file.getAbsolutePath();

        PackageManager packageManager = getPackageManager();
        PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(pluginPath, PackageManager.GET_ACTIVITIES);// 获取 packageManager 的存档信息。
        ActivityInfo activityInfo = packageArchiveInfo.activities[0];


        // 插桩，代理
        startActivity(new Intent(this, ProxyActivity.class).putExtra("className", activityInfo.name));
    }
}
