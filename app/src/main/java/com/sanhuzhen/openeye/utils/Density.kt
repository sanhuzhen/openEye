package com.sanhuzhen.openeye.utils

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.util.DisplayMetrics
import android.content.res.Configuration


/**
 * description:设置屏幕密度以适应不同屏幕大小和分辨率的设备
 */
object Density {
    private var sNoncompatDensity: Float = 0.toFloat()
    private var sNoncompatScaledDensity: Float = 0.toFloat()

    private var oldDensity = -1f
    private var oldScaledDensity = -1f
    private var oldDensityDpi = -1f

    fun setDensity(activity: Activity, application: Application) {
        var appDisplayMetrics: DisplayMetrics = application.resources.displayMetrics

        if (sNoncompatDensity == 0.toFloat()) {
            sNoncompatDensity = appDisplayMetrics.density
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity

            application.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onLowMemory() {

                }

                override fun onConfigurationChanged(newConfig: Configuration) {
                    if (newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = application.resources.displayMetrics.scaledDensity
                    }
                }
            })
            val targetDensity = appDisplayMetrics.widthPixels.toFloat() / 384
            val targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity)
            val targetDensityDpi = (160 * targetDensity).toInt()
            appDisplayMetrics.density = targetDensity
            appDisplayMetrics.scaledDensity = targetScaledDensity
            appDisplayMetrics.densityDpi = targetDensityDpi

            val activityDisplayMetrics = activity.resources.displayMetrics
            activityDisplayMetrics.density = targetDensity
            activityDisplayMetrics.scaledDensity = targetScaledDensity
            activityDisplayMetrics.densityDpi = targetDensityDpi
        }
    }
    fun resetDensity(activity: Activity,application:Application ){
        var appDisplayMetrics:DisplayMetrics=application.resources.displayMetrics
        if(oldDensity==-1f){
            oldDensity=appDisplayMetrics.density
        }
        if(oldDensityDpi.toInt() ==-1){
            oldDensityDpi= appDisplayMetrics.densityDpi.toFloat()
        }
        if(oldScaledDensity==-1f){
            oldScaledDensity=appDisplayMetrics.scaledDensity
        }

        if(sNoncompatDensity==0.toFloat()){
            sNoncompatDensity=appDisplayMetrics.density
            sNoncompatScaledDensity=appDisplayMetrics.scaledDensity

            application.registerComponentCallbacks(object :ComponentCallbacks{

                override fun onLowMemory() {

                }

                override fun onConfigurationChanged(newConfig: Configuration) {
                    if (newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = application.resources.displayMetrics.scaledDensity
                        oldScaledDensity *= newConfig.fontScale
                    }
                }
            })


            val activityDisplayMetrics = activity.resources.displayMetrics
            activityDisplayMetrics.density = oldDensity
            activityDisplayMetrics.scaledDensity = oldScaledDensity
            activityDisplayMetrics.densityDpi = oldDensityDpi.toInt()
        }
    }
}