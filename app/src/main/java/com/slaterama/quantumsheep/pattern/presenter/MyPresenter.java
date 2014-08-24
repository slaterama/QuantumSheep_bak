package com.slaterama.quantumsheep.pattern.presenter;

import com.slaterama.qslib.alpha.app.pattern.mvp.Presenter;
import com.slaterama.quantumsheep.pattern.model.MyModel;

public abstract class MyPresenter<V> extends Presenter<MyModel, V> {

	public MyPresenter(V view) {
		super(view);
	}
}
