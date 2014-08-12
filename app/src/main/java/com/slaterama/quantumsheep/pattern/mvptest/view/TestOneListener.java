package com.slaterama.quantumsheep.pattern.mvptest.view;

public interface TestOneListener extends AbsTestListener {

	public void setUsername(String name);
	public void setClickCount(int clickCount);
	public void setException(Exception e);

}
