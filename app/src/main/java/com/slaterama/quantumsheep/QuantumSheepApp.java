package com.slaterama.quantumsheep;

import android.app.Application;
import android.os.Bundle;

import com.slaterama.qslib.alpha.app.architecture.Architecture;
import com.slaterama.qslib.alpha.app.architecture.ArchitectureMVP;
import com.slaterama.qslib.alpha.app.architecture.AbsArchitectureManager.ArchitectureCallbacks;
import com.slaterama.qslib.alpha.app.architecture.ArchitectureManager;
import com.slaterama.qslib.alpha.app.pattern.PatternManager;
import com.slaterama.qslib.utils.LogEx;

public class QuantumSheepApp extends Application
		implements ArchitectureCallbacks {

	private static final int APP_ARCHITECTURE_ID = 0;

	private Architecture mArchitecture;

	private PatternManager mPatternManager;

	@Override
	public void onCreate() {
		super.onCreate();
		LogEx.setTagFormat("QuantumSheepTests");
		mArchitecture = ArchitectureManager.getInstance().getArchitecture(
				this, APP_ARCHITECTURE_ID, null, this);

		mPatternManager = PatternManager.newInstance(this);
	}

	@Override
	public Architecture onCreateArchitecture(int id, Bundle args) {
		return new ArchitectureMVP();
	}
}
