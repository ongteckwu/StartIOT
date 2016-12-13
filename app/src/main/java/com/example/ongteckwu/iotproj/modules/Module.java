package com.example.ongteckwu.iotproj.modules;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ongteckwu on 30/11/16.
 */
public abstract class Module implements Serializable{
    private String moduleName;
    private String moduleNumber;
    private int imageId;
    private Map<String, Object> renderMap;
    private ModType modType;
    private transient Class<? extends AppCompatActivity> activity;
    // chains

    public Module(String moduleName, String moduleNumber, int imageId, ModType modType, final Class<? extends AppCompatActivity> activity) {
        this.moduleName = moduleName;
        this.moduleNumber = moduleNumber;
        this.imageId = imageId;
        this.activity = activity;
        this.modType = modType;
        this.renderMap = new HashMap<>();
    }

    public abstract String getFeatures();

    public Map<String, Object> getRenderMap() {return renderMap; }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleNumber() { return "ID: " + moduleNumber; }

    public int getImageId() {
        return imageId;
    }

    public ModType getModType() {
        return modType;
    }

    public Class<? extends AppCompatActivity> getActivity() {
        return activity;
    }

    // abstract getActivity

}
