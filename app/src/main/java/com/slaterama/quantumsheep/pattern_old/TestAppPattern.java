package com.slaterama.quantumsheep.pattern_old;

import com.slaterama.qslib.alpha.app.pattern.Pattern;
import com.slaterama.qslib.alpha.app.pattern.PatternEvent;
import com.slaterama.qslib.utils.LogEx;

public class TestAppPattern implements Pattern {

	public TestAppPattern() {
		super();
//		LogEx.d();
	}

	@Override
	public void sendEvent(PatternEvent event, Object data) {
		LogEx.d(String.valueOf(this));
	}
}
