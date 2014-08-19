package com.slaterama.quantumsheep.pattern.presenter;

import com.slaterama.qslib.alpha.app.pattern.Model;
import com.slaterama.qslib.alpha.app.pattern.Model.ModelEvent;
import com.slaterama.qslib.alpha.app.pattern.mvp.Presenter;
import com.slaterama.qslib.utils.LogEx;

public class EmployeePresenterOne extends Presenter {

	public EmployeePresenterOne(EmployeeViewOne view) {
		super(view);
	}

	@Override
	public void update(Model model, ModelEvent event) {
		String action = event.getAction();
		String property = event.getProperty();
		LogEx.d(String.format("action=%s, property=%s", action, property));
	}

	public static interface EmployeeViewOne extends IView {

	}
}
