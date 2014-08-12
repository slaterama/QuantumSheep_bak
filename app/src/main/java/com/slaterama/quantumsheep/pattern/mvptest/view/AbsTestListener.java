package com.slaterama.quantumsheep.pattern.mvptest.view;

import java.util.Date;

public abstract interface AbsTestListener {

	public void setCreatedAt(Date createdAt);
	public void setUpdatedAt(Date updatedAt);

}
