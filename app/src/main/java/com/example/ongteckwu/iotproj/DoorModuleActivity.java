package com.example.ongteckwu.iotproj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.example.ongteckwu.iotproj.components.Server;
import com.example.ongteckwu.iotproj.components.ServerSingleton;
import com.example.ongteckwu.iotproj.modules.DataModule;
import com.example.ongteckwu.iotproj.modules.DoorModule;
import com.example.ongteckwu.iotproj.modules.ModType;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoorModuleActivity extends AbstractModuleActivity {
    private ModType modType;
    private DoorModule module;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_module);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // get module
        Intent intent = getIntent();
        module = (DoorModule) intent.getSerializableExtra("module");
        modType = module.getModType();
    }

    public void onRadioButtonClicked(View v) {
        // Is the view now checked?
        boolean checked = ((RadioButton) v).isChecked();

        // Check which checkbox was clicked
        switch(v.getId()) {
            case R.id.radiobutton_closed:
                if (checked)
                    setState("closed");
                break;
            case R.id.radiobutton_unlocked:
                if (checked)
                    setState("unlocked");
                break;
            case R.id.radiobutton_locked:
                if (checked)
                    setState("locked");
                break;
        }
    }

    public void setState(String state) {
        Server server = ServerSingleton.getInstance().getServer();
        DatabaseReference serverRef = firebaseDatabase.getReference("Server_stuff/" + server.getName() + "/modules/" + module.getModuleNumber());
        Log.i("SASDA", "Server_stuff/" + server.getName() + "/modules/" + module.getModuleNumber());
        serverRef.child("door").setValue(state);
    }
}
