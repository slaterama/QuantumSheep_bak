package com.slaterama.quantumsheep.pattern.mvptest.presenter;

import com.slaterama.quantumsheep.pattern.mvptest.MvpTestPattern;
import com.slaterama.quantumsheep.pattern.mvptest.view.TestOneListener;

public class TestOnePresenter extends AbsTestPresenter {

	public TestOnePresenter(MvpTestPattern pattern, TestOneListener listener) {
		super(pattern, listener);
	}
}
