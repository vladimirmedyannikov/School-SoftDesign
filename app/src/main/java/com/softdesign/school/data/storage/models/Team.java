package com.softdesign.school.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Vladimir on 27.02.2016.
 */
@Table(name = "Teams")
public class Team extends Model {
    @Column(name = "teamName")
    private String teamName;

    public Team() {
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public static List<Team> getAll(){
        return new Select()
                .from(Team.class)
                .orderBy("teamName ASC")
                .execute();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
