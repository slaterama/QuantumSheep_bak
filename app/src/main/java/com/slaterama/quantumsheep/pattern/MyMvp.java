package com.slaterama.quantumsheep.pattern;

import com.slaterama.qslib.alpha.app.pattern.Model;
import com.slaterama.qslib.alpha.app.pattern.mvp.Mvp;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.pattern.model.MyModel;

public class MyMvp extends Mvp {

	public MyMvp() {
		super();
		LogEx.d();
	}

	@Override
	protected Model onCreateModel() {
		return new MyModel();
	}
}
