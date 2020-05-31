package com.guillaumegonnet.scorekeeperV2.ui.scores;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.guillaumegonnet.scorekeeperV2.db.ScoreRepository;
import com.guillaumegonnet.scorekeeperV2.db.ShotDb;

import java.util.List;

public class ScoresViewModel extends AndroidViewModel {

    private ScoreRepository mRepository;
    private LiveData<List<ShotDb>> mShots;

    public ScoresViewModel(Application application) {
        super(application);
        mRepository = new ScoreRepository(application);
        mShots = mRepository.getShots();

    }

    public LiveData<List<ShotDb>> getShots() {
        return mShots;
    }
    public void insert(ShotDb shot) {mRepository.insert(shot);}
}