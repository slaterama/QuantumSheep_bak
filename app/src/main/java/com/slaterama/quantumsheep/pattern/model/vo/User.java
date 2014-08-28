package com.slaterama.quantumsheep.pattern.model.vo;

import android.text.TextUtils;

import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;

public class User extends AbstractEntity {

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
			notifySubscribers(new UpdateEvent(this, Property.FIRST_NAME.name(), oldValue, firstName));
		}
	}

	public String getLastName() {
		return mLastName;
	}

	public void setLastName(String lastName) {
		if (!TextUtils.equals(mLastName, lastName)) {
			String oldValue = mLastName;
			mLastName = lastName;
			notifySubscribers(new UpdateEvent(this, Property.LAST_NAME.name(), oldValue, lastName));
		}
	}

	public String getUsername() {
		return mUsername;
	}

	public void setUsername(String username) {
		if (!TextUtils.equals(mUsername, username)) {
			String oldValue = mUsername;
			mUsername = username;
			notifySubscribers(new UpdateEvent(this, Property.USERNAME.name(), oldValue, username));
		}
	}

	public boolean isActive() {
		return mActive;
	}

	public void setActive(boolean active) {
		if (mActive != active) {
			Boolean oldValue = mActive;
			mActive = active;
			notifySubscribers(new UpdateEvent(this, Property.ACTIVE.name(), oldValue, active));
		}
	}

	public String getFullName() {
		return String.format("%s %s", mFirstName, mLastName);
	}

	public static enum Property {
		ID,
		FIRST_NAME,
		LAST_NAME,
		USERNAME,
		ACTIVE,
		CREATED_AT,
		UPDATED_AT
	}
}
