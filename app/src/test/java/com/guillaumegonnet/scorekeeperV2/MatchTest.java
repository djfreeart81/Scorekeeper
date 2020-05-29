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

    @Test
    public void getRemainingPointsTestCompleteBalls() {

        Match match = new Match("snooker", "team1", "team2", 4);
        LinkedList<Score> scoreList = new LinkedList<Score>();

        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(1, true, 4));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(2, false, 7));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(1, false, 2));
        scoreList.add(new Score(1, false, 3));
        scoreList.add(new Score(1, false, 4));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(1, false, 6));
        scoreList.add(new Score(1, false, 7));


        int result = match.getRemainingPoints(scoreList);

        assertThat(result, is(equalTo(0)));
    }

    @Test
    public void getRemainingPointsTestRedBallBeforeLast2() {

        Match match = new Match("snooker", "team1", "team2", 4);
        LinkedList<Score> scoreList = new LinkedList<Score>();

        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(1, true, 4));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(2, false, 7));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 2));
        scoreList.add(new Score(1, false, 3));
        scoreList.add(new Score(1, false, 4));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(1, false, 6));
        scoreList.add(new Score(1, false, 7));


        int result = match.getRemainingPoints(scoreList);

        assertThat(result, is(equalTo(0)));
    }

    @Test
    public void getRemainingPointsTest12234567() {

        Match match = new Match("snooker", "team1", "team2", 4);
        LinkedList<Score> scoreList = new LinkedList<Score>();

        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(1, true, 4));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(2, false, 7));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 2));
        scoreList.add(new Score(1, false, 2));
        scoreList.add(new Score(1, false, 3));
        scoreList.add(new Score(1, false, 4));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(1, false, 6));
        scoreList.add(new Score(1, false, 7));


        int result = match.getRemainingPoints(scoreList);

        assertThat(result, is(equalTo(0)));
    }

    @Test
    public void getRemainingPointsTestStopBeforeLastColorBalls() {

        Match match = new Match("snooker", "team1", "team2", 4);
        LinkedList<Score> scoreList = new LinkedList<Score>();

        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(1, true, 4));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(2, false, 7));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(1, false, 1));

        int result = match.getRemainingPoints(scoreList);

        assertThat(result, is(equalTo(27)));
    }

    @Test
    public void getRemainingPointsTestStopAfterLastColorBallBeforeStartingOrder() {

        Match match = new Match("snooker", "team1", "team2", 4);
        LinkedList<Score> scoreList = new LinkedList<Score>();

        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(1, true, 4));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 5));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(2, false, 7));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(2, false, 1));
        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 5));

        int result = match.getRemainingPoints(scoreList);

        assertThat(result, is(equalTo(27)));
    }

    @Test
    public void getRemainingPointsTestEmpty() {

        Match match = new Match("snooker", "team1", "team2", 4);
        LinkedList<Score> scoreList = new LinkedList<Score>();

        int result = match.getRemainingPoints(scoreList);

        assertThat(result, is(equalTo(99)));
    }

    @Test
    public void getRemainingPointsTest1fault() {

        Match match = new Match("snooker", "team1", "team2", 4);
        LinkedList<Score> scoreList = new LinkedList<Score>();

        scoreList.add(new Score(1, true, 5));

        int result = match.getRemainingPoints(scoreList);

        assertThat(result, is(equalTo(99)));
    }

    @Test
    public void getRemainingPointsTest2balls() {

        Match match = new Match("snooker", "team1", "team2", 4);
        LinkedList<Score> scoreList = new LinkedList<Score>();

        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 5));

        int result = match.getRemainingPoints(scoreList);

        assertThat(result, is(equalTo(91)));
    }

    @Test
    public void getScoreTest0() {
        Match match = new Match("snooker", "team1", "team2", 4);
        LinkedList<Score> scoreList = new LinkedList<Score>();

        int scoreTeam1 = match.getScoreGame(1, scoreList);
        int scoreTeam2 = match.getScoreGame(2, scoreList);

        assertThat(scoreTeam1, is(equalTo(0)));
        assertThat(scoreTeam2, is(equalTo(0)));
    }

    @Test
    public void getScoreTest1() {
        Match match = new Match("snooker", "team1", "team2", 4);
        LinkedList<Score> scoreList = new LinkedList<Score>();

        scoreList.add(new Score(1, false, 1));
        scoreList.add(new Score(1, false, 3));
        scoreList.add(new Score(1, true, 5));
        scoreList.add(new Score(2, true, 7));
        scoreList.add(new Score(2, false, 1));

        int scoreTeam1 = match.getScoreGame(1, scoreList);
        int scoreTeam2 = match.getScoreGame(2, scoreList);

        assertThat(scoreTeam1, is(equalTo(9)));
        assertThat(scoreTeam2, is(equalTo(8)));
    }

}