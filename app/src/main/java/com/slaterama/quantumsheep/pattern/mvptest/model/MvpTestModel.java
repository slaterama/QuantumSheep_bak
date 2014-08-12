package com.slaterama.quantumsheep.pattern.mvptest.model;

public class MvpTestModel {

	private TestUserVo mUserVo;
	private TestMiscVo mMiscVo;

	public MvpTestModel() {
		mUserVo = new TestUserVo();
		mMiscVo = new TestMiscVo();
	}
}
