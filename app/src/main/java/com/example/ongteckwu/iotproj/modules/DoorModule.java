package com.example.ongteckwu.iotproj.modules;

import com.example.ongteckwu.iotproj.DoorModuleActivity;
import com.example.ongteckwu.iotproj.components.Server;
import com.example.ongteckwu.iotproj.components.ServerSingleton;
import com.example.ongteckwu.iotproj.utils.BulletTextUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

/**
 * Created by ongteckwu on 30/11/16.
 */
public class DoorModule extends Module {
    public DoorModule(String moduleName, String moduleNumber, int imageId, ModType modType) {
        super(moduleName, moduleNumber, imageId, modType, DoorModuleActivity.class);

    }

    @Override
    public String getFeatures() {
        return "Features: " + BulletTextUtil.makeBulletList(0, "Open", "Lock");
    }
}
