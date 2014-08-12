package com.slaterama.quantumsheep.pattern.mvptest.model;

public class TestMiscVo extends AbsTestVo {

	private int mClickCount;
	private int mValue;

	public int getClickCount() {
		return mClickCount;
	}

	public void setClickCount(int clickCount) {
		mClickCount = clickCount;
	}

	public void incrementClickCount() {
		mClickCount++;
	}

	public int getValue() {
		return mValue;
	}

	public void setValue(int value) {
		mValue = value;
	}
}
