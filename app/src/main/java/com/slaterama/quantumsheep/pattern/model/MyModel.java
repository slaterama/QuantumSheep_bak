package com.slaterama.quantumsheep.pattern.model;

import android.util.SparseArray;

import com.slaterama.qslib.alpha.app.pattern.Model;
import com.slaterama.qslib.alpha.app.pattern.event.RetrieveEvent;
import com.slaterama.quantumsheep.pattern.model.vo.User;

import java.util.Observer;

public class MyModel extends Model {

	private SparseArray<User> mUsers;

	private boolean mUsersLoaded = false;

	public MyModel() {
		super();
		mUsers = new SparseArray<User>();

		User user = new User(1, "Scott", "Slater", "slaterama", true);
		user.addObserver(this);
		mUsers.put(user.getId(), user);

		user = new User(2, "Johnny", "Appleseed", "jappleseed", false);
		user.addObserver(this);
		mUsers.put(user.getId(), user);

		mUsersLoaded = true;
	}

	public void retrieveUser(int id, Observer observer) {
		User user = mUsers.get(id);
		if (user == null) {
			// Retrieve the user
		} else {
			if (observer != null)
				observer.update(this, new RetrieveEvent(user));
		}
	}
}