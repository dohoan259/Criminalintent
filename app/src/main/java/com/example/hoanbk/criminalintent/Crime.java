package com.example.hoanbk.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by hoanbk on 3/1/2017.
 */

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mIsSolved;

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mIsSolved;
    }

    public void setSolved(boolean solved) {
        mIsSolved = solved;
    }

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
