package com.example.hoanbk.criminalintent;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by hoanbk on 3/2/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    private static final String TAG = "CrimeListActivity";

    @Override
    public Fragment createFragment() {

        Log.d(TAG, "createFragment()");

        return new CrimeListFragment();
    }
}
