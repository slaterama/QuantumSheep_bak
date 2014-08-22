package com.slaterama.quantumsheep.pattern.model;

import android.util.SparseArray;

import com.slaterama.qslib.alpha.app.pattern.Model;

public class MyModel extends Model {

	public final static String USER_LOADED = "USER_LOADED";
	public final static String USER_CHANGED = "USER_CHANGED";
	
	public final static String PROP_USER_FIRST_NAME = "firstName";
	public final static String PROP_USER_LAST_NAME = "lastName";
	public final static String PROP_USER_USERNAME = "username";
	public final static String PROP_USER_ACTIVE = "active";
	public final static String PROP_UPDATED_AT = "updatedAt";

	private SparseArray<UserVO> mUsers;

	private boolean mUsersLoaded = false;

	public MyModel() {
		super();
		mUsers = new SparseArray<UserVO>();
		mUsers.put(1, new UserVO(this, 1, "Scott", "Slater", "slaterama", true));
		mUsers.put(2, new UserVO(this, 2, "Johnny", "Appleseed", "jappleseed", false));
		mUsersLoaded = true;
	}

	public void retrieveUser(int id) {
		if (mUsersLoaded) {
			UserVO user = mUsers.get(id);
			onUserRetrieved(user);
		} else {

		}
	}

	public UserVO getUser(int id) {
		return mUsers.get(id);
	}

	public void onUserRetrieved(UserVO user) {
		setChanged();
		notifyObservers(new ModelEvent(USER_LOADED, user.getId(), null, user));
	}

	protected void onChanged(String action, Object what, String property, Object value) {
		setChanged();
		notifyObservers(new ModelEvent(action, what, property, value));
	}

}
