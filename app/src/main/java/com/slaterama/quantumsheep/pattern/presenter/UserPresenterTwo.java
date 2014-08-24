package com.slaterama.quantumsheep.pattern.presenter;

import com.slaterama.qslib.alpha.app.pattern.event.RetrieveEvent;
import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;
import com.slaterama.qslib.alpha.app.pattern.mvp.Presenter;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.pattern.model.MyModel;
import com.slaterama.quantumsheep.pattern.model.vo.BaseVO;
import com.slaterama.quantumsheep.pattern.model.vo.User;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterTwo.UserViewTwo;

import java.util.Date;
import java.util.Observable;

public class UserPresenterTwo extends Presenter<MyModel, UserViewTwo> {

	protected User mUser;

	public UserPresenterTwo(UserViewTwo view) {
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
					case LAST_NAME:
						mView.setFullName(user.getFullName());
						break;
					case ACTIVE:
						mView.setActive(user.isActive());
						break;
				}
				return;
			} catch (IllegalArgumentException e) {
				if (LogEx.isLoggable(LogEx.INFO))
					LogEx.i(e.getMessage());
			}

			try {
				BaseVO.Property baseProperty = BaseVO.Property.valueOf(propertyName);
				switch (baseProperty) {
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
	}

	public void loadUser(int id) {
		mUser = mModel.getUser(id);
		if (mUser != null)
			updateView(mUser);
	}

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
