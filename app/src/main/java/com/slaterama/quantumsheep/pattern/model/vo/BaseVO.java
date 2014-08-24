package com.slaterama.quantumsheep.pattern.model.vo;

import com.slaterama.qslib.alpha.app.pattern.ObservableVO;
import com.slaterama.qslib.utils.objectscompat.ObjectsCompat;

import java.util.Date;

public abstract class BaseVO extends ObservableVO {

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
	public void onUpdated(String property, Object oldValue, Object newValue) {
		super.onUpdated(property, oldValue, newValue);
		Date updatedAt = new Date();
		if (!ObjectsCompat.getInstance().equals(mUpdatedAt, updatedAt)) {
			Date oldUpdatedAt = mUpdatedAt;
			mUpdatedAt = updatedAt;
			notifyUpdated(Property.UPDATED_AT.name(), oldUpdatedAt, updatedAt, false);
		}
	}

	public static enum Property {
		ID,
		CREATED_AT,
		UPDATED_AT
	}
}