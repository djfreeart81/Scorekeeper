package com.guillaumegonnet.scorekeeperV2.db.Shot;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.guillaumegonnet.scorekeeperV2.db.Game.GameDb;

/**
 * Created by Guillaume Gonnet on 30/05/20.
 */

@Entity(tableName = "shot_table", foreignKeys = @ForeignKey(entity = GameDb.class, parentColumns = "id", childColumns = "gameId"))
public class ShotDb {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "team")
    private int team;

    @ColumnInfo(name = "fault")
    private boolean fault;

    @ColumnInfo(name = "point")
    private int point;

    @ColumnInfo(name = "gameId")
    private int gameId;

    public ShotDb(int team, boolean fault, int point, int gameId) {
        this.team = team;
        this.fault = fault;
        this.point = point;
        this.gameId = gameId;
    }

    @Ignore
    public ShotDb(int id, int team, boolean fault, int point, int gameId) {
        this.id = id;
        this.team = team;
        this.fault = fault;
        this.point = point;
        this.gameId = gameId;
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
