package com.slaterama.quantumsheep.pattern.model.vo;

import com.slaterama.qslib.alpha.app.pattern.EntityObject;
import com.slaterama.qslib.alpha.app.pattern.event.UpdateEvent;
import com.slaterama.qslib.utils.objectscompat.ObjectsCompat;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractEntity extends EntityObject
		implements Serializable {

	protected int mId;
	protected Date mCreatedAt;
	protected Date mUpdatedAt;

	public AbstractEntity(int id) {
		super();
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
	public void notifySubscribers(Object data) {
		super.notifySubscribers(data);
		Date updatedAt = new Date();
		if (!ObjectsCompat.getInstance().equals(mUpdatedAt, updatedAt)) {
			Date oldValue = mUpdatedAt;
			mUpdatedAt = updatedAt;
			super.notifySubscribers(new UpdateEvent(this, Property.UPDATED_AT.name(), oldValue, updatedAt));
		}
	}

	public static enum Property {
		ID,
		CREATED_AT,
		UPDATED_AT
	}
}
