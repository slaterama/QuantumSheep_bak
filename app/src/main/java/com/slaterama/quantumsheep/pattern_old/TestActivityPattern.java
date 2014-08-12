package com.slaterama.quantumsheep.pattern_old;

import com.slaterama.qslib.alpha.app.pattern.ModelEvent;
import com.slaterama.qslib.alpha.app.pattern.Pattern;
import com.slaterama.qslib.alpha.app.pattern.PatternEvent;
import com.slaterama.qslib.alpha.app.pattern.ViewEvent;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.model.TestActivityPatternModel;

import java.util.Set;
import java.util.WeakHashMap;

public class TestActivityPattern implements Pattern {

	public final static int INCREMENT_CLICK_COUNT = 1001;

	public final static int CLICK_COUNT_CHANGED = 2001;

	private TestActivityPatternModel mModel;

	private WeakHashMap<TestPatternListener, TestActivityPattern> mListenerMap;

	public TestActivityPattern() {
		super();
		mModel = new TestActivityPatternModel(this);
	}

	@Override
	public void sendEvent(PatternEvent event, Object data) {
		LogEx.d(String.format("event=%s, action=%d, data=%s", event, event.getAction(), String.valueOf(data)));
		int action = event.getAction();
		if (event instanceof ViewEvent) {
			switch (action) {
				case INCREMENT_CLICK_COUNT:
					mModel.incrementClickCount();
					break;
			}
		} else if (event instanceof ModelEvent) {
			switch (action) {
				case CLICK_COUNT_CHANGED:
					if (mListenerMap != null) {
						Set<TestPatternListener> keySet = mListenerMap.keySet();
						for (TestPatternListener listener : keySet) {
							listener.onClickCountChanged((Integer) data);
						}
					}
					break;
			}
		}
	}

	public void registerListener(TestPatternListener listener) {
		if (mListenerMap == null)
			mListenerMap = new WeakHashMap<TestPatternListener, TestActivityPattern>();
		mListenerMap.put(listener, this);
		listener.onClickCountChanged(mModel.getClickCount());
	}

	public void unregisterListener(TestPatternListener listener) {
		if (mListenerMap != null)
			mListenerMap.remove(listener);
	}
}
