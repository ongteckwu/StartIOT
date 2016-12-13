package com.example.ongteckwu.iotproj;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ongteckwu on 13/12/16.
 */
public abstract class AbstractModuleActivity extends AppCompatActivity {
    public void onClickReturn(View v) {
        finish();
    }

    public void onClickSettings(View v) {
        // SETTINGS
    }
}
