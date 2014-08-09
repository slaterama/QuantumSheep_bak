package com.slaterama.quantumsheep.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.slaterama.qslib.alpha.app.architecture.Architecture;
import com.slaterama.qslib.alpha.app.architecture.ArchitectureMVP;
import com.slaterama.qslib.alpha.app.architecture.AbsArchitectureManager.ArchitectureCallbacks;
import com.slaterama.qslib.alpha.app.architecture.SupportArchitectureManager;
import com.slaterama.quantumsheep.R;

public class MainActivity extends FragmentActivity
		implements ArchitectureCallbacks {

	private final static int ARCHITECTURE_ID = 0;

	private Architecture mArchitecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		mArchitecture = SupportArchitectureManager.getInstance().getArchitecture(
				this, ARCHITECTURE_ID, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
	public Architecture onCreateArchitecture(int id, Bundle args) {
		return new ArchitectureMVP();
	}
}
