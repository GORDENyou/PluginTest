package p.gordenyou.plugintest;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {
    private static final String TAG = PluginManager.class.getSimpleName(); // 类名会一起改变
    private static PluginManager instance;
    private Context context;

    static PluginManager getInstance(Context context) {
        if (instance == null) {
            synchronized (PluginManager.class) {
                if (instance == null) {
                    instance = new PluginManager(context);
                }
            }
        }
        return instance;
    }

    private PluginManager(Context context) {
        this.context = context;
    }

    private DexClassLoader dexClassLoader;
    private Resources resources;

    public ClassLoader getClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public void loadPlugin() {
        try {
            // ############获取 Class#############
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "p.apk");
            if (!file.exists()) {
                Log.d(TAG, "loadPlugin: 插件包不存在");
                return;
            }

            // 获取插件包的路径
            String pluginPath = file.getAbsolutePath();

            // 设置一个缓存目录
            File fileDir = context.getDir("pDir", Context.MODE_PRIVATE);

            dexClassLoader = new DexClassLoader(pluginPath, fileDir.getAbsolutePath(), null, context.getClassLoader());

            // ############获取资源#############
            // 获取方式 1：AssetManager assetManager = context.getAssets();我们下边采用反射方式获取

            AssetManager assetManager = AssetManager.class.newInstance();

            // 我们需要加载插件包的路径
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, pluginPath);

            Resources r = context.getResources();// 宿主的配置信息
            resources = new Resources(assetManager, r.getDisplayMetrics(), r.getConfiguration());// 后面的两个参数是配置信息

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
