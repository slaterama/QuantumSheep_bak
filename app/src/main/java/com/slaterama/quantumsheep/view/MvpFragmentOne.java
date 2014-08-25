package com.slaterama.quantumsheep.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.slaterama.qslib.alpha.support.v4.app.PatternManager;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.R;
import com.slaterama.quantumsheep.pattern.MyMvp;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterOne;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterOne.UserViewOne;

import java.util.Date;

import static com.slaterama.quantumsheep.view.MvpActivity.PATTERN_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class MvpFragmentOne extends Fragment
		implements CompoundButton.OnCheckedChangeListener, UserViewOne {

	private final static String STATE_USER_LOAD_REQUESTED = "userLoadRequested";

	private boolean mUserLoadRequested = false;
	private int mUserId = -1;

	private EditText mFirstNameEdit;
	private EditText mLastNameEdit;
	private EditText mUsernameEdit;
	private CheckBox mActiveCbx;
	private TextView mCreatedAtView;
	private TextView mUpdatedAtView;

	private MyMvp mMyMvp;
	private UserPresenterOne mPresenter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null)
			mUserLoadRequested = savedInstanceState.getBoolean(STATE_USER_LOAD_REQUESTED, false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_mvp_one, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mFirstNameEdit = (EditText) view.findViewById(R.id.fragment_mvp_one_first_name);
		mLastNameEdit = (EditText) view.findViewById(R.id.fragment_mvp_one_last_name);
		mUsernameEdit = (EditText) view.findViewById(R.id.fragment_mvp_one_username);
		mActiveCbx = (CheckBox) view.findViewById(R.id.fragment_mvp_one_status);
		mCreatedAtView = (TextView) view.findViewById(R.id.fragment_mvp_one_user_created_at);
		mUpdatedAtView = (TextView) view.findViewById(R.id.fragment_mvp_one_user_updated_at);

		mFirstNameEdit.addTextChangedListener(new EditTextWatcher(mFirstNameEdit));
		mLastNameEdit.addTextChangedListener(new EditTextWatcher(mLastNameEdit));
		mUsernameEdit.addTextChangedListener(new EditTextWatcher(mUsernameEdit));
		mActiveCbx.setOnCheckedChangeListener(this);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mMyMvp = (MyMvp) PatternManager.newInstance(getActivity()).getPattern(PATTERN_ID);
		if (mMyMvp == null)
			throw new IllegalStateException(String.format("Expecting %s pattern with ID %d",
					MyMvp.class.getSimpleName(), PATTERN_ID));
		mPresenter = new UserPresenterOne(this);
	}

	@Override
	public void onStart() {
		super.onStart();
		mMyMvp.registerPresenter(mPresenter);
		if (!mUserLoadRequested) {
			mPresenter.loadUser(mUserId);
			mUserLoadRequested = true;
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(STATE_USER_LOAD_REQUESTED, mUserLoadRequested);
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

	// Interaction listeners

	public void onEditTextChanged(EditText editText, CharSequence text) {
		LogEx.d(String.format("editText=%s, text=%s", editText, String.valueOf(text)));
		switch (editText.getId()) {
			case R.id.fragment_mvp_one_first_name:
				mPresenter.setUserFirstName(String.valueOf(text));
				break;
			case R.id.fragment_mvp_one_last_name:
				mPresenter.setUserLastName(String.valueOf(text));
				break;
			case R.id.fragment_mvp_one_username:
				mPresenter.setUsername(String.valueOf(text));
				break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		mPresenter.setUserActive(isChecked);
	}

	// EmployeeViewOne implementation

	@Override
	public void setFirstName(String firstName) {
		mFirstNameEdit.setText(firstName);
	}

	@Override
	public void setLastName(String lastName) {
		mLastNameEdit.setText(lastName);
	}

	@Override
	public void setUsername(String username) {
		mUsernameEdit.setText(username);
	}

	@Override
	public void setActive(boolean active) {
		mActiveCbx.setChecked(active);
	}

	@Override
	public void setCreatedAt(Date createdAt) {
		mCreatedAtView.setText(String.valueOf(createdAt));
	}

	@Override
	public void setUpdatedAt(Date updatedAt) {
		mUpdatedAtView.setText(String.valueOf(updatedAt));
	}

	// Classes

	protected class EditTextWatcher implements TextWatcher {

		EditText mEditText;

		public EditTextWatcher(EditText editText) {
			mEditText = editText;
		}

		@Override
		public void beforeTextChanged(CharSequence text, int start, int count, int after) {

		}

		@Override
		public void onTextChanged(CharSequence text, int start, int before, int count) {
			MvpFragmentOne.this.onEditTextChanged(mEditText, text);
		}

		@Override
		public void afterTextChanged(Editable text) {

		}
	}
}
