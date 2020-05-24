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

    public int getScoreGame(int team, LinkedList<LinkedList<Integer>> scoreList) {
        int score = 0;
        for (LinkedList<Integer> list : scoreList) {
            if (list.getFirst() == team) {
                score += list.getLast();
            }
        }
        return score;
    }

    public int getRemainingPoints(LinkedList<LinkedList<Integer>> scoreList) {
        int numberOfRedScored = 0;
        int valueOfColorAtEndScored = 0;

        //create a LinkedList with only ball points (no fault points)
        LinkedList<Integer> ballScoredList = new LinkedList<>();
        for (LinkedList<Integer> list : scoreList) {
            if (list.get(1) == 0) {
                ballScoredList.add(list.get(2));
            }
        }

        for (int ball : ballScoredList) {
            if (ball == 1) {
                numberOfRedScored++;
            }
        }
        int lastIndexofBall1 = ballScoredList.lastIndexOf(1);

        // count points for the last color balls as no red entered before
        if (numberOfRedScored == 9 && ballScoredList.size() > 9) {
            int i = ballScoredList.size() - 1;
            int ball = 0;

            while ((ballScoredList.get(i) == ballScoredList.get(i - 1) + 1
                    || (ballScoredList.get(i) == 2 && i == lastIndexofBall1 + 2))
                    && i > lastIndexofBall1) {
                if (ballScoredList.get(i) == 2 && ballScoredList.get(i - 1) > 1) { //1/2/2 case
                    valueOfColorAtEndScored += 2;
                } else if (ballScoredList.get(i) == ballScoredList.get(i - 1) + 1) {
                    valueOfColorAtEndScored += ballScoredList.get(i);
                }
                i--;
            }
        }

        return mRemainingPoints - numberOfRedScored * 8 - valueOfColorAtEndScored;
    }
}
