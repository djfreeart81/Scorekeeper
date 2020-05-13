package com.guillaumegonnet.scorekeeperV2;

/**
 * Created by Guillaume Gonnet on 09/05/20.
 */
public class Game extends Match {
    public int mScoreGameTeam1;
    public int mScoreGameTeam2;
    public int mRemainingPoints = 147;

    public Game(String sportName, String team1, String team2, int scoreMax) {
        super(sportName, team1, team2, scoreMax);
    }



}
