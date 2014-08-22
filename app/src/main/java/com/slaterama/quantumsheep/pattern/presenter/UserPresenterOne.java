package com.slaterama.quantumsheep.pattern.presenter;

import android.text.TextUtils;

import com.slaterama.qslib.alpha.app.pattern.Model;
import com.slaterama.qslib.alpha.app.pattern.Model.ModelEvent;
import com.slaterama.qslib.alpha.app.pattern.mvp.Presenter;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.pattern.model.MyModel;
import com.slaterama.quantumsheep.pattern.model.UserVO;

import java.util.Date;

public class UserPresenterOne extends Presenter {

	public UserPresenterOne(UserViewOne view) {
		super(view);
	}

	@Override
	public void update(Model model, ModelEvent event) {
		String action = event.getAction();
		if (TextUtils.equals(action, MyModel.USER_LOADED)) {
			UserVO user = (UserVO) event.getValue();
			UserViewOne view = ((UserViewOne) mView);
			view.setFirstName(user.getFirstName());
			view.setLastName(user.getLastName());
			view.setUsername(user.getUsername());
			view.setStatus(user.isActive());
			view.setCreatedAt(user.getCreatedAt());
			view.setUpdatedAt(user.getUpdatedAt());
		} else if (TextUtils.equals(action, MyModel.USER_CHANGED)) {
			// TODO Do I need this? I should probably check this.
			// This presenter should probably have "id" saved to check against "what".
			Object what = event.getWhat();
			String property = event.getProperty();
			Object value = event.getValue();
			if (TextUtils.equals(property, MyModel.PROP_USER_ACTIVE)) {
				LogEx.d(String.format("property=%s, value=%s", property, String.valueOf(value)));
				((UserViewOne) mView).setStatus((Boolean) value);
			} else if (TextUtils.equals(property, MyModel.PROP_UPDATED_AT)) {
				((UserViewOne) mView).setUpdatedAt((Date) value);
			}

			// TODO I hate this multiple if statements with TextUtils.equals. Options?
			// TODO So many casts. How to simplify?
		}
	}

	public void retrieveUser(int id) {
		LogEx.d();
		((MyModel) mModel).loadUser(id); // TODO This object could do it rather than the model?
	}

	public void setUserFirstName(int id, String firstName) {
		UserVO user = ((MyModel) mModel).getUser(id);
		if (user != null)
			user.setFirstName(firstName);
	}

	public void setUserLastName(int id, String lastName) {
		UserVO user = ((MyModel) mModel).getUser(id);
		if (user != null)
			user.setLastName(lastName);
	}

	public void setUsername(int id, String username) {
		UserVO user = ((MyModel) mModel).getUser(id);
		if (user != null)
			user.setUsername(username);
	}

	public void setUserActive(int id, boolean active) {
		UserVO user = ((MyModel) mModel).getUser(id);
		if (user != null)
			user.setActive(active);
	}

	public static interface UserViewOne extends IView {
		public void setFirstName(String firstName);
		public void setLastName(String lastName);
		public void setUsername(String username);
		public void setStatus(boolean active);
		public void setCreatedAt(Date createdAt);
		public void setUpdatedAt(Date updatedAt);
	}
}
