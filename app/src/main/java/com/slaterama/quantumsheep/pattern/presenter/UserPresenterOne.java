package com.slaterama.quantumsheep.pattern.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.pattern.model.vo.IConnectivity;
import com.slaterama.quantumsheep.pattern.model.vo.IUser;
import com.slaterama.quantumsheep.pattern.model.vo.User;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterOne.UserViewOne;

public class UserPresenterOne extends MyUserPresenter<UserViewOne> {

	protected static final IntentFilter CONNECTIVITY_FILTER =
			new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

	protected Context mContext;
	protected ConnectivityReceiver mConnectivityReceiver;

	public UserPresenterOne(Context context, UserViewOne view) {
		super(view);
		mContext = context;
		mConnectivityReceiver = new ConnectivityReceiver(mContext);
	}

	@Override
	public void onStart() {
		super.onStart();
		mContext.registerReceiver(mConnectivityReceiver, CONNECTIVITY_FILTER);
	}

	@Override
	public void onStop() {
		super.onStop();
		mContext.unregisterReceiver(mConnectivityReceiver);
	}

	@Override
	public void onUserUpdated(UpdateEvent event, User user) {
		String propertyName = event.getPropertyName();
		try {
			User.Property property = User.Property.valueOf(propertyName);
			switch (property) {
				case FIRST_NAME:
					mView.setFirstName(user.getFirstName());
					break;
				case LAST_NAME:
					mView.setLastName(user.getLastName());
					break;
				case USERNAME:
					mView.setUsername(user.getUsername());
					break;
				case ACTIVE:
					mView.setActive(user.isActive());
					break;
				case UPDATED_AT:
					mView.setUpdatedAt(user.getUpdatedAt());
					break;
			}
		} catch (IllegalArgumentException e) {
			if (LogEx.isLoggable(LogEx.INFO))
				LogEx.i(e.getMessage());
		}
	}

	@Override
	protected void updateView(User user) {
		if (mView != null && user != null) {
			mView.setFirstName(user.getFirstName());
			mView.setLastName(user.getLastName());
			mView.setUsername(user.getUsername());
			mView.setActive(user.isActive());
			mView.setCreatedAt(user.getCreatedAt());
			mView.setUpdatedAt(user.getUpdatedAt());
		}
	}

	public void setUserFirstName(String firstName) {
		if (mUser != null)
			mUser.setFirstName(firstName);
	}

	public void setUserLastName(String lastName) {
		if (mUser != null)
			mUser.setLastName(lastName);
	}

	public void setUsername(String username) {
		if (mUser != null)
			mUser.setUsername(username);
	}

	public void setUserActive(boolean active) {
		if (mUser != null)
			mUser.setActive(active);
	}

	protected class ConnectivityReceiver extends com.slaterama.quantumsheep.content.ConnectivityReceiver {
		public ConnectivityReceiver(Context context) {
			super(context);
		}

		@Override
		public void onConnectionChange(ConnectivityManager connectivityManager, WifiManager wifiManager) {
			boolean networkStatePermission;
			NetworkInfo activeNetworkInfo = null;
			boolean wifiStatePermission;
			WifiInfo connectionInfo = null;

			try {
				activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
				networkStatePermission = true;
			} catch (SecurityException e) {
				networkStatePermission = false;
			}

			try {
				connectionInfo = wifiManager.getConnectionInfo();
				wifiStatePermission = true;
			} catch (SecurityException e) {
				wifiStatePermission = false;
			}

			mView.onConnectivityChange(connectivityManager, networkStatePermission, activeNetworkInfo,
					wifiManager, wifiStatePermission, connectionInfo);
		}
	}

	public static interface UserViewOne
			extends IUser, IConnectivity {}
}
