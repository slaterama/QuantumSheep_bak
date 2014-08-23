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

public class UserPresenterTwo extends Presenter {

	private MyModel mMyModel;
	private UserViewTwo mUserViewTwo;

	private User mUser;

	public UserPresenterTwo(UserViewTwo view) {
		super(view);
		mUserViewTwo = view;
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
						case LAST_NAME:
							mUserViewTwo.setFullName(user.getFullName());
							break;
						case ACTIVE:
							mUserViewTwo.setActive(user.isActive());
							break;
					}
				} else if (property instanceof BaseVO.Property) {
					switch ((BaseVO.Property) property) {
						case UPDATED_AT:
							mUserViewTwo.setUpdatedAt(user.getUpdatedAt());
							break;
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
		if (user != null && mUserViewTwo != null) {
			mUserViewTwo.setFullName(user.getFullName());
			mUserViewTwo.setActive(user.isActive());
			mUserViewTwo.setUpdatedAt(user.getUpdatedAt());
		}
	}

	public static interface UserViewTwo extends IView {
		public void setFullName(String fullName);

		public void setActive(boolean active);

		public void setUpdatedAt(Date updatedAt);
	}
}
