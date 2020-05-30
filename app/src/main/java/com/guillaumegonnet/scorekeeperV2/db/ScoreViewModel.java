package com.guillaumegonnet.scorekeeperV2.db;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.LinkedList;

/**
 * Created by Guillaume Gonnet on 30/05/20.
 */
public class ScoreViewModel extends AndroidViewModel {

    private ScoreRepository mRepository;
    private LiveData<LinkedList<ShotDb>> mShots;

    public ScoreViewModel(Application application) {
        super(application);
        mRepository = new ScoreRepository(application);
        mShots = mRepository.getShots();
    }

    LiveData<LinkedList<ShotDb>> getShots() {
        return mShots;
    }

    public void insert(ShotDb shot) {
        mRepository.insert(shot);
    }
}
