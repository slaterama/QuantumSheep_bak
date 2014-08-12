package com.slaterama.quantumsheep.pattern.mvptest.model;

public final class TestUserVo extends AbsTestVo {

	private int mUserId;
	private String mUsername;
	private String mFirstName;
	private String mLastName;

	public TestUserVo() {

	}

	public int getUserId() {
		return mUserId;
	}

	public void setUserId(int userId) {
		mUserId = userId;
	}

	public String getUsername() {
		return mUsername;
	}

	public void setUsername(String username) {
		mUsername = username;
	}

	public String getFirstName() {
		return mFirstName;
	}

	public void setFirstName(String firstName) {
		mFirstName = firstName;
	}

	public String getLastName() {
		return mLastName;
	}

	public void setLastName(String lastName) {
		mLastName = lastName;
	}
}
