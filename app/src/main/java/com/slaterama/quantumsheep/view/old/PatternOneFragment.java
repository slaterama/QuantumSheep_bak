package com.slaterama.quantumsheep.view.old;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.slaterama.qslib.alpha.app.pattern.ViewEvent;
import com.slaterama.qslib.alpha.support.v4.app.PatternManager;
import com.slaterama.quantumsheep.R;
import com.slaterama.quantumsheep.pattern_old.TestActivityPattern;
import com.slaterama.quantumsheep.pattern_old.TestPatternListener;

public class PatternOneFragment extends Fragment
		implements View.OnClickListener,
		TestPatternListener {

	private PatternManager mPatternManager;
	private TestActivityPattern mPattern;

	private TextView mClickCountTxt;
	private Button mIncrementCountBtn;

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mPatternManager = PatternManager.from(getActivity());
		mPattern = (TestActivityPattern) mPatternManager.getPattern(PatternActivity.PATTERN_ID);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_pattern_one, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mClickCountTxt = (TextView) view.findViewById(R.id.pattern_test_click_count);
		mIncrementCountBtn = (Button) view.findViewById(R.id.pattern_test_increment_count);
		mIncrementCountBtn.setOnClickListener(this);
	}

	@Override
	public void onStart() {
		super.onStart();
		mPattern.registerListener(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		mPattern.unregisterListener(this);
	}

	@Override
	public void onClick(View v) {
		ViewEvent event = new ViewEvent(TestActivityPattern.INCREMENT_CLICK_COUNT);
		mPattern.sendEvent(event, null);
	}

	@Override
	public void onClickCountChanged(int clickCount) {
		mClickCountTxt.setText(getString(R.string.pattern_test_click_count, clickCount));
	}
}
