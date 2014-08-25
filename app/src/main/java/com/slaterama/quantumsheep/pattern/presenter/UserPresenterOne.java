package com.slaterama.quantumsheep.pattern.presenter;

import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.pattern.model.vo.User;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterOne.UserViewOne;

import java.util.Date;

public class UserPresenterOne extends MyUserPresenter<UserViewOne> {

	public UserPresenterOne(UserViewOne view) {
		super(view);
	}

	@Override
	public void onUserUpdated(UpdateEvent event, User user) {
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
		} catch (IllegalArgumentException e) {
			if (LogEx.isLoggable(LogEx.INFO))
				LogEx.i(e.getMessage());
		}
	}

	@Override
	protected void updateView(User user) {
		if (mView != null && user != null) {
			mView.setFirstName(user.getFirstName());
			mView.setLastName(user.getLastName());
			mView.setUsername(user.getUsername());
			mView.setActive(user.isActive());
			mView.setCreatedAt(user.getCreatedAt());
			mView.setUpdatedAt(user.getUpdatedAt());
		}
	}

	public void setUserFirstName(String firstName) {
		if (mUser != null)
			mUser.setFirstName(firstName);
	}

	public void setUserLastName(String lastName) {
		if (mUser != null)
			mUser.setLastName(lastName);
	}

	public void setUsername(String username) {
		if (mUser != null)
			mUser.setUsername(username);
	}

	public void setUserActive(boolean active) {
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
