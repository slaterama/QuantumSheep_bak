package com.slaterama.quantumsheep.pattern.model.vo;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public interface IConnectivity {

	public void onConnectivityChange(ConnectivityManager connectivityManager,
									 boolean networkStatePermission, NetworkInfo activeNetworkInfo,
									 WifiManager wifiManager, boolean wifiStatePermission,
									 WifiInfo connectionInfo);
}
