package com.guillaumegonnet.scorekeeperV2.ui.selectgame;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.guillaumegonnet.scorekeeperV2.db.Game.GameDb;
import com.guillaumegonnet.scorekeeperV2.db.Match.MatchDb;
import com.guillaumegonnet.scorekeeperV2.db.ScoreRepository;

public class SelectGameViewModel extends AndroidViewModel {
    private ScoreRepository mRepository;
    private LiveData<GameDb> mGame;
    private LiveData<MatchDb> mMatch;
    private LiveData<Integer> mOngoingMatchId;
    private int mOngoingMatchIdInteger;

    public SelectGameViewModel(Application application) {
        super(application);
        mRepository = new ScoreRepository(application);
        mGame = mRepository.getOnGoingGame();
        mMatch = mRepository.getOnGoingMatch();
        mOngoingMatchId = mRepository.getOnGoingMatchId();
        mOngoingMatchIdInteger = mRepository.getOnGoingMatchIdInteger();
    }

    public void insertMatch(MatchDb match) {
        mRepository.insertMatch(match);
    }

    public void insertGame(GameDb game) {
        mRepository.insertGame(game);
    }

    public LiveData<MatchDb> getOngoingMatch() {
        return mMatch;
    }

    public LiveData<GameDb> getOngoingGame() {
        return mGame;
    }

    public LiveData<Integer> getOngoingMatchId() {
        return mOngoingMatchId;
    }

    public int getOngoingMatchIdInteger() {
        return mOngoingMatchIdInteger;
    }

}
