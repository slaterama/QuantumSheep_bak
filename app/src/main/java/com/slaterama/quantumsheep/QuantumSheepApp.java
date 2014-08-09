package com.slaterama.quantumsheep;

import android.app.Application;
import android.os.Bundle;

import com.slaterama.qslib.alpha.app.archnew.ArchitectureManager;
import com.slaterama.qslib.alpha.app.archnew.ArchitectureManager.ArchitectureCallbacks;
import com.slaterama.qslib.alpha.support.v4.app.archnew.Architecture;
import com.slaterama.qslib.alpha.support.v4.app.archnew.ArchitectureMVP;
import com.slaterama.qslib.utils.LogEx;

/**
 * Created by slaterama on 8/8/14.
 */
public class QuantumSheepApp extends Application
		implements ArchitectureCallbacks {

	private static final int APP_ARCHITECTURE_ID = 0;

	private Architecture mArchitecture;

	@Override
	public void onCreate() {
		super.onCreate();
		LogEx.setTagFormat("QuantumSheepTests");
		mArchitecture = ArchitectureManager.getInstance().getArchitecture(
				this, APP_ARCHITECTURE_ID, null, this);
	}

	@Override
	public Architecture onCreateArchitecture(int id, Bundle args) {
		return new ArchitectureMVP();
	}
}
