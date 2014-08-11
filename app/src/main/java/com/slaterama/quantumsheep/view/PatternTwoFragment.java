package com.slaterama.quantumsheep.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.slaterama.qslib.alpha.app.pattern.Pattern;
import com.slaterama.qslib.alpha.app.pattern.PatternEvent;
import com.slaterama.qslib.alpha.app.pattern.PatternManager;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.R;

public class PatternTwoFragment extends Fragment
		implements View.OnClickListener {

	private PatternManager mPatternManager;
	private Pattern mPattern;

	private Button mIncrementCountBtn;

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mPatternManager = PatternManager.newInstance(getActivity());
		mPattern = mPatternManager.getPattern(PatternActivity.PATTERN_ID);
		// TODO Eventually something like mPatternManager.registerXYZ(PATTERN_ID, this);
		LogEx.d();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_pattern_two, container, false);
		mIncrementCountBtn = (Button) rootView.findViewById(R.id.pattern_test_increment_count);
		mIncrementCountBtn.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		PatternEvent event = new PatternEvent() {};
		mPattern.sendEvent(event, null);
		// TODO Eventually something like mPatternManager.sendEvent(event, null);
	}
}
