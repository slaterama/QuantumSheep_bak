package com.slaterama.quantumsheep.pattern.presenter;

import com.slaterama.qslib.alpha.app.pattern.mvp.Presenter;

public class EmployeePresenterOne extends Presenter {

	public EmployeePresenterOne(EmployeeViewOne view) {
		super(view);
	}

	public static interface EmployeeViewOne extends IView {

	}
}
