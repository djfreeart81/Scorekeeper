package com.guillaumegonnet.scorekeeperV2;

/**
 * Created by Guillaume Gonnet on 29/05/20.
 */
public class Score {
    private int team;
    private boolean fault;
    private int point;

    public Score(int team, boolean fault, int point) {
        this.team = team;
        this.fault = fault;
        this.point = point;
    }

    public int getTeam() {
        return team;
    }

    public boolean getFault() {
        return fault;
    }

    public int getPoint() {
        return point;
    }

}