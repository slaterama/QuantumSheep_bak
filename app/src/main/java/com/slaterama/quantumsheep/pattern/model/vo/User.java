package com.slaterama.quantumsheep.pattern.model.vo;

import android.text.TextUtils;

public class User extends MyVO {

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
			String oldValue = mFirstName;
			mFirstName = firstName;
			notifyUpdated(Property.FIRST_NAME.name(), oldValue, firstName);
		}
	}

	public String getLastName() {
		return mLastName;
	}

	public void setLastName(String lastName) {
		if (!TextUtils.equals(mLastName, lastName)) {
			String oldValue = mLastName;
			mLastName = lastName;
			notifyUpdated(Property.LAST_NAME.name(), oldValue, lastName);
		}
	}

	public String getUsername() {
		return mUsername;
	}

	public void setUsername(String username) {
		if (!TextUtils.equals(mUsername, username)) {
			String oldValue = mUsername;
			mUsername = username;
			notifyUpdated(Property.USERNAME.name(), oldValue, username);
		}
	}

	public boolean isActive() {
		return mActive;
	}

	public void setActive(boolean active) {
		if (mActive != active) {
			Boolean oldValue = mActive;
			mActive = active;
			notifyUpdated(Property.ACTIVE.name(), oldValue, active);
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