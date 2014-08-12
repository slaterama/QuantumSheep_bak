package com.slaterama.quantumsheep.view.mvptest;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.slaterama.quantumsheep.R;

public class MvpTestActivity extends ActionBarActivity
		implements MvpTestOneFragment.OnFragmentInteractionListener,
		MvpTestTwoFragment.OnFragmentInteractionListener {

	// TODO private MvpTestPattern mMvpTestPattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvptest);

		// TODO mMvpTestPattern = ...
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mvp_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onFragmentInteraction(Uri uri) {

	}
}
