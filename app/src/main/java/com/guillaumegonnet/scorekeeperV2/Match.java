package com.guillaumegonnet.scorekeeperV2;

/**
 * Created by Guillaume Gonnet on 09/05/20.
 */
public class Match {
    public String sportName;
    public String team1;
    public String team2;
    public int scoreMatchTeam1;
    public int scoreMatchTeam2;
    public int scoreMax;
    public boolean isMatchStarted;

    public Match(String sportName, String team1, String team2, int scoreMax) {
        this.sportName = sportName;
        this.team1 = team1;
        this.team2 = team2;
        this.scoreMax = scoreMax;
    }

    public int getScoreMatchTeam1() {
        return scoreMatchTeam1;
    }

    public void setScoreMatchTeam1(int scoreMatchTeam1) {
        this.scoreMatchTeam1 = scoreMatchTeam1;
    }

    public int getScoreMatchTeam2() {
        return scoreMatchTeam2;
    }

    public void setScoreMatchTeam2(int scoreMatchTeam2) {
        this.scoreMatchTeam2 = scoreMatchTeam2;
    }
}
