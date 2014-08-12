package com.slaterama.quantumsheep.pattern;

import com.slaterama.qslib.alpha.app.pattern.Pattern;
import com.slaterama.qslib.alpha.app.pattern.PatternEvent;
import com.slaterama.qslib.utils.LogEx;

public class TestActivityPattern implements Pattern {

	public TestActivityPattern() {
		super();
//		LogEx.d();
	}

	@Override
	public void sendEvent(PatternEvent event, Object data) {
		LogEx.d(String.valueOf(this));
	}
}
