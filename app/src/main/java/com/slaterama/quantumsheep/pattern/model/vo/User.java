package com.slaterama.quantumsheep.pattern.model.vo;

import com.slaterama.quantumsheep.pattern.model.MyModel;
import com.slaterama.quantumsheep.pattern.model.vo.BaseVO;

public class User extends BaseVO {

	private String mFirstName;
	private String mLastName;
	private String mUsername;
	private boolean mActive;

	public User(MyModel model, int id, String firstName, String lastName, String username, boolean active) {
		super(model, id);
		mFirstName = firstName;
		mLastName = lastName;
		mUsername = username;
		mActive = active;
	}

	public String getFirstName() {
		return mFirstName;
	}

	public void setFirstName(String firstName) {
		mFirstName = firstName;
		onChanged(MyModel.USER_CHANGED, MyModel.PROP_USER_FIRST_NAME, firstName);
	}

	public String getLastName() {
		return mLastName;
	}

	public void setLastName(String lastName) {
		mLastName = lastName;
		onChanged(MyModel.USER_CHANGED, MyModel.PROP_USER_LAST_NAME, lastName);
	}

	public String getUsername() {
		return mUsername;
	}

	public void setUsername(String username) {
		mUsername = username;
		onChanged(MyModel.USER_CHANGED, MyModel.PROP_USER_USERNAME, username);
	}

	public boolean isActive() {
		return mActive;
	}

	public void setActive(boolean active) {
		mActive = active;
		onChanged(MyModel.USER_CHANGED, MyModel.PROP_USER_ACTIVE, active);
	}
}
