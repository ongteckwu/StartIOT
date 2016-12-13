package com.example.ongteckwu.iotproj.modules;

import com.example.ongteckwu.iotproj.DataModuleActivity;
import com.example.ongteckwu.iotproj.utils.BulletTextUtil;

import java.util.Map;

/**
 * Created by ongteckwu on 30/11/16.
 */
public class DataModule extends Module {

    // store data type and use it to store the correct data value into renderMap
    private DataModType.dataType dt;

    public DataModule(String moduleNumber, int imageId, DataModType modType) {
        super("Data Module", moduleNumber, imageId, modType, DataModuleActivity.class);
        Map renderMap = getRenderMap();
        this.dt = modType.getDt();

    }

    @Override
    public String getFeatures() {
        return "Features: " + BulletTextUtil.makeBulletList(0, "Data", "Graph", "Chains");
    }

    public DataModType.dataType getDt() {
        return dt;
    }
}
