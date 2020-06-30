package com.guillaumegonnet.scorekeeperV2.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.guillaumegonnet.scorekeeperV2.db.Game.GameDao;
import com.guillaumegonnet.scorekeeperV2.db.Game.GameDb;
import com.guillaumegonnet.scorekeeperV2.db.Match.MatchDao;
import com.guillaumegonnet.scorekeeperV2.db.Match.MatchDb;
import com.guillaumegonnet.scorekeeperV2.db.Shot.ShotDao;
import com.guillaumegonnet.scorekeeperV2.db.Shot.ShotDb;

import java.util.List;

/**
 * Created by Guillaume Gonnet on 30/05/20.
 */
public class ScoreRepository {
    private ShotDao mShotDao;
    private GameDao mGameDao;
    private MatchDao mMatchDao;

    private LiveData<List<ShotDb>> mShots;
    private LiveData<List<GameDb>> mGames;
    private LiveData<MatchDb> mOngoingMatch;
    private LiveData<GameDb> mOngoingGame;
    private LiveData<Integer> mOngoingMatchId;
    private int mOngoingMatchIdInteger;

    public ScoreRepository(Application application) {
        ScoreRoomDatabase db = ScoreRoomDatabase.getDatabase(application);
        mShotDao = db.shotDao();
        mGameDao = db.gameDao();
        mMatchDao = db.matchDao();
        mShots = mShotDao.getShots();
        mGames = mGameDao.getAllGames();
        mOngoingMatch = mMatchDao.getOngoingMatch();
        mOngoingGame = mGameDao.getOngoingGame();
        mOngoingMatchId = mMatchDao.getOngoingMatchId();
        mOngoingMatchIdInteger = mMatchDao.getOngoingMatchIdInteger();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<ShotDb>> getShots() {
        return mShots;
    }

    public LiveData<List<GameDb>> getGames() {
        return mGames;
    }

    public LiveData<MatchDb> getOnGoingMatch() {
        return mOngoingMatch;
    }

    public LiveData<GameDb> getOnGoingGame() {
        return mOngoingGame;
    }

    public LiveData<Integer> getOnGoingMatchId() {
        return mOngoingMatchId;
    }


    public int getOnGoingMatchIdInteger() {
        return mOngoingMatchIdInteger;
    }


    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertShot(ShotDb shot) {
        ScoreRoomDatabase.databaseWriteExecutor.execute(() -> {
            mShotDao.insert(shot);
        });
    }

    public void insertGame(GameDb game) {
        ScoreRoomDatabase.databaseWriteExecutor.execute(() -> {
            int matchId = mMatchDao.getOngoingMatchIdInteger();
            game.setMatchId(matchId);
            mGameDao.insert(game);
        });
    }

    public void insertMatch(MatchDb match) {
        ScoreRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMatchDao.insert(match);
        });
    }

    public void deleteAll() {
        ScoreRoomDatabase.databaseWriteExecutor.execute(() -> {
            mShotDao.deleteAll();
            mGameDao.deleteAll();
            mMatchDao.deleteAll();

        });
    }
}