package com.slaterama.quantumsheep.pattern.model;

import android.os.AsyncTask;
import android.util.SparseArray;

import com.slaterama.qslib.alpha.app.pattern.Model;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.pattern.model.vo.User;

public class MyModel extends Model {

	private SparseArray<User> mUsers;

	public MyModel() {
		super();
		mUsers = new SparseArray<>();

		User user = new User(1, "Scott", "Slater", "slaterama", true);
		user.addObserver(this);
		mUsers.put(user.getId(), user);

		user = new User(2, "Johnny", "Appleseed", "jappleseed", false);
		user.addObserver(this);
		mUsers.put(user.getId(), user);
	}

	public User getUser(int id, boolean retrieveIfNotFound) {
		User user = mUsers.get(id);
		if (user == null && retrieveIfNotFound)
			retrieveUser(id);
		return user;
	}

	public User getUser(int id) {
		return getUser(id, true);
	}

	public void retrieveUser(int id) {
		new AsyncTask<Integer, Void, User>() {
			@Override
			protected User doInBackground(Integer... params) {
				try {
					Thread.sleep(1500);
					User newUser = new User(params[0],
							String.format("User%d", params[0]), "McUserpants",
							String.format("usermcu%d", params[0]), true);
					mUsers.put(params[0], newUser);
					return newUser;
				} catch (InterruptedException e) {
					if (LogEx.isLoggable(LogEx.VERBOSE))
						LogEx.v(e.getMessage(), e);
				}
				return null;
			}

			@Override
			protected void onPostExecute(User user) {
				if (user != null) {
					user.addObserver(MyModel.this);
					user.notifyRetrieved();
				}
			}
		}.execute(id);
	}
}