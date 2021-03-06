package com.example.hoanbk.criminalintent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by hoanbk on 3/1/2017.
 */

public class Crime {

    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";
    private static final String JSON_PHOTO = "photo";
    private static final String JSON_SUSPECT = "suspect";

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mIsSolved;
    private Photo mPhoto;
    private String mSuspect;

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

    public Photo getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Photo photo){
        mPhoto = photo;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public Crime(JSONObject json)
    throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mTitle = json.getString(JSON_TITLE);
        mIsSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATE));
        if(json.has(JSON_PHOTO)) {
            mPhoto = new Photo(json.getJSONObject(JSON_PHOTO));
        }
        if(json.has(JSON_SUSPECT)){
            mSuspect = json.getString(JSON_SUSPECT);
        }
    }

    public JSONObject toJSON()
            throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_SOLVED, mIsSolved);
        json.put(JSON_DATE, mDate.getTime());
        if(mPhoto != null){
            json.put(JSON_PHOTO, mPhoto.toJSON());
        }
        json.put(JSON_SUSPECT, mSuspect);
        return json;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
