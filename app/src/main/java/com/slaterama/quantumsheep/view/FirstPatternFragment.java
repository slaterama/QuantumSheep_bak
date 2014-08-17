package com.slaterama.quantumsheep.view;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slaterama.qslib.alpha.support.v4.app.PatternManager;
import com.slaterama.qslib.alpha.support.v4.app.RetainedInstanceManager;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstPatternFragment.OnFirstFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstPatternFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FirstPatternFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFirstFragmentInteractionListener mListener;

	private RetainedInstanceManager mRetainedInstanceManager;
	private Object mObject;

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment FirstPatternFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static FirstPatternFragment newInstance(String param1, String param2) {
		FirstPatternFragment fragment = new FirstPatternFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public FirstPatternFragment() {
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
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}

		// TODO Causes my own exception -- how to handle?
		PatternManager patternManager = PatternManager.newInstance(getActivity());
		LogEx.d();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_first_pattern, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		FragmentActivity activity = getActivity();
		PatternManager patternManager = PatternManager.newInstance(activity);

		//LogEx.d();

		/*
		PatternManager patternManager = PatternManager.newInstance(getActivity());

		mRetainedInstanceManager = RetainedInstanceManager.newInstance(this);
		if (mRetainedInstanceManager.containsKey("object")) {
			mObject = mRetainedInstanceManager.get("object");
		} else {
			mObject = new Object();
			mRetainedInstanceManager.put("object", mObject);
		}
		// LogEx.d(String.format("mObject=%s", mObject));
		// TODO Register a presenter or something
		*/
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
	public interface OnFirstFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFirstFragmentInteraction(Uri uri);
	}

}
