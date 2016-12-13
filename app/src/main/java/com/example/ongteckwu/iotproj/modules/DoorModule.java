package com.example.ongteckwu.iotproj.modules;

import com.example.ongteckwu.iotproj.DoorModuleActivity;
import com.example.ongteckwu.iotproj.utils.BulletTextUtil;

import java.util.Map;

/**
 * Created by ongteckwu on 30/11/16.
 */
public class DoorModule extends Module {
    // renderMap<door_component_<name>, value>
    public DoorModule(String moduleNumber, int imageId, ModType modType) {
        super("Door Module", moduleNumber, imageId, modType, DoorModuleActivity.class);

        Map renderMap = getRenderMap();
        //renderMap.put
        // values are retrieved here from firebase and passed to the respective layouts
    }

    @Override
    public String getFeatures() {
        return "Features: " + BulletTextUtil.makeBulletList(0, "Open", "Lock");
    }
}
