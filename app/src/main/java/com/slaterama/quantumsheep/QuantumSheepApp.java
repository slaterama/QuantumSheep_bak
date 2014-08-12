package com.slaterama.quantumsheep;

import android.app.Application;

import com.slaterama.qslib.alpha.app.pattern.Pattern;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.pattern.TestAppPattern;

public class QuantumSheepApp extends Application {

	private Pattern mAppPattern;

	@Override
	public void onCreate() {
		super.onCreate();
		LogEx.setTagFormat("QuantumSheepTests");
		mAppPattern = new TestAppPattern();
	}
}
