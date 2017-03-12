package com.example.hoanbk.criminalintent;

import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity
implements CrimeFragment.Callbacks{
    @Override
    public Fragment createFragment() {
        UUID crimeId = (UUID)getIntent()
                .getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

    @Override
    public void onCrimeUpdated(Crime crime) {

    }
}
