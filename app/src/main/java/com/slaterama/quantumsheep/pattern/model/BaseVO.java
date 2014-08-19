package com.slaterama.quantumsheep.pattern.model;

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

	protected void onUpdated() {
		mUpdatedAt = new Date();
		mModel.notifyObjectUpdatedAtChanged(this, mUpdatedAt);
	}
}
