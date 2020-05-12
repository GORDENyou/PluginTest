package p.gordenyou.plugin;

import android.content.Intent;
import android.os.Bundle;

public class PluginActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);

        findViewById(R.id.button_activity).setOnClickListener(v -> startActivity(new Intent(proxyActivity, TestActivity.class)));

        findViewById(R.id.button_service).setOnClickListener(v -> startService(new Intent(proxyActivity, TestService.class)));
    }
}
