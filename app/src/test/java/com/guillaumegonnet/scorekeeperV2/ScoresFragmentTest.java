package com.guillaumegonnet.scorekeeperV2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Guillaume Gonnet on 20/05/20.
 */

@RunWith(JUnit4.class)

public class ScoresFragmentTest {
    int previousBallScored = 0;
    int mRemainingPoints = 99;
    int point = 0;

    int result = 0;



    @Test
    public void RemainingRedColour() {
        int[] scoredBalls = {1, 2, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 2, 1, 3, 2, 3, 4, 5, 6, 7};

        for (int i = 0; i < scoredBalls.length; i++) {
            previousBallScored = i == 0 ? 0 : scoredBalls[i - 1];
            result = calculateRemainingPoints(scoredBalls[i], previousBallScored);
        }
        assertThat(result, is(equalTo(0)));
    }

    @Test
    public void RemainingRedAll() {
        int[] scoredBalls = {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7};

        for (int i = 0; i < scoredBalls.length; i++) {
            previousBallScored = i == 0 ? 0 : scoredBalls[i - 1];
            result = calculateRemainingPoints(scoredBalls[i], previousBallScored);
        }
        assertThat(result, is(equalTo(0)));
    }

    @Test
    public void RemainingRedRedColour() {
        int[] scoredBalls = {1, 2, 1, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 2, 1, 3, 2, 3, 4, 5, 6, 7};

        for (int i = 0; i < scoredBalls.length; i++) {
            previousBallScored = i == 0 ? 0 : scoredBalls[i - 1];
            result = calculateRemainingPoints(scoredBalls[i], previousBallScored);
        }
        assertThat(result, is(equalTo(0)));
    }


    public int calculateRemainingPoints(int point, int previousBallScored) {

        if (point == 1 || point == -1) {
            if (previousBallScored == 1) {
                mRemainingPoints -= 7 * Integer.signum(point); //reflect the color ball missed during previous turn
                mRemainingPoints -= 1 * Integer.signum(point);
            } else {
                mRemainingPoints -= 1 * Integer.signum(point);
            }
        } else {
            if (previousBallScored != 1) {
                mRemainingPoints -= point;
            } else {
                mRemainingPoints -= 7 * Integer.signum(point);
            }
        }

        return mRemainingPoints;
    }

}