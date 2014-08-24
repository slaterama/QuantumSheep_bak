package com.slaterama.quantumsheep.pattern;

import com.slaterama.qslib.alpha.app.pattern.mvp.Mvp;
import com.slaterama.quantumsheep.pattern.model.MyModel;

public class MyMvp extends Mvp<MyModel> {

	@Override
	protected MyModel onCreateModel() {
		return new MyModel();
	}
}
