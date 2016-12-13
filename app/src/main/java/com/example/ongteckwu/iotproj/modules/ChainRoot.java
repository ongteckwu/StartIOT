package com.example.ongteckwu.iotproj.modules;

/**
 * Created by ongteckwu on 10/12/16.
 */
public interface ChainRoot {
    void chain(Chainable o);
    void unchain(Chainable o);
    void notifychains();
}