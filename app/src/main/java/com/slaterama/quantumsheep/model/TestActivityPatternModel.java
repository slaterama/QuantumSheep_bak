package com.slaterama.quantumsheep.model;

import com.slaterama.qslib.alpha.app.pattern.ModelEvent;
import com.slaterama.qslib.alpha.app.pattern.Pattern;
import com.slaterama.qslib.alpha.app.pattern.PatternEvent;
import com.slaterama.quantumsheep.pattern.TestActivityPattern;

public class TestActivityPatternModel {

	private Pattern mPattern;

	private int mClickCount = 0;

	public TestActivityPatternModel(Pattern pattern) {
		mPattern = pattern;
	}

	public int getClickCount() {
		return mClickCount;
	}

	public void setClickCount(int clickCount) {
		mClickCount = clickCount;
		PatternEvent event = new ModelEvent(TestActivityPattern.CLICK_COUNT_CHANGED);
		mPattern.sendEvent(event, mClickCount);
	}

	public void incrementClickCount() {
		mClickCount++;
		PatternEvent event = new ModelEvent(TestActivityPattern.CLICK_COUNT_CHANGED);
		mPattern.sendEvent(event, mClickCount);
	}
}
