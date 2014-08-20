package com.slaterama.quantumsheep.pattern.model;

import com.slaterama.qslib.alpha.app.pattern.Model.ModelEvent;

import java.util.Date;

public class BaseVO {

	protected MyModel mModel;

	private int mId;
	private Date mCreatedAt;
	private Date mUpdatedAt;

	public BaseVO(MyModel model, int id) {
		mModel = model;
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

	protected void onChanged(String action, String property, Object value) {
		mModel.onChanged(action, mId, property, value);
		onUpdated(action);
	}

	protected void onUpdated(String action) {
		mUpdatedAt = new Date();
		mModel.onChanged(action, mId, MyModel.PROP_UPDATED_AT, mUpdatedAt);
	}
}
