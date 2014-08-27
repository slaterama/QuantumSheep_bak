package com.slaterama.quantumsheep.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.slaterama.qslib.alpha.support.v4.app.PatternManager;
import com.slaterama.quantumsheep.R;
import com.slaterama.quantumsheep.pattern.MyMvp;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterTwo;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterTwo.UserViewTwo;

import java.util.Date;

import static com.slaterama.quantumsheep.view.MvpActivity.PATTERN_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class MvpFragmentTwo extends Fragment
		implements UserViewTwo {

	private int mUserId = -1;

	private TextView mFullNameView;
	private TextView mStatusView;
	private TextView mUpdatedAtView;

	private MyMvp mMyMvp;
	private UserPresenterTwo mPresenter;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mvp_two, container, false);
    }

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mFullNameView = (TextView) view.findViewById(R.id.fragment_mvp_two_name);
		mStatusView = (TextView) view.findViewById(R.id.fragment_mvp_two_status);
		mUpdatedAtView = (TextView) view.findViewById(R.id.fragment_mvp_two_updated_at);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mMyMvp = (MyMvp) PatternManager.newInstance(getActivity()).getPattern(PATTERN_ID);
		if (mMyMvp == null)
			throw new IllegalStateException(String.format("Expecting %s pattern with ID %d",
					MyMvp.class.getSimpleName(), PATTERN_ID));
		mPresenter = new UserPresenterTwo(this);
		mMyMvp.registerPresenter(mPresenter);
	}

	@Override
	public void onStart() {
		super.onStart();
		mMyMvp.registerPresenter(mPresenter);
		if (!mMyMvp.isPresenterRegistered(mPresenter))
			mMyMvp.registerPresenter(mPresenter);
	}

	@Override
	public void onStop() {
		super.onStop();
		mMyMvp.unregisterPresenter(mPresenter);
	}

	// Methods

	public void setUserId(int userId) {
		mUserId = userId;
		if (mPresenter != null)
			mPresenter.loadUser(mUserId);
	}

	// UserViewTwo implementation

	@Override
	public void setFullName(String fullName) {
		mFullNameView.setText(fullName);
	}

	@Override
	public void setActive(boolean active) {
		mStatusView.setText(active ? "Active" : "Inactive");
	}

	@Override
	public void setUpdatedAt(Date updatedAt) {
		mUpdatedAtView.setText(String.valueOf(updatedAt));
	}
}
