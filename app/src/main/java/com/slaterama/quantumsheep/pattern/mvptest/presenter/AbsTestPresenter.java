package com.slaterama.quantumsheep.pattern.mvptest.presenter;

import com.slaterama.qslib.alpha.app.pattern.Pattern;
import com.slaterama.quantumsheep.pattern.mvptest.view.AbsTestListener;

public abstract class AbsTestPresenter {

	private Pattern mPattern;
	private AbsTestListener mListener;

	public AbsTestPresenter(Pattern pattern, AbsTestListener listener) {
		super();
		mPattern = pattern;
		mListener = listener;
	}

	public AbsTestListener getListener() {
		return mListener;
	}

	public void setListener(AbsTestListener listener) {
		mListener = listener;
	}
}
