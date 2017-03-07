package com.example.hoanbk.criminalintent;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hoanbk on 3/1/2017.
 */

public class CrimeListFragment extends ListFragment {

    private static final String TAG = "CrimeListFragment";

    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);    // noi cho fragment can nhan callback cho optionMenu
        getActivity().setTitle(R.string.crimes_title);


        Log.d(TAG, "onCreate()");

        mCrimes = CrimeLab.getInstance(getActivity()).getCrimes();
        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_empty, container, false);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);
    }

    @TargetApi(11)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_crime: {
                Crime crime = new Crime();
                CrimeLab.getInstance(getActivity()).addCrime(crime);
                Intent i = new Intent(getActivity(), CrimeActivity.class);
                i.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
                startActivityForResult(i, 0);
                return true;
            }
//            case R.id.menu_item_show_subtitle: {
//                ActionBar actionBar = getActivity().getActionBar();
//                actionBar.setSubtitle(R.string.subtitle);
//                return true;
//            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
//        Intent i = new Intent(getActivity(), CrimeActivity.class);
        Intent i = new Intent(getActivity(), CrimePagerActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(i);
    }

    private class CrimeAdapter extends ArrayAdapter<Crime>{
        public CrimeAdapter(ArrayList<Crime> crimes){
            super(getActivity(), 0, crimes);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().
                inflate(R.layout.list_item_crime, null);
            }

            Log.d(TAG, "getView(): " + position);

            Crime c = getItem(position);
            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
            TextView dataTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_dataTextView);
            dataTextView.setText(c.getDate().toString());
            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());

            return convertView;
        }
    }
}
