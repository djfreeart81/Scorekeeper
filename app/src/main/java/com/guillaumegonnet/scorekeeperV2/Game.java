package com.guillaumegonnet.scorekeeperV2;

import java.util.LinkedList;

/**
 * Created by Guillaume Gonnet on 29/05/20.
 */
public class Game {
    private String sportName;
    private int scoreGameTeam1 = 0;
    private int scoreGameTeam2 = 0;
    private int mRemainingPoints = 99;

    public Game(String sportName) {
        this.sportName = sportName;
    }

    public int getScoreGame(int team, LinkedList<Shot> scoreList) {
        int scoreGame = 0;
        for (Shot shot : scoreList) {
            if (shot.getTeam() == team) {
                scoreGame += shot.getPoint();
            }
        }
        return scoreGame;
    }

    public int getRemainingPoints(LinkedList<Shot> scoreList) {
        int numberOfRedScored = 0;
        int valueOfColorAtEndScored = 0;

        //create a LinkedList with only ball points (no fault points)
        LinkedList<Integer> ballScoredList = new LinkedList<>();
        for (Shot shot : scoreList) {
            if (!shot.getFault()) {
                ballScoredList.add(shot.getPoint());
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
                    break;
                } else if (ballScoredList.get(i) == ballScoredList.get(i - 1) + 1) {
                    valueOfColorAtEndScored += ballScoredList.get(i);
                }
                i--;
            }
        }

        return mRemainingPoints - numberOfRedScored * 8 - valueOfColorAtEndScored;
    }
}
