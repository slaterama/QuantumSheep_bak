package com.slaterama.quantumsheep.pattern.mvptest;

import com.slaterama.qslib.alpha.app.pattern.Pattern;
import com.slaterama.qslib.alpha.app.pattern.PatternEvent;
import com.slaterama.qslib.alpha.app.pattern.Presenter;

import java.util.Map;
import java.util.WeakHashMap;

public class MvpTestPattern implements Pattern {

	protected Map<Presenter, Pattern> mPresenterMap;

	public MvpTestPattern() {
		super();
		mPresenterMap = new WeakHashMap<Presenter, Pattern>();
	}

	// TODO mPresenterMap & these two methods should be in an abstract ancestor


	@Override
	public void registerPresenter(Presenter presenter) {
		mPresenterMap.put(presenter, this);
	}

	@Override
	public void unregisterPresenter(Presenter presenter) {
		mPresenterMap.remove(presenter);
	}

	@Override
	public void sendEvent(PatternEvent event, Object data) {

	}
}
