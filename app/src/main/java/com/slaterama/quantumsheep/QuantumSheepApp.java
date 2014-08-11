package com.slaterama.quantumsheep;

import android.app.Application;
import android.os.Bundle;

import com.slaterama.qslib.alpha.app.pattern.Pattern;
import com.slaterama.qslib.alpha.app.pattern.PatternManager;
import com.slaterama.qslib.alpha.app.pattern.PatternManager.PatternCallbacks;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.pattern.TestAppPattern;

public class QuantumSheepApp extends Application
		implements PatternCallbacks {

	private PatternManager mPatternManager;
	private Pattern mAppPattern;

	@Override
	public void onCreate() {
		super.onCreate();
		LogEx.setTagFormat("QuantumSheepTests");
		mPatternManager = PatternManager.newInstance(this);
		mAppPattern = mPatternManager.initPattern(null, this);
	}

	@Override
	public Pattern onCreatePattern(int id, Bundle args) {
		return new TestAppPattern();
	}
}
