package com.slaterama.quantumsheep;

import android.app.Application;

import com.slaterama.qslib.utils.AppUtils;
import com.slaterama.qslib.utils.LogEx;

/* TODO
	Next steps:
	1. Figure out how to deal with the redundant "registerPresenter" in Fragments
	2. Build an "AcceptingEditText" class somehow, with "acceptText()" type functionality
	3. Introduce some sort of "State" into presenters. (Avoiding double-click processing etc.)
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
