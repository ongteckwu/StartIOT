package com.example.ongteckwu.iotproj.modules;

/**
 * Created by ongteckwu on 30/11/16.
 */
public abstract class Module {
    private String moduleName;
    private String moduleNumber;
    private int imageId;
    // chains

    public Module(String moduleName, String moduleNumber, int imageId) {
        this.moduleName = moduleName;
        this.moduleNumber = moduleNumber;
        this.imageId = imageId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleNumber() {
        return "ID: " + moduleNumber;
    }

    public int getImageId() {
        return imageId;
    }

    // abstract getActivity
}
