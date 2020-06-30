package com.guillaumegonnet.scorekeeperV2.ui.scores;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.guillaumegonnet.scorekeeperV2.db.Game.GameDb;
import com.guillaumegonnet.scorekeeperV2.db.Match.MatchDb;
import com.guillaumegonnet.scorekeeperV2.db.ScoreRepository;
import com.guillaumegonnet.scorekeeperV2.db.Shot.ShotDb;

import java.util.List;

public class ScoresViewModel extends AndroidViewModel {

    private ScoreRepository mRepository;
    private LiveData<List<ShotDb>> mShots;
    private LiveData<GameDb> mGame;
    private LiveData<MatchDb> mMatch;
    private int mOngoingGameIdInteger;

    public ScoresViewModel(Application application) {
        super(application);
        mRepository = new ScoreRepository(application);
        mShots = mRepository.getShots();
        mGame = mRepository.getOnGoingGame();
        mMatch = mRepository.getOnGoingMatch();
        mOngoingGameIdInteger = mRepository.getOnGoingGameIdInteger();
    }

    public LiveData<List<ShotDb>> getShots() {
        return mShots;
    }

    public void insert(ShotDb shot) {
        mRepository.insertShot(shot);
    }

    public LiveData<MatchDb> getOngoingMatch() {
        return mMatch;
    }

    public LiveData<GameDb> getOngoingGame() {
        return mGame;
    }

    public int getOngoingGameIdInteger() {
        return mOngoingGameIdInteger;
    }
}