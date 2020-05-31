package com.guillaumegonnet.scorekeeperV2.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by Guillaume Gonnet on 30/05/20.
 */

@Entity(tableName = "shot_table")
public class ShotDb {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "team")
    private int team;

    @ColumnInfo(name = "fault")
    private boolean fault;

    @ColumnInfo(name = "point")
    private int point;

    public ShotDb(int team, boolean fault, int point) {
        this.team = team;
        this.fault = fault;
        this.point = point;
    }

    @Ignore
    public ShotDb(int id, int team, boolean fault, int point) {
        this.id = id;
        this.team = team;
        this.fault = fault;
        this.point = point;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public int getTeam() {
        return this.team;
    }

    public boolean getFault() {
        return this.fault;
    }

    public int getPoint() {
        return this.point;
    }

}
