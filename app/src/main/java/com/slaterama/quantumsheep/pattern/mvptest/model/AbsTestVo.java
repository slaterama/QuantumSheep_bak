package com.slaterama.quantumsheep.pattern.mvptest.model;

import java.util.Date;

public abstract class AbsTestVo {

	protected Date mCreatedAt;
	protected Date mUpdatedAt;

	public AbsTestVo() {
		super();
		mCreatedAt = new Date();
		mUpdatedAt = new Date();
	}

	public Date getCreatedAt() {
		return mCreatedAt;
	}

	public void setCreatedAt(Date createdAt) {
		mCreatedAt = createdAt;
	}

	public void refreshCreatedAt() {
		mCreatedAt = new Date();
	}

	public Date getUpdatedAt() {
		return mUpdatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		mUpdatedAt = updatedAt;
	}

	public void refreshUpdatedAt() {
		mUpdatedAt = new Date();
	}
}
