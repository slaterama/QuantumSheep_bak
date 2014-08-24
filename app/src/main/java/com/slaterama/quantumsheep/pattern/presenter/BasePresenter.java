package com.slaterama.quantumsheep.pattern.presenter;

import com.slaterama.qslib.alpha.app.pattern.mvp.Presenter;
import com.slaterama.quantumsheep.pattern.model.MyModel;

public abstract class BasePresenter<V> extends Presenter<MyModel, V> {

	public BasePresenter(V view) {
		super(view);
	}
}
