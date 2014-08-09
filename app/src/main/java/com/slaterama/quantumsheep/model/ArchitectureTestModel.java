package com.slaterama.quantumsheep.model;

public class ArchitectureTestModel {
	private int mClickCount = 0;

	public void setClickCount(int clickCount) {
		mClickCount = clickCount;

		// TODO Send event somehow
	}

	public void incrementClickCount() {
		mClickCount++;

		// TODO Send event somehow
	}

	public int getClickCount() {
		return mClickCount;
	}
}
