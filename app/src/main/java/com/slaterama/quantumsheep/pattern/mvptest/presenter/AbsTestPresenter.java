package com.slaterama.quantumsheep.pattern.mvptest.presenter;

import com.slaterama.qslib.alpha.app.pattern.Pattern;
import com.slaterama.qslib.alpha.app.pattern.Presenter;
import com.slaterama.quantumsheep.pattern.mvptest.view.AbsTestListener;

public abstract class AbsTestPresenter
		implements Presenter {

	private Pattern mPattern;
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

	public Pattern getPattern() {
		return mPattern;
	}

	public void setPattern(Pattern pattern) {
		mPattern = pattern;
	}
}
