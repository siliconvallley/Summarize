package com.dh.http.interceptor

import android.Manifest.permission.*
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.telephony.TelephonyManager
import androidx.annotation.RequiresPermission
import java.lang.Exception

/**
 * @author : Silicon_Valley
 * @date : 2018/12/17
 * @description :
 * @version :
 */
object NetworkUtils {

    /**
     * 跳转到网络设置页面
     */
    fun openNetworkSetting(context: Context) {
        val intent = Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    /**
     * 或许网络信息
     * 必须添加<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun getActiveNetworkInfo(context: Context): NetworkInfo? {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo ?: null
    }

    /**
     * 判断网络是否连接
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isConnected(context: Context): Boolean {
        val info = getActiveNetworkInfo(context)
        return info?.isConnected ?: false
    }

    /**
     * 判断网络是否连接
     */
    /*@RequiresPermission(ACCESS_NETWORK_STATE)
    fun isConnected(): Boolean {
        val info = getActiveNetworkInfo(Utils.getApp())
        return info?.isConnected ?: false
    }*/

    /**
     * 判断数据流量是否可用
     */
    fun getMobileDataEnable(context: Context): Boolean {
        return try {
            val tm: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            // 判断版本大于8.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return tm.isDataEnabled
            }
            val method = tm.javaClass.getDeclaredMethod("getDataEnabled")
            method.invoke(tm) as Boolean
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 打开数据流量
     * <uses-permission android:name="android.permission.MODIFY_PHONE_STATE"/>
     */
    fun setMobileDataEnable(context: Context, enabled: Boolean) {
        try {
            val tm: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val method = tm.javaClass.getDeclaredMethod("setDataEnabled", Boolean::class.java)
            method.invoke(tm, enabled)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 判断是否使用的是移动数据流量
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isMobileData(context: Context): Boolean {
        val info = getActiveNetworkInfo(context)
        return null != info && info.isAvailable && info.type == ConnectivityManager.TYPE_MOBILE
    }

    /**
     * 判断WiFi是否可用
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
    fun getWifiEnable(context: Context):Boolean{
        val manager:WifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return manager.isWifiEnabled
    }

    /**
     * 打开WiFi
     */
    @RequiresPermission(CHANGE_WIFI_STATE)
    fun setWifiEnable(context: Context,enabled: Boolean){
        val manager:WifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (enabled == manager.isWifiEnabled) return
        manager.isWifiEnabled = enabled
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isWifiConnected(context: Context):Boolean{
        val info = getActiveNetworkInfo(context)
        return info != null && info.type == ConnectivityManager.TYPE_WIFI
    }
}