package com.example.hoanbk.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by hoanbk on 3/2/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new CrimeListFragment();
    }
}
