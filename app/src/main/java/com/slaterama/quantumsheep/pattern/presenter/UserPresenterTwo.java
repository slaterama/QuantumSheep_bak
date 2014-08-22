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
		if (data instanceof RetrieveEvent) {
			RetrieveEvent event = (RetrieveEvent) data;
			mUser = (User) event.getSource();
			if (mUser != null && mUserViewTwo != null) {
				mUserViewTwo.setFullName(makeFullName(mUser.getFirstName(), mUser.getLastName()));
				mUserViewTwo.setActive(mUser.isActive());
				mUserViewTwo.setUpdatedAt(mUser.getUpdatedAt());
			}
		} else if (data instanceof UpdateEvent) {
			UpdateEvent event = (UpdateEvent) data;
			if (mUserViewTwo != null) {
				String propertyName = event.getPropertyName();
				if (TextUtils.equals(propertyName, User.FIRST_NAME)) {
					//mUserViewTwo.setFullName(makeFullName((String) event.getNewValue(), mUser.getLastName()));
					mUserViewTwo.setFullName(makeFullName(mUser.getFirstName(), mUser.getLastName()));
				} else if (TextUtils.equals(propertyName, User.LAST_NAME)) {
					//mUserViewTwo.setFullName(makeFullName(mUser.getFirstName(), (String) event.getNewValue()));
					mUserViewTwo.setFullName(makeFullName(mUser.getFirstName(), mUser.getLastName()));
				} else if (TextUtils.equals(propertyName, User.ACTIVE)) {
					//mUserViewTwo.setActive((Boolean) event.getNewValue());
					mUserViewTwo.setActive(mUser.isActive());
				} else if (TextUtils.equals(propertyName, User.UPDATED_AT)) {
					//mUserViewTwo.setUpdatedAt((Date) event.getNewValue());
					mUserViewTwo.setUpdatedAt(mUser.getUpdatedAt());
				}
			}
		}
	}

	public void retrieveUser(int id) {
		mMyModel.retrieveUser(id, this);
	}

	private String makeFullName(String firstName, String lastName) {
		return String.format("%s %s", firstName, lastName);
	}

	public static interface UserViewTwo extends IView {
		public void setFullName(String fullName);

		public void setActive(boolean active);

		public void setUpdatedAt(Date updatedAt);
	}
}
