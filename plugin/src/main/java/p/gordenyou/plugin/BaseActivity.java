package p.gordenyou.plugin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import p.gordenyou.standard.ActivityStandard;

public class BaseActivity extends Activity implements ActivityStandard {

    public Activity proxyActivity;

    @Override
    public void insertAppContext(Activity appActivity) {
        this.proxyActivity = appActivity;
    }

    @Override
    public void setContentView(int layoutResID) {
        proxyActivity.setContentView(layoutResID);
    }

    public View findViewById(int layoutId){
        return proxyActivity.findViewById(layoutId);
    }

    public void startActivity(Intent intent){
        //我们最终还是需要宿主使用反射获取Activity，所以我们需要添加 Activity 的类名
        intent = new Intent().putExtra("className", intent.getComponent().getClassName());
        proxyActivity.startActivity(intent);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onPause() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {

    }
}
