package com.guillaumegonnet.scorekeeperV2;

/**
 * Created by Guillaume Gonnet on 09/05/20.
 */
public class Match {
    private String sportName;
    private String team1;
    private String team2;
    private int scoreMatchTeam1;
    private int scoreMatchTeam2;
    private int mRaceTo;

    public Match(String sportName, String team1, String team2, int mRaceTo) {
        this.sportName = sportName;
        this.team1 = team1;
        this.team2 = team2;
        this.mRaceTo = mRaceTo;
    }

    public String getNameTeam1() {
        return team1;
    }

    public String getNameTeam2() {
        return team2;
    }

    public int getScoreMatchTeam1() {
        return scoreMatchTeam1;
    }

    public int getScoreMatchTeam2() {
        return scoreMatchTeam2;
    }

    public int getRaceTo() {
        return mRaceTo;
    }


}
