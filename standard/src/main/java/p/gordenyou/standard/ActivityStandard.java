package p.gordenyou.standard;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public interface ActivityStandard {

    // 获取宿主环境
    void insertAppContext(Activity appActivity);

    void onCreate(@Nullable Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
