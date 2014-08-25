package com.slaterama.quantumsheep.pattern.presenter;

import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.pattern.model.vo.User;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterTwo.UserViewTwo;

import java.util.Date;

public class UserPresenterTwo extends MyUserPresenter<UserViewTwo> {

	public UserPresenterTwo(UserViewTwo view) {
		super(view);
	}

	@Override
	public void onUserUpdated(UpdateEvent event, User user) {
		String propertyName = event.getPropertyName();
		try {
			User.Property property = User.Property.valueOf(propertyName);
			switch (property) {
				case FIRST_NAME:
				case LAST_NAME:
					mView.setFullName(user.getFullName());
					break;
				case ACTIVE:
					mView.setActive(user.isActive());
					break;
				case UPDATED_AT:
					mView.setUpdatedAt(user.getUpdatedAt());
					break;
			}
			return;
		} catch (IllegalArgumentException e) {
			if (LogEx.isLoggable(LogEx.INFO))
				LogEx.i(e.getMessage());
		}
	}

	@Override
	protected void updateView(User user) {
		if (user != null && mView != null) {
			mView.setFullName(user.getFullName());
			mView.setActive(user.isActive());
			mView.setUpdatedAt(user.getUpdatedAt());
		}
	}

	public static interface UserViewTwo {
		public void setFullName(String fullName);

		public void setActive(boolean active);

		public void setUpdatedAt(Date updatedAt);
	}
}
