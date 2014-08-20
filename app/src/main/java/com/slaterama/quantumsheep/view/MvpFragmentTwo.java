package com.slaterama.quantumsheep.view;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.slaterama.qslib.alpha.support.v4.app.PatternManager;
import com.slaterama.quantumsheep.R;
import com.slaterama.quantumsheep.pattern.MyMvp;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterOne;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterTwo;
import com.slaterama.quantumsheep.pattern.presenter.UserPresenterTwo.UserViewTwo;

import java.util.Date;

import static com.slaterama.quantumsheep.view.MvpActivity.PATTERN_ID;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MvpFragmentTwo.OnSecondFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MvpFragmentTwo#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MvpFragmentTwo extends Fragment
		implements UserViewTwo {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

	/**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondPatternFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MvpFragmentTwo newInstance(String param1, String param2) {
        MvpFragmentTwo fragment = new MvpFragmentTwo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	private int mUserId = 2;

	private TextView mFullNameView;
	private TextView mStatusView;
	private TextView mUpdatedAtView;

	private OnSecondFragmentInteractionListener mListener;

	private MyMvp mMyMvp;
	private UserPresenterTwo mPresenter;

	public MvpFragmentTwo() {
        // Required empty public constructor
    }

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnSecondFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnSecondFragmentInteractionListener");
		}
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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

	// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSecondFragmentInteraction(uri);
        }
    }

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	// UserViewTwo implementation


	@Override
	public void setFullName(String fullName) {
		mFullNameView.setText(fullName);
	}

	@Override
	public void setStatus(boolean active) {
		mStatusView.setText(active ? "Active" : "Inactive");
	}

	@Override
	public void setUpdatedAt(Date updatedAt) {
		mUpdatedAtView.setText(String.valueOf(updatedAt));
	}

	/**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSecondFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onSecondFragmentInteraction(Uri uri);
    }

}