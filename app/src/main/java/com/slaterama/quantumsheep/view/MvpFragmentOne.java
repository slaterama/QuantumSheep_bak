package com.slaterama.quantumsheep.view;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.slaterama.qslib.alpha.support.v4.app.PatternManager;
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
 * Activities that contain this fragment must implement the
 * {@link MvpFragmentOne.OnFirstFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MvpFragmentOne#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MvpFragmentOne extends Fragment
		implements View.OnFocusChangeListener,
		CompoundButton.OnCheckedChangeListener, UserViewOne {
	private static final String ARG_USER_ID = "userId";

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param userId The user ID.
	 * @return A new instance of fragment FirstPatternFragment.
	 */
	public static MvpFragmentOne newInstance(int userId) {
		MvpFragmentOne fragment = new MvpFragmentOne();
		Bundle args = new Bundle();
		args.putInt(ARG_USER_ID, userId);
		fragment.setArguments(args);
		return fragment;
	}

	private int mUserId = 2;

	private EditText mFirstNameEdit;
	private EditText mLastNameEdit;
	private EditText mUsernameEdit;
	private CheckBox mStatusCbx;
	private TextView mCreatedAtView;
	private TextView mUpdatedAtView;

	private OnFirstFragmentInteractionListener mListener;

	private MyMvp mMyMvp;
	private UserPresenterOne mPresenter;

	private Map<EditText, Editable> mPreviousValuesMap;

	public MvpFragmentOne() {
		// Required empty public constructor
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFirstFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFirstFragmentInteractionListener");
		}
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

		mPreviousValuesMap = new HashMap<EditText, Editable>();
		mPreviousValuesMap.put(mFirstNameEdit, mFirstNameEdit.getText());
		mPreviousValuesMap.put(mLastNameEdit, mLastNameEdit.getText());
		mPreviousValuesMap.put(mUsernameEdit, mUsernameEdit.getText());

		mFirstNameEdit.setOnFocusChangeListener(this);
		mLastNameEdit.setOnFocusChangeListener(this);
		mUsernameEdit.setOnFocusChangeListener(this);

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

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFirstFragmentInteraction(uri);
		}
	}

	// Interaction listeners

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (v instanceof EditText && !hasFocus) {
			EditText editText = (EditText) v;
			if (!TextUtils.equals(editText.getText(), mPreviousValuesMap.get(editText))) {
				mPreviousValuesMap.put(editText, editText.getText());
				switch (editText.getId()) {
					case R.id.fragment_mvp_one_first_name: {
//						mPresenter.doSomething;
						break;
					}
					case R.id.fragment_mvp_one_last_name: {
//						mPresenter.doSomething;
						break;
					}
					case R.id.fragment_mvp_one_username: {
//						mPresenter.doSomething;
						break;
					}
				}
			}
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

	// Interfaces

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFirstFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFirstFragmentInteraction(Uri uri);
	}

}
