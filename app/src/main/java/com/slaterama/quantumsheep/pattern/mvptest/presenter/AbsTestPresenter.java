package com.slaterama.quantumsheep.pattern.mvptest.presenter;

import com.slaterama.quantumsheep.pattern.mvptest.view.AbsTestListener;

public abstract class AbsTestPresenter {

	private AbsTestListener mListener;

	public AbsTestPresenter(AbsTestListener listener) {
		super();
		mListener = listener;
	}

	public AbsTestListener getListener() {
		return mListener;
	}

	public void setListener(AbsTestListener listener) {
		mListener = listener;
	}
}
