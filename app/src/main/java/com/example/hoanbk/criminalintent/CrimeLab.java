package com.example.hoanbk.criminalintent;

import android.content.Context;
import android.widget.Toast;

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
        for(Crime crime : mCrimes) {
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }

    public void addCrime(Crime c){
        mCrimes.add(c);
    }
}
