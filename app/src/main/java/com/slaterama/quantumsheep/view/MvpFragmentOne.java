package com.slaterama.quantumsheep.view;

import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.slaterama.qslib.alpha.support.v4.app.PatternManager;
import com.slaterama.quantumsheep.R;
import com.slaterama.quantumsheep.pattern.MyMvp;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterOne;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterOne.UserViewOne;

import java.util.Date;

import static com.slaterama.quantumsheep.view.MvpActivity.PATTERN_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class MvpFragmentOne extends Fragment
		implements View.OnFocusChangeListener,
		TextView.OnEditorActionListener,
		CompoundButton.OnCheckedChangeListener, UserViewOne {

	private int mUserId = -1;

	private TextView mConnectivityStatusView;
	private EditText mFirstNameEdit;
	private EditText mLastNameEdit;
	private EditText mUsernameEdit;
	private CheckBox mActiveCbx;
	private TextView mCreatedAtView;
	private TextView mUpdatedAtView;

	private MyMvp mMyMvp;
	private UserPresenterOne mPresenter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_mvp_one, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mConnectivityStatusView = (TextView) view.findViewById(R.id.fragment_mvp_one_connectivity_status);
		mFirstNameEdit = (EditText) view.findViewById(R.id.fragment_mvp_one_first_name);
		mLastNameEdit = (EditText) view.findViewById(R.id.fragment_mvp_one_last_name);
		mUsernameEdit = (EditText) view.findViewById(R.id.fragment_mvp_one_username);
		mActiveCbx = (CheckBox) view.findViewById(R.id.fragment_mvp_one_status);
		mCreatedAtView = (TextView) view.findViewById(R.id.fragment_mvp_one_user_created_at);
		mUpdatedAtView = (TextView) view.findViewById(R.id.fragment_mvp_one_user_updated_at);

		mFirstNameEdit.setOnFocusChangeListener(this);
		mFirstNameEdit.setOnEditorActionListener(this);
		mLastNameEdit.setOnFocusChangeListener(this);
		mLastNameEdit.setOnEditorActionListener(this);
		mUsernameEdit.setOnFocusChangeListener(this);
		mUsernameEdit.setOnEditorActionListener(this);
		mActiveCbx.setOnCheckedChangeListener(this);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mMyMvp = (MyMvp) PatternManager.newInstance(getActivity()).getPattern(PATTERN_ID);
		if (mMyMvp == null)
			throw new IllegalStateException(String.format("Expecting %s pattern with ID %d",
					MyMvp.class.getSimpleName(), PATTERN_ID));
		mPresenter = new UserPresenterOne(getActivity(), this);
		mMyMvp.registerPresenter(mPresenter);
	}

	@Override
	public void onStart() {
		super.onStart();
		if (!mMyMvp.isPresenterRegistered(mPresenter))
			mMyMvp.registerPresenter(mPresenter);
		mPresenter.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
		mMyMvp.unregisterPresenter(mPresenter);
		mPresenter.onStop();
	}

	// Methods

	public void setUserId(int userId) {
		mUserId = userId;
		if (mPresenter != null)
			mPresenter.loadUser(mUserId);
	}

	// Interaction listeners

	@Override
	public void onFocusChange(View view, boolean hasFocus) {
		if (hasFocus) {
			switch (view.getId()) {
				case R.id.fragment_mvp_one_first_name:
				case R.id.fragment_mvp_one_last_name:
				case R.id.fragment_mvp_one_username:
					EditText editText = (EditText) view;
					editText.setSelection(editText.getText().length());
					break;
			}
		}
	}

	@Override
	public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
		switch (view.getId()) {
			case R.id.fragment_mvp_one_first_name:
				mPresenter.setUserFirstName(mFirstNameEdit.getText().toString());
				break;
			case R.id.fragment_mvp_one_last_name:
				mPresenter.setUserLastName(mLastNameEdit.getText().toString());
				break;
			case R.id.fragment_mvp_one_username:
				mPresenter.setUsername(mUsernameEdit.getText().toString());
				break;
		}
		return false;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		mPresenter.setUserActive(isChecked);
	}

	// EmployeeViewOne implementation

	@Override
	public void setFirstName(String firstName) {
		mFirstNameEdit.setText(firstName);
	}

	@Override
	public void setLastName(String lastName) {
		mLastNameEdit.setText(lastName);
	}

	@Override
	public void setFullName(String fullName) {
		// Not implemented
	}

	@Override
	public void setUsername(String username) {
		mUsernameEdit.setText(username);
	}

	@Override
	public void setActive(boolean active) {
		mActiveCbx.setChecked(active);
	}

	@Override
	public void setCreatedAt(Date createdAt) {
		mCreatedAtView.setText(String.valueOf(createdAt));
	}

	@Override
	public void setUpdatedAt(Date updatedAt) {
		mUpdatedAtView.setText(String.valueOf(updatedAt));
	}

	@Override
	public void onConnectivityChange(ConnectivityManager connectivityManager,
									 boolean networkStatePermission, NetworkInfo activeNetworkInfo,
									 WifiManager wifiManager, boolean wifiStatePermission,
									 WifiInfo connectionInfo) {
		String text;
		int backgroundColor;
		if (!networkStatePermission) {
			text = "Unknown network state";
			backgroundColor = Color.GRAY;
		} else if (activeNetworkInfo == null) {
			text = "No connection available";
			backgroundColor = Color.RED;
		} else if (activeNetworkInfo.isConnectedOrConnecting()) {
			String subtypeName = activeNetworkInfo.getSubtypeName();
			text = "Connected: " + activeNetworkInfo.getTypeName() +
					(TextUtils.isEmpty(subtypeName) ? "" : " (" + activeNetworkInfo.getSubtypeName() + ")");
			if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI
					&& connectionInfo != null) {
				text += " [" + String.valueOf(connectionInfo.getLinkSpeed()) + WifiInfo.LINK_SPEED_UNITS + "]";
			}
			backgroundColor = activeNetworkInfo.isConnected() ? Color.GREEN : Color.YELLOW;
		} else {
			text = "Not connected to the Internet";
			backgroundColor = Color.RED;
		}
		mConnectivityStatusView.setText(text);
		mConnectivityStatusView.setBackgroundColor(backgroundColor);
	}
}
