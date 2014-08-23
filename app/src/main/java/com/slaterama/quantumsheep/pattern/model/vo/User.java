package com.slaterama.quantumsheep.pattern.model.vo;

import android.text.TextUtils;

import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;

public class User extends BaseVO {

	private String mFirstName;
	private String mLastName;
	private String mUsername;
	private boolean mActive;

	public User(int id, String firstName, String lastName, String username, boolean active) {
		super(id);
		mFirstName = firstName;
		mLastName = lastName;
		mUsername = username;
		mActive = active;
	}

	public String getFirstName() {
		return mFirstName;
	}

	public void setFirstName(String firstName) {
		if (!TextUtils.equals(mFirstName, firstName)) {
			UpdateEvent event = new UpdateEvent(this, Property.FIRST_NAME, mFirstName, firstName);
			mFirstName = firstName;
			notifyUpdated(event);
		}
	}

	public String getLastName() {
		return mLastName;
	}

	public void setLastName(String lastName) {
		if (!TextUtils.equals(mLastName, lastName)) {
			UpdateEvent event = new UpdateEvent(this, Property.LAST_NAME, mLastName, lastName);
			mLastName = lastName;
			notifyUpdated(event);
		}
	}

	public String getUsername() {
		return mUsername;
	}

	public void setUsername(String username) {
		if (!TextUtils.equals(mUsername, username)) {
			UpdateEvent event = new UpdateEvent(this, Property.USERNAME, mUsername, username);
			mUsername = username;
			notifyUpdated(event);
		}
	}

	public boolean isActive() {
		return mActive;
	}

	public void setActive(boolean active) {
		if (mActive != active) {
			UpdateEvent event = new UpdateEvent(this, Property.ACTIVE, mActive, active);
			mActive = active;
			notifyUpdated(event);
		}
	}

	public String getFullName() {
		return String.format("%s %s", mFirstName, mLastName);
	}

	public static enum Property {
		FIRST_NAME,
		LAST_NAME,
		USERNAME,
		ACTIVE
	}
}