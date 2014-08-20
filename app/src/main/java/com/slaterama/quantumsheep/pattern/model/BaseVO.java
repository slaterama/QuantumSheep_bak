package com.slaterama.quantumsheep.pattern.model;

import com.slaterama.qslib.alpha.app.pattern.ModelEntity;
import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;
import com.slaterama.qslib.utils.objectscompat.ObjectsCompat;

import java.util.Date;

public class BaseVO extends ModelEntity {

	public static final String UPDATED_AT = "updatedAt";

	private int mId;
	private Date mCreatedAt;
	private Date mUpdatedAt;

	public BaseVO(int id) {
		mId = id;
		mCreatedAt = mUpdatedAt = new Date();
	}

	public int getId() {
		return mId;
	}

	public Date getCreatedAt() {
		return mCreatedAt;
	}

	public Date getUpdatedAt() {
		return mUpdatedAt;
	}

	@Override
	protected void notifyUpdated(UpdateEvent event) {
		super.notifyUpdated(event);
		refreshUpdatedAt();
	}

	protected void refreshUpdatedAt() {
		Date updatedAt = new Date();
		if (!ObjectsCompat.getInstance().equals(mUpdatedAt, updatedAt)) {
			UpdateEvent event = new UpdateEvent(this, UPDATED_AT, mUpdatedAt, updatedAt);
			mUpdatedAt = updatedAt;
			setChanged();
			notifyObservers(event);
		}
	}
}
