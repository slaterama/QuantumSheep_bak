package com.slaterama.quantumsheep.architecture;

import com.slaterama.qslib.alpha.app.architecture.Architecture;
import com.slaterama.quantumsheep.model.ArchitectureTestModel;

public class ArchitectureTestArchitecture implements Architecture {

	private ArchitectureTestModel mModel;

	public ArchitectureTestArchitecture() {
		mModel = new ArchitectureTestModel();

		// mModel.setListener(this);
	}
}
