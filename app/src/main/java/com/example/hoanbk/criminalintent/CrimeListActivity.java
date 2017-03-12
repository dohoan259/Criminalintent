package com.example.hoanbk.criminalintent;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

/**
 * Created by hoanbk on 3/2/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity
        implements CrimeListFragment.Callbacks, CrimeFragment.Callbacks {

    private static final String TAG = "CrimeListActivity";

    @Override
    public Fragment createFragment() {

        Log.d(TAG, "createFragment()");

        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
//        return R.layout.activity_twopane;
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onCrimeUpdated(Crime crime) {
        FragmentManager fm = getSupportFragmentManager();
        CrimeListFragment listFragment = (CrimeListFragment) fm.findFragmentById(R.id.fragmentContainer);
        listFragment.updateUI();
    }

    @Override
    public void onCrimeSelected(Crime crime) {

        Log.d(TAG, "onCrimeSelected()");

        // nen tin theo detailFragmentContainer thay vi tim the ten layout
        if (findViewById(R.id.detailFragmentContainer) == null) {
            // Start an instance of CrimePagerActivity
            Intent i = new Intent(this, CrimeActivity.class);
            i.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
            startActivity(i);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment oldDetail = fm.findFragmentById(R.id.detailFragmentContainer);
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());
            if(oldDetail != null) {
                ft.remove(oldDetail);
            }
            ft.add(R.id.detailFragmentContainer, newDetail);
            ft.commit();
        }
    }
}
