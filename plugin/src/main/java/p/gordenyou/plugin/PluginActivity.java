package p.gordenyou.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PluginActivity extends BaseActivity {

    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(proxyActivity, TestActivity.class));
            }
        });
    }
}
