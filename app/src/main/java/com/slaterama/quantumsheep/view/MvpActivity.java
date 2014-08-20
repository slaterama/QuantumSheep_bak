package com.slaterama.quantumsheep.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.slaterama.qslib.alpha.app.pattern.Pattern;
import com.slaterama.qslib.alpha.support.v4.app.PatternManager;
import com.slaterama.qslib.utils.LogEx;
import com.slaterama.quantumsheep.R;
import com.slaterama.quantumsheep.pattern.MyMvp;

public class MvpActivity extends ActionBarActivity
		implements PatternManager.PatternCallbacks {

	public final static int PATTERN_ID = 0;

	private PatternManager mPatternManager;
	private MyMvp mMyMvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
		mPatternManager = PatternManager.newInstance(this);
		mMyMvp = (MyMvp) mPatternManager.initPattern(PATTERN_ID, null, this);
		LogEx.d(String.format("mMyMvp=%s", mMyMvp));
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pattern, menu);
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
		return new MyMvp();
	}
}
