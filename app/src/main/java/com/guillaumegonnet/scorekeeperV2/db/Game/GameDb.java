package com.guillaumegonnet.scorekeeperV2.db.Game;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.guillaumegonnet.scorekeeperV2.db.Match.MatchDb;

/**
 * Created by Guillaume Gonnet on 07/06/20.
 */

@Entity(tableName = "game_table", foreignKeys = @ForeignKey(entity = MatchDb.class, parentColumns = "id", childColumns = "MatchId"))
public class GameDb {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "scoreT1")
    private int scoreGameTeam1 = 0;

    @ColumnInfo(name = "scoreT2")
    private int scoreGameTeam2 = 0;

    @ColumnInfo(name = "MatchId")
    private int matchId;

    public GameDb(int scoreGameTeam1, int scoreGameTeam2, int matchId) {
        this.scoreGameTeam1 = scoreGameTeam1;
        this.scoreGameTeam2 = scoreGameTeam2;
        this.matchId = matchId;
    }

    @Ignore
    public GameDb(int id, int scoreGameTeam1, int scoreGameTeam2, int matchId) {
        this.id = id;
        this.scoreGameTeam1 = scoreGameTeam1;
        this.scoreGameTeam2 = scoreGameTeam2;
        this.matchId = matchId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScoreGameTeam1() {
        return scoreGameTeam1;
    }

    public void setScoreGameTeam1(int scoreGameTeam1) {
        this.scoreGameTeam1 = scoreGameTeam1;
    }

    public int getScoreGameTeam2() {
        return scoreGameTeam2;
    }

    public void setScoreGameTeam2(int scoreGameTeam2) {
        this.scoreGameTeam2 = scoreGameTeam2;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
}
