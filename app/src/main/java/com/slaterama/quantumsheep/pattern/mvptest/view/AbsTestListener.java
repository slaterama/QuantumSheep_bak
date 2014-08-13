package com.slaterama.quantumsheep.pattern.mvptest.view;

import com.slaterama.qslib.alpha.app.pattern.ViewListener;

import java.util.Date;

public abstract interface AbsTestListener extends ViewListener {

	public void setCreatedAt(Date createdAt);
	public void setUpdatedAt(Date updatedAt);

}
