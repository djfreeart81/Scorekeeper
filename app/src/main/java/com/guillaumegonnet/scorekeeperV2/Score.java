package com.guillaumegonnet.scorekeeperV2;

/**
 * Created by Guillaume Gonnet on 29/05/20.
 */
public class Score {
    public int team;
    public boolean fault;
    public int point;

    public Score(int team, boolean fault, int point) {
        this.team = team;
        this.fault = fault;
        this.point = point;
    }
}