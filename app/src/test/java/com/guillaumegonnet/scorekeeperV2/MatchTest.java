package com.guillaumegonnet.scorekeeperV2;

import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Guillaume Gonnet on 20/05/20.
 */
public class MatchTest {


    public LinkedList<Integer> getBall(int team, int fault, int point) {
        LinkedList<Integer> score = new LinkedList<Integer>();
        score.add(team);
        score.add(fault);
        score.add(point);
        return score;
    }

    @Test
    public void RemainingRedColour() {

        Match match = new Match("snooker", "team1", "team2", 4);
        LinkedList<LinkedList<Integer>> scoreList = new LinkedList<LinkedList<Integer>>();

        scoreList.add(getBall(1, 0, 1));
        scoreList.add(getBall(1, 0, 5));
        scoreList.add(getBall(1, 0, 1));
        scoreList.add(getBall(2, 0, 1));
        scoreList.add(getBall(1, 1, 4));
        scoreList.add(getBall(1, 0, 1));
        scoreList.add(getBall(1, 0, 1));
        scoreList.add(getBall(1, 0, 5));
        scoreList.add(getBall(2, 0, 1));
        scoreList.add(getBall(2, 0, 7));
        scoreList.add(getBall(2, 0, 1));
        scoreList.add(getBall(2, 0, 1));
        scoreList.add(getBall(1, 0, 1));
        scoreList.add(getBall(1, 0, 5));
        scoreList.add(getBall(1, 0, 2));
        scoreList.add(getBall(1, 0, 3));
        scoreList.add(getBall(1, 0, 4));
        scoreList.add(getBall(1, 0, 5));
        scoreList.add(getBall(1, 0, 6));
        scoreList.add(getBall(1, 0, 7));


        int result = match.getRemainingPoints(scoreList);

        assertThat(result, is(equalTo(0)));
    }

}