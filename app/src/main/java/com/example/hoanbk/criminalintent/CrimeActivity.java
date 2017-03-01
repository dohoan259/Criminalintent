package com.example.hoanbk.criminalintent;

import android.support.v4.app.Fragment;
import android.os.Bundle;

public class CrimeActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new CrimeFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment);
    }
}
