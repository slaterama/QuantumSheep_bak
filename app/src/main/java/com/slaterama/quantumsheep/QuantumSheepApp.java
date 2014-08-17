package com.slaterama.quantumsheep;

import android.app.Application;

import com.slaterama.qslib.utils.AppUtils;
import com.slaterama.qslib.utils.LogEx;

public class QuantumSheepApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		LogEx.setTagFormat("%s|%s",
				AppUtils.getApplicationLabel(this),
				LogEx.Placeholder.SIMPLE_CLASS_NAME);
	}
}
