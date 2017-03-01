package com.example.hoanbk.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by hoanbk on 3/1/2017.
 */

public class CrimeLab {
    private ArrayList<Crime> mCrimes;
    private static CrimeLab sCrimeLab;
    private Context mAppContext;

    private CrimeLab(Context appContext){
        mAppContext = appContext;
        mCrimes = new ArrayList<>();
        for(int i=0; i < 100; i++){
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            mCrimes.add(c);
        }
    }

    public static CrimeLab getInstance(Context c){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime get(UUID id){
        for (Crime c : mCrimes) {
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
}
