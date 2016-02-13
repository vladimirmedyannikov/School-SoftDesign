package com.softdesign.school.data.storage.models;

import android.graphics.drawable.Drawable;

/**
 * Created by Vladimir on 13.02.2016.
 */
public class User {
    private String mFirstName;
    private String mLastName;
    private int mRait;
    private String mEmail;

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    private Drawable mImage;
    private String mVkLink;
    private String mGitLink;

    public User(Drawable image, String firstName, String lastName){
        this.mImage = image;
        this.mFirstName = firstName;
        this.mLastName = lastName;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public int getmRait() {
        return mRait;
    }

    public void setmRait(int mRait) {
        this.mRait = mRait;
    }

    public Drawable getmImage() {
        return mImage;
    }

    public void setmImage(Drawable mImage) {
        this.mImage = mImage;
    }

    public String getmVkLink() {
        return mVkLink;
    }

    public void setmVkLink(String mVkLink) {
        this.mVkLink = mVkLink;
    }

    public String getmGitLink() {
        return mGitLink;
    }

    public void setmGitLink(String mGitLink) {
        this.mGitLink = mGitLink;
    }

    public String getFullName() {
        return mFirstName + " " + mLastName;
    }
}
