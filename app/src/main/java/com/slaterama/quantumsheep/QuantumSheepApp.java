package com.slaterama.quantumsheep;

import android.app.Application;

import com.slaterama.qslib.utils.AppUtils;
import com.slaterama.qslib.utils.LogEx;

/* TODO
	Next steps:
	1. Introduce some sort of "State" into presenters. (Avoiding double-click processing etc.)
	2. In Model, make some sort of "loading" indicator so two objects requesting the same
	   entity don't request to load it twice
 */

public class QuantumSheepApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		LogEx.setTagFormat("%s|%s",
				AppUtils.getApplicationLabel(this),
				LogEx.Placeholder.SIMPLE_CLASS_NAME);
	}
}
