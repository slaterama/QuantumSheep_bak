package com.slaterama.quantumsheep.pattern.presenter;

import android.text.TextUtils;

import com.slaterama.qslib.alpha.app.pattern.Model;
import com.slaterama.qslib.alpha.app.pattern.mvp.Presenter;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.pattern.model.MyModel;
import com.slaterama.quantumsheep.pattern.model.UserVO;

import java.util.Date;

public class UserPresenterTwo extends Presenter {

	private UserVO mUser; // TODO When to set this?

	public UserPresenterTwo(UserViewTwo view) {
		super(view);
	}

	@Override
	public void update(Model model, Model.ModelEvent event) {
		String action = event.getAction();
		if (TextUtils.equals(action, MyModel.USER_LOADED)) {
			mUser = (UserVO) event.getValue();
			UserViewTwo view = ((UserViewTwo) mView);
			view.setFullName(String.format("%s %s", mUser.getFirstName(), mUser.getLastName()));
			view.setStatus(mUser.isActive());
			view.setUpdatedAt(mUser.getUpdatedAt());
		} else if (TextUtils.equals(action, MyModel.USER_CHANGED)) {
			// TODO Do I need this? I should probably check this.
			// This presenter should probably have "id" saved to check against "what".
			if (mUser == null)
				return; // TODO This is messy
			Object what = event.getWhat();
			String property = event.getProperty();
			Object value = event.getValue();
			if (TextUtils.equals(property, MyModel.PROP_USER_FIRST_NAME)) {
				UserViewTwo view = ((UserViewTwo) mView);
				view.setFullName(String.format("%s %s", mUser.getFirstName(), mUser.getLastName()));
			} else if (TextUtils.equals(property, MyModel.PROP_USER_LAST_NAME)) {
				UserViewTwo view = ((UserViewTwo) mView);
				view.setFullName(String.format("%s %s", mUser.getFirstName(), mUser.getLastName()));
			} else if (TextUtils.equals(property, MyModel.PROP_USER_ACTIVE)) {
				((UserViewTwo) mView).setStatus((Boolean) value);
			} else if (TextUtils.equals(property, MyModel.PROP_UPDATED_AT)) {
				((UserViewTwo) mView).setUpdatedAt((Date) value);
			}

			// TODO I hate this multiple if statements with TextUtils.equals. Options?
			// TODO So many casts. How to simplify?
		}
	}

	public void retrieveUser(int id) {
		LogEx.d();
		((MyModel) mModel).retrieveUser(id); // TODO This object could do it rather than the model?
	}

	public static interface UserViewTwo extends IView {
		public void setFullName(String fullName);

		public void setStatus(boolean active);

		public void setUpdatedAt(Date updatedAt);
	}
}
