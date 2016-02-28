package com.softdesign.school.data.storage.models;

import android.graphics.drawable.Drawable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Vladimir on 13.02.2016.
 */
@Table(name = "Users")
public class ActiveUser extends Model{
    @Column(name = "firstName")
    private String mFirstName;

    @Column(name = "lastName")
    private String mLastName;

    @Column(name = "rate")
    private int mRait;

    @Column(name = "email")
    private String mEmail;

    @Column(name = "team")
    private Team team;

    private Drawable mImage;
    private String mVkLink;
    private String mGitLink;

    public ActiveUser() {
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public static List<ActiveUser> getAll(){
        return new Select()
                .from(ActiveUser.class)
                .orderBy("firstname ASC, lastname ASC")
                .execute();
    }

    public static List<ActiveUser> getUsersByTeam(Team team){
        return new Select()
                .from(ActiveUser.class)
                .where("team = ?", team)
                .orderBy("firstname ASC, lastname ASC")
                .execute();
    }

    public ActiveUser(Drawable image, String firstName, String lastName){
        this.mImage = image;
        this.mFirstName = firstName;
        this.mLastName = lastName;
    }

    public ActiveUser(String mFirstName, String mLastName, Team team) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.team = team;
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
