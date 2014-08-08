package com.slaterama.quantumsheep;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.slaterama.qslib.alpha.app.architecture.Architecture;
import com.slaterama.qslib.alpha.app.architecture.ArchitectureMVP;
import com.slaterama.qslib.alpha.app.architecture.ArchitectureManager;
import com.slaterama.qslib.utils.LogEx;

public class MainActivity extends FragmentActivity {

	private final static int ARCHITECTURE_ID = 0;

	private Architecture mArchitecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		LogEx.d("This is a test");

		ArchitectureManager architectureManager = ArchitectureManager.getInstance();
		mArchitecture = architectureManager.getArchitecture(this, ARCHITECTURE_ID, null, mArchitectureCallbacks);
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

	private ArchitectureManager.ArchitectureCallbacks mArchitectureCallbacks = new ArchitectureManager.ArchitectureCallbacks() {
		@Override
		public Architecture onCreateArchitecture(int id, Bundle args) {
			return new ArchitectureMVP();
		}
	};
}
