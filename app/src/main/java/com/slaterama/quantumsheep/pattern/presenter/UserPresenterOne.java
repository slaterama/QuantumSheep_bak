package com.slaterama.quantumsheep.pattern.presenter;

import android.text.TextUtils;

import com.slaterama.qslib.alpha.app.pattern.Model;
import com.slaterama.qslib.alpha.app.pattern.event.RetrieveEvent;
import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;
import com.slaterama.qslib.alpha.app.pattern.mvp.Presenter;
import com.slaterama.qslib.utils.objectscompat.ObjectsCompat;
import com.slaterama.quantumsheep.pattern.model.MyModel;
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
		if (data instanceof RetrieveEvent) {
			RetrieveEvent event = (RetrieveEvent) data;
			mUser = (User) event.getSource();
			if (mUser != null && mUserViewOne != null) {
				mUserViewOne.setFirstName(mUser.getFirstName());
				mUserViewOne.setLastName(mUser.getLastName());
				mUserViewOne.setUsername(mUser.getUsername());
				mUserViewOne.setActive(mUser.isActive());
				mUserViewOne.setCreatedAt(mUser.getCreatedAt());
				mUserViewOne.setUpdatedAt(mUser.getUpdatedAt());
			}
		} else if (data instanceof UpdateEvent) {
			UpdateEvent event = (UpdateEvent) data;
			if (mUserViewOne != null) {
				String propertyName = event.getPropertyName();
				if (TextUtils.equals(propertyName, User.FIRST_NAME)) {
					// mUserViewOne.setFirstName((String) event.getNewValue());
					mUserViewOne.setFirstName(mUser.getFirstName());
				} else if (TextUtils.equals(propertyName, User.LAST_NAME)) {
					//mUserViewOne.setLastName((String) event.getNewValue());
					mUserViewOne.setLastName(mUser.getLastName());
				} else if (TextUtils.equals(propertyName, User.USERNAME)) {
					//mUserViewOne.setUsername((String) event.getNewValue());
					mUserViewOne.setUsername(mUser.getUsername());
				} else if (TextUtils.equals(propertyName, User.ACTIVE)) {
					//mUserViewOne.setActive((Boolean) event.getNewValue());
					mUserViewOne.setActive(mUser.isActive());
				} else if (TextUtils.equals(propertyName, User.UPDATED_AT)) {
					//mUserViewOne.setUpdatedAt((Date) event.getNewValue());
					mUserViewOne.setUpdatedAt(mUser.getUpdatedAt());
				}
			}
		}
	}

	public void retrieveUser(int id) {
		mMyModel.retrieveUser(id, this);
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
