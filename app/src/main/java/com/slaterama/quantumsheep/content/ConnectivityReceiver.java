package com.slaterama.quantumsheep.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

public abstract class ConnectivityReceiver extends BroadcastReceiver {

	private Context mContext;
	private ConnectivityManager mConnectivityManager;
	private WifiManager mWifiManager;

	public ConnectivityReceiver(Context context) {
		super();
		mContext = context;
		mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
	}

	public Context getContext() {
		return mContext;
	}

	public ConnectivityManager getConnectivityManager() {
		return mConnectivityManager;
	}

	public WifiManager getWifiManager() {
		return mWifiManager;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		onConnectionChange(mConnectivityManager, mWifiManager);
	}

	public void onConnectionChange(ConnectivityManager connectivityManager, WifiManager wifiManager) {
	}
}
