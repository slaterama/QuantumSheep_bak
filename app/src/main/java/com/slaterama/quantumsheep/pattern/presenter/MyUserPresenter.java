package com.slaterama.quantumsheep.pattern.presenter;

import com.slaterama.qslib.alpha.app.pattern.event.CreateEvent;
import com.slaterama.qslib.alpha.app.pattern.event.DeleteEvent;
import com.slaterama.qslib.alpha.app.pattern.event.RetrieveEvent;
import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;
import com.slaterama.quantumsheep.pattern.model.vo.User;

import java.util.EventObject;
import java.util.Observable;

public abstract class MyUserPresenter<V> extends MyPresenter<V>
		implements UserPresenter {

	protected int mUserId;
	protected User mUser;

	public MyUserPresenter(V view) {
		super(view);
	}

	@Override
	public void update(Observable observable, Object data) {
		if (!(data instanceof EventObject))
			return;
		EventObject event = (EventObject) data;
		if (!(event.getSource() instanceof User))
			return;
		User user = (User) event.getSource();
		if (user.getId() != mUserId)
			return;

		if (event instanceof CreateEvent)
			onUserCreated((CreateEvent) event, user);
		else if (event instanceof RetrieveEvent) {
			mUser = user;
			onUserRetrieved((RetrieveEvent) event, user);
			updateView(user);
		} else if (event instanceof UpdateEvent)
			onUserUpdated((UpdateEvent) event, user);
		else if (event instanceof DeleteEvent)
			onUserDeleted((DeleteEvent) event, user);
	}

	public void loadUser(int id) {
		mUserId = id;
		mUser = mModel.getUser(id);
		if (mUser != null)
			updateView(mUser);
	}

	protected abstract void updateView(User user);

	@Override
	public void onUserCreated(CreateEvent event, User user) {
	}

	@Override
	public void onUserRetrieved(RetrieveEvent event, User user) {
	}

	@Override
	public void onUserUpdated(UpdateEvent event, User user) {
	}

	@Override
	public void onUserDeleted(DeleteEvent event, User user) {
	}
}
