package com.slaterama.quantumsheep.pattern.model;

import com.slaterama.qslib.alpha.app.pattern.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyModel extends Model {

	public final static String USER_LOADED = "USER_LOADED";
	public final static String USER_CHANGED = "USER_CHANGED";
	
	public final static String PROP_USER_FIRST_NAME = "firstName";
	public final static String PROP_USER_LAST_NAME = "lastName";
	public final static String PROP_USER_USERNAME = "username";
	public final static String PROP_USER_ACTIVE = "active";
	public final static String PROP_UPDATED_AT = "updatedAt";

	private List<UserVO> mUsers;

	public MyModel() {
		super();
		mUsers = new ArrayList<UserVO>();
		mUsers.add(new UserVO(this, 1, "Scott", "Slater", "slaterama", true));
	}
	
	public void notifyUserFirstNameChanged(UserVO user, String firstName) {
		notifyObservers(new ModelEvent(USER_CHANGED, user.getId(), PROP_USER_FIRST_NAME, firstName));
	}
	
	public void notifyUserLastNameChanged(UserVO user, String lastName) {
		notifyObservers(new ModelEvent(USER_CHANGED, user.getId(), PROP_USER_LAST_NAME, lastName));
	}
	
	public void notifyUserUsernameChanged(UserVO user, String username) {
		notifyObservers(new ModelEvent(USER_CHANGED, user.getId(), PROP_USER_USERNAME, username));
	}
	
	public void notifyUserStateChanged(UserVO user, boolean active) {
		notifyObservers(new ModelEvent(USER_CHANGED, user.getId(), PROP_USER_ACTIVE, active));
	}

	public void notifyObjectUpdatedAtChanged(BaseVO object, Date updatedAt) {
		if (object instanceof UserVO)
			notifyUserUpdatedAtChanged((UserVO) object, updatedAt);
	}
	
	public void notifyUserUpdatedAtChanged(UserVO user, Date updatedAt) {
		notifyObservers(new ModelEvent(USER_CHANGED, user.getId(), PROP_UPDATED_AT, updatedAt));
	}
}
