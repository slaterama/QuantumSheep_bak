package com.slaterama.quantumsheep;

import android.app.Application;

import com.slaterama.qslib.alpha.app.pattern.Pattern;
import com.slaterama.qslib.utils.AppUtils;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.pattern_old.TestAppPattern;

public class QuantumSheepApp extends Application {

	private Pattern mAppPattern;

	@Override
	public void onCreate() {
		super.onCreate();
		LogEx.setTagFormat("%s|%s", AppUtils.getApplicationLabel(this), LogEx.Placeholder.SIMPLE_CLASS_NAME);
		mAppPattern = new TestAppPattern();
	}
}
