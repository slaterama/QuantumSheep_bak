package com.slaterama.quantumsheep.pattern.presenter;

import com.slaterama.qslib.alpha.app.pattern.mvp.Presenter;

import java.util.Observable;

public class EmployeePresenterTwo extends Presenter {

	public EmployeePresenterTwo(EmployeeViewTwo view) {
		super(view);
	}

	@Override
	public void update(Observable observable, Object data) {
		super.update(observable, data);
	}

	public static interface EmployeeViewTwo extends IView {

	}
}
