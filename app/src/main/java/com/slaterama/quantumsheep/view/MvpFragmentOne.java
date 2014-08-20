package com.slaterama.quantumsheep.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
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
import java.util.HashMap;
import java.util.Map;

import static com.slaterama.quantumsheep.view.MvpActivity.PATTERN_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class MvpFragmentOne extends Fragment
		implements
		CompoundButton.OnCheckedChangeListener, UserViewOne {
	private static final String ARG_USER_ID = "userId";

	private int mUserId = 2;

	private EditText mFirstNameEdit;
	private EditText mLastNameEdit;
	private EditText mUsernameEdit;
	private CheckBox mStatusCbx;
	private TextView mCreatedAtView;
	private TextView mUpdatedAtView;

	private MyMvp mMyMvp;
	private UserPresenterOne mPresenter;

	public MvpFragmentOne() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mUserId = getArguments().getInt(ARG_USER_ID);
		}
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
		mStatusCbx = (CheckBox) view.findViewById(R.id.fragment_mvp_one_status);
		mCreatedAtView = (TextView) view.findViewById(R.id.fragment_mvp_one_user_created_at);
		mUpdatedAtView = (TextView) view.findViewById(R.id.fragment_mvp_one_user_updated_at);

		mFirstNameEdit.addTextChangedListener(new EditTextWatcher(mFirstNameEdit));
		mLastNameEdit.addTextChangedListener(new EditTextWatcher(mLastNameEdit));
		mUsernameEdit.addTextChangedListener(new EditTextWatcher(mUsernameEdit));
		mStatusCbx.setOnCheckedChangeListener(this);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mMyMvp = (MyMvp) PatternManager.newInstance(getActivity()).getPattern(PATTERN_ID);
		if (mMyMvp == null)
			throw new IllegalStateException(String.format("Expecting %s pattern with ID %d",
					MyMvp.class.getSimpleName(), PATTERN_ID));
		mPresenter = new UserPresenterOne(this);
		mMyMvp.registerPresenter(mPresenter);
		mPresenter.loadUser(mUserId);
	}

	@Override
	public void onStart() {
		super.onStart();
		mMyMvp.registerPresenter(mPresenter);

		// TODO I don't like registering the presenter both in onActivityCreated and here.
		// But I need to load user once, AFTER registering.
	}

	@Override
	public void onStop() {
		super.onStop();
		mMyMvp.unregisterPresenter(mPresenter);
	}

	// Interaction listeners

	public void onEditTextChanged(EditText editText, CharSequence text) {
		LogEx.d(String.format("editText=%s, text=%s", editText, String.valueOf(text)));
		switch (editText.getId()) {
			case R.id.fragment_mvp_one_first_name:
				mPresenter.setUserFirstName(mUserId, String.valueOf(text));
				break;
			case R.id.fragment_mvp_one_last_name:
				mPresenter.setUserLastName(mUserId, String.valueOf(text));
				break;
			case R.id.fragment_mvp_one_username:
				mPresenter.setUsername(mUserId, String.valueOf(text));
				break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		mPresenter.setUserActive(mUserId, isChecked);
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
	public void setStatus(boolean active) {
		mStatusCbx.setChecked(active);
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
