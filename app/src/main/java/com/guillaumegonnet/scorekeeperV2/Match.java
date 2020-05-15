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
    public int mScoreGameTeam1;
    public int mScoreGameTeam2;
    public int mRaceTo;
    public boolean isMatchStarted;
    public int mRemainingPoints = 147;

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
    public void setScoreMatchTeam1(int scoreMatchTeam1) {
        this.scoreMatchTeam1 = scoreMatchTeam1;
    }

    public int getScoreMatchTeam2() {
        return scoreMatchTeam2;
    }
    public void setScoreMatchTeam2(int scoreMatchTeam2) {
        this.scoreMatchTeam2 = scoreMatchTeam2;
    }

    public int getRaceTo() {
        return mRaceTo;
    }

    public void increaseScore(String team, int points) {
        if (team == team1) {
            mScoreGameTeam1 += points;
        }
        if (team == team2) {
            mScoreGameTeam2 += points;
        }

        if (points == 1 || points == -1) {
            mRemainingPoints -= points;
        } else {
            mRemainingPoints += points % Math.abs(points) * 7;
        }
    }
}
