package com.slaterama.quantumsheep.view;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slaterama.qslib.alpha.support.v4.app.PatternManager;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThirdPatternFragment.OnThirdFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThirdPatternFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ThirdPatternFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnThirdFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment ThirdPatternFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ThirdPatternFragment newInstance(String param1, String param2) {
		ThirdPatternFragment fragment = new ThirdPatternFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public ThirdPatternFragment() {
		// Required empty public constructor
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnThirdFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnThirdFragmentInteractionListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}

		Fragment fragment = getFragmentManager().findFragmentByTag(TestFragment.TAG);
		if (fragment == null) {
			fragment = Fragment.instantiate(getActivity(), TestFragment.class.getName());
			getFragmentManager().beginTransaction().add(fragment, TestFragment.TAG).commit();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_third_pattern, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		PatternManager patternManager = PatternManager.newInstance(getActivity());
		// TODO Register a presenter or something
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onThirdFragmentInteraction(uri);
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		LogEx.d();
		if (isRemoving()) {
			Fragment fragment = getFragmentManager().findFragmentByTag(TestFragment.TAG);
			if (fragment != null) {
				getFragmentManager().beginTransaction()
						.remove(fragment)
						.commit();
				getFragmentManager().executePendingTransactions();
			}
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
		LogEx.d();
	}

	public static class TestFragment extends Fragment {

		public static final String TAG = "TestFragment";

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setRetainInstance(true);
			LogEx.d();
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			LogEx.d();
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
	public interface OnThirdFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onThirdFragmentInteraction(Uri uri);
	}

}
