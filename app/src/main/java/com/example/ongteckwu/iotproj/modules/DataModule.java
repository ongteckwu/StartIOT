package com.example.ongteckwu.iotproj.modules;

import android.util.Log;

import com.example.ongteckwu.iotproj.AbstractModuleActivity;
import com.example.ongteckwu.iotproj.DataModuleActivity;
import com.example.ongteckwu.iotproj.components.Server;
import com.example.ongteckwu.iotproj.components.ServerSingleton;
import com.example.ongteckwu.iotproj.utils.BulletTextUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ongteckwu on 30/11/16.
 */
public class DataModule extends Module {
    private int value = 0;
    private transient FirebaseDatabase firebaseDatabase;
    private DataModuleActivity activity;

    public DataModule(String moduleName, String moduleNumber, int imageId, DataModType modType) {
        super(moduleName, moduleNumber, imageId, modType, DataModuleActivity.class);
        firebaseDatabase = FirebaseDatabase.getInstance();

        Server server = ServerSingleton.getInstance().getServer();
        DatabaseReference serverRef = firebaseDatabase.getReference("Server_stuff/" + server.getName() + "/modules");
        serverRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot snapshot){
                for ( DataSnapshot child : snapshot.getChildren()){
                    WuZheYu w = child.getValue(WuZheYu.class);
                    if (w.getModule_id() != null && w.getModule_id().equals(getModuleNumber())) {
                        value = w.getValue();
                    }
                }
                Log.i("SNAPSHOT", Integer.toString(value));
                // if activity is deflated, set activity to null
                if (activity != null) {
                    activity.updateValue(value);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError);
            }
        } );
    }

    public void addActivity(DataModuleActivity activity) {
        this.activity = activity;
        activity.updateValue(value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String getFeatures() {
        return "Features: " + BulletTextUtil.makeBulletList(0, "Data", "Graph", "Chains");
    }

    public static class WuZheYu {
        private String module_id;
        private String sensor_type;
        private String type;
        private int value;

        public WuZheYu() {

        }

        public WuZheYu(String module_id, String sensor_type, String type, int value) {
            this.module_id = module_id;
            this.sensor_type = sensor_type;
            this.type = type;
            this.value = value;
        }

        public String getModule_id() {
            return module_id;
        }

        public String getSensor_type() {
            return sensor_type;
        }

        public String getType() {
            return type;
        }

        public int getValue() {
            return value;
        }

        public String toString(){
            return "Wuzheyu(id=" + module_id +", value=" + value + ")" ;
        }
    }
}
