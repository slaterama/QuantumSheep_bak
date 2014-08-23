package com.slaterama.quantumsheep.pattern.model.vo;

import com.slaterama.qslib.alpha.app.pattern.ModelEntity;
import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;
import com.slaterama.qslib.utils.objectscompat.ObjectsCompat;

import java.util.Date;

public abstract class BaseVO extends ModelEntity {

	protected int mId;
	protected Date mCreatedAt;
	protected Date mUpdatedAt;

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
			UpdateEvent event = new UpdateEvent(this, Property.UPDATED_AT, mUpdatedAt, updatedAt);
			mUpdatedAt = updatedAt;
			setChanged();
			notifyObservers(event);
		}
	}

	public static enum Property {
		ID,
		CREATED_AT,
		UPDATED_AT
	}
}