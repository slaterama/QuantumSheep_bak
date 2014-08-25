package com.slaterama.quantumsheep.pattern.presenter;

import com.slaterama.qslib.alpha.app.pattern.event.CreateEvent;
import com.slaterama.qslib.alpha.app.pattern.event.DeleteEvent;
import com.slaterama.qslib.alpha.app.pattern.event.RetrieveEvent;
import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;
import com.slaterama.quantumsheep.pattern.model.vo.User;

public interface UserPresenter {
	public void onUserCreated(CreateEvent event, User user);

	public void onUserRetrieved(RetrieveEvent event, User user);

	public void onUserUpdated(UpdateEvent event, User user);

	public void onUserDeleted(DeleteEvent event, User user);
}
