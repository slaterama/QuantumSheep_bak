package com.slaterama.quantumsheep.pattern.presenter;

import com.slaterama.qslib.alpha.app.pattern.Model;
import com.slaterama.qslib.alpha.app.pattern.event.RetrieveEvent;
import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;
import com.slaterama.qslib.alpha.app.pattern.mvp.Presenter;
import com.slaterama.qslib.utils.objectscompat.ObjectsCompat;
import com.slaterama.quantumsheep.pattern.model.MyModel;
import com.slaterama.quantumsheep.pattern.model.vo.BaseVO;
import com.slaterama.quantumsheep.pattern.model.vo.User;

import java.util.Date;
import java.util.Observable;

public class UserPresenterOne extends Presenter {

	private MyModel mMyModel;
	private UserViewOne mUserViewOne;

	private User mUser;

	public UserPresenterOne(UserViewOne view) {
		super(view);
		mUserViewOne = view;
	}

	@Override
	protected void setModel(Model model) {
		if (ObjectsCompat.getInstance().isNull(model)) {
			super.setModel(model);
		} else if (model instanceof MyModel) {
			super.setModel(model);
			mMyModel = (MyModel) model;
		} else {
			throw new ClassCastException("Model must be of type MyModel");
		}
	}

	@Override
	public void update(Observable observable, Object data) {

		// TODO Make sure this is the user/userID we WANT first

		if (data instanceof RetrieveEvent) {
			RetrieveEvent event = (RetrieveEvent) data;
			mUser = (User) event.getSource();
			updateView(mUser);
		} else if (data instanceof UpdateEvent) {
			UpdateEvent event = (UpdateEvent) data;
			Object source = event.getSource();
			Object property = event.getProperty();
			if (source instanceof User) {
				User user = (User) source;
				if (property instanceof User.Property) {
					switch ((User.Property) property) {
						case FIRST_NAME:
							mUserViewOne.setFirstName(user.getFirstName());
							break;
						case LAST_NAME:
							mUserViewOne.setLastName(user.getLastName());
							break;
						case USERNAME:
							mUserViewOne.setUsername(user.getUsername());
							break;
						case ACTIVE:
							mUserViewOne.setActive(user.isActive());
							break;
					}
				} else if (property instanceof BaseVO.Property) {
					switch ((BaseVO.Property) property) {
						case UPDATED_AT:
							mUserViewOne.setUpdatedAt(user.getUpdatedAt());
					}
				}
			}
		}
	}

	public void loadUser(int id) {
		mUser = mMyModel.getUser(id);
		if (mUser != null)
			updateView(mUser);
	}

	protected void updateView(User user) {
		if (user != null && mUserViewOne != null) {
			mUserViewOne.setFirstName(user.getFirstName());
			mUserViewOne.setLastName(user.getLastName());
			mUserViewOne.setUsername(user.getUsername());
			mUserViewOne.setActive(user.isActive());
			mUserViewOne.setCreatedAt(user.getCreatedAt());
			mUserViewOne.setUpdatedAt(user.getUpdatedAt());
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

	public static interface UserViewOne extends IView {
		public void setFirstName(String firstName);
		public void setLastName(String lastName);
		public void setUsername(String username);
		public void setActive(boolean active);
		public void setCreatedAt(Date createdAt);
		public void setUpdatedAt(Date updatedAt);
	}
}
