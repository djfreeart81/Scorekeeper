package com.guillaumegonnet.scorekeeperV2;

import java.util.LinkedList;

/**
 * Created by Guillaume Gonnet on 09/05/20.
 */
public class Match {
    public String sportName;
    public String team1;
    public String team2;
    public int scoreMatchTeam1;
    public int scoreMatchTeam2;
    public int mRaceTo;
    public boolean isMatchStarted;
    public int mRemainingPoints = 99;

    //mBallScoredList contains (team, fault, point)
    private LinkedList<LinkedList<Integer>> mBallScoredList = new LinkedList<LinkedList<Integer>>();


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

    public int getScoreGame(int team, LinkedList<LinkedList<Integer>> mBallScoredList) {
        int score = 0;
        for (LinkedList<Integer> list : mBallScoredList) {
            if (list.getFirst() == team) {
                score += list.getLast();
            }
        }
        return score;
    }

    public int getRemainingPoints(LinkedList<LinkedList<Integer>> mBallScoredList) {
        int numberOfRedScored = 0;
        for (LinkedList<Integer> list : mBallScoredList) {
            if (list.get(1) == 0 && list.get(2) == 1) {
                numberOfRedScored++;
            }
        }

        return mRemainingPoints;
    }
}
