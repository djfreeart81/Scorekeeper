package com.guillaumegonnet.scorekeeperV2.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.LinkedList;

/**
 * Created by Guillaume Gonnet on 30/05/20.
 */
class ScoreRepository {
    private ScoreDao mScoreDao;
    private LiveData<LinkedList<ShotDb>> mShots;

    ScoreRepository(Application application) {
        ScoreRoomDatabase db = ScoreRoomDatabase.getDatabase(application);
        mScoreDao = db.scoreDao();
        mShots = mScoreDao.getShots();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<LinkedList<ShotDb>> getShots() {
        return getShots();
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(ShotDb shot) {
        ScoreRoomDatabase.databaseWriteExecutor.execute(() -> {
            mScoreDao.insert(shot);
        });
    }
}
