package com.example.ongteckwu.iotproj.utils;

import android.view.animation.Interpolator;

/**
 * Created by ongteckwu on 1/12/16.
 */
public class EaseInOutQuintInterpolator implements Interpolator {

    // easeInOutQuint
    public float getInterpolation(float t) {
        float x;
        if (t<0.5f)
        {
            x = t*2.0f;
            return 0.5f*x*x*x*x*x;
        }
        x = (t-0.5f)*2-1;
        return 0.5f*x*x*x*x*x+1;
    }
}

