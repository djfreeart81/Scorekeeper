package com.guillaumegonnet.scorekeeperV2.db.Match;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by Guillaume Gonnet on 07/06/20.
 */
@Entity(tableName = "match_table")
public class MatchDb {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "nameT1")
    private String nameTeam1 = "Team 1";

    @ColumnInfo(name = "nameT2")
    private String nameTeam2 = "Team 2";

    @ColumnInfo(name = "pointMatchT1")
    private int pointMatchT1 = 0;

    @ColumnInfo(name = "pointMatchT2")
    private int pointMatchT2 = 0;

    public MatchDb(String nameTeam1, String nameTeam2, int pointMatchT1, int pointMatchT2) {
        this.nameTeam1 = nameTeam1;
        this.nameTeam2 = nameTeam2;
        this.pointMatchT1 = pointMatchT1;
        this.pointMatchT2 = pointMatchT2;
    }

    @Ignore
    public MatchDb(int id, String nameTeam1, String nameTeam2, int pointMatchT1, int pointMatchT2) {
        this.id = id;
        this.nameTeam1 = nameTeam1;
        this.nameTeam2 = nameTeam2;
        this.pointMatchT1 = pointMatchT1;
        this.pointMatchT2 = pointMatchT2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTeam1() {
        return nameTeam1;
    }

    public void setNameTeam1(String nameTeam1) {
        this.nameTeam1 = nameTeam1;
    }

    public String getNameTeam2() {
        return nameTeam2;
    }

    public void setNameTeam2(String nameTeam2) {
        this.nameTeam2 = nameTeam2;
    }

    public int getPointMatchT1() {
        return pointMatchT1;
    }

    public void setPointMatchT1(int pointMatchT1) {
        this.pointMatchT1 = pointMatchT1;
    }

    public int getPointMatchT2() {
        return pointMatchT2;
    }

    public void setPointMatchT2(int pointMatchT2) {
        this.pointMatchT2 = pointMatchT2;
    }
}