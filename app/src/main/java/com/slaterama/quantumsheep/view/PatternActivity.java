package com.slaterama.quantumsheep.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.slaterama.qslib.alpha.app.pattern.Pattern;
import com.slaterama.qslib.alpha.app.pattern.PatternManager;
import com.slaterama.qslib.alpha.app.pattern.PatternManager.PatternCallbacks;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.R;
import com.slaterama.quantumsheep.pattern.TestActivityPattern;

public class PatternActivity extends ActionBarActivity
		implements PatternCallbacks {

	private PatternManager mPatternManager;
	private Pattern mActivityPattern;

	private PatternOneFragment mFragmentOne;
	private PatternTwoFragment mFragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);

		mPatternManager = PatternManager.newInstance(this);
		mActivityPattern = mPatternManager.initPattern(null, this);
		LogEx.d();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		FragmentManager fragmentManager = getSupportFragmentManager();
		mFragmentOne = (PatternOneFragment) fragmentManager.findFragmentById(R.id.architecture_fragment_one);
		mFragmentTwo = (PatternTwoFragment) fragmentManager.findFragmentById(R.id.architecture_fragment_two);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.architecture, menu);
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
	public Pattern onCreatePattern(int id, Bundle args) {
		return new TestActivityPattern();
	}
}
