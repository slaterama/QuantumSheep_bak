package com.slaterama.quantumsheep.pattern.presenter;

import com.slaterama.qslib.alpha.app.pattern.event.RetrieveEvent;
import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.pattern.model.vo.User;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterOne.UserViewOne;

import java.util.Date;
import java.util.Observable;

public class UserPresenterOne extends MyPresenter<UserViewOne> {

	protected User mUser;

	public UserPresenterOne(UserViewOne view) {
		super(view);
	}

	@Override
	public void update(Observable observable, Object data) {

		// TODO Make sure this is the user/userID we WANT first

		if (data instanceof RetrieveEvent) {
			RetrieveEvent event = (RetrieveEvent) data;
			mUser = (User) event.getSource();
			updateView(mUser);
			return;
		}

		if (data instanceof UpdateEvent) {
			UpdateEvent event = (UpdateEvent) data;
			User user = (User) event.getSource();
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
				// return;
			} catch (IllegalArgumentException e) {
				if (LogEx.isLoggable(LogEx.INFO))
					LogEx.i(e.getMessage());
			}
		}
	}

	public void loadUser(int id) {
		mUser = mModel.getUser(id);
		if (mUser != null)
			updateView(mUser);
	}

	protected void updateView(User user) {
		if (user != null && mView != null) {
			mView.setFirstName(user.getFirstName());
			mView.setLastName(user.getLastName());
			mView.setUsername(user.getUsername());
			mView.setActive(user.isActive());
			mView.setCreatedAt(user.getCreatedAt());
			mView.setUpdatedAt(user.getUpdatedAt());
		}
	}

	public void setUserFirstName(int id, String firstName) {
		if (mUser != null)
			mUser.setFirstName(firstName);
	}

	public void setUserLastName(int id, String lastName) {
		if (mUser != null)
			mUser.setLastName(lastName);
	}

	public void setUsername(int id, String username) {
		if (mUser != null)
			mUser.setUsername(username);
	}

	public void setUserActive(int id, boolean active) {
		if (mUser != null)
			mUser.setActive(active);
	}

	public static interface UserViewOne {
		public void setFirstName(String firstName);

		public void setLastName(String lastName);

		public void setUsername(String username);

		public void setActive(boolean active);

		public void setCreatedAt(Date createdAt);

		public void setUpdatedAt(Date updatedAt);
	}
}
