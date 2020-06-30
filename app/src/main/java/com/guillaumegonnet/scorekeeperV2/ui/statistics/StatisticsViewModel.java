package com.guillaumegonnet.scorekeeperV2.ui.statistics;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.guillaumegonnet.scorekeeperV2.db.ScoreRepository;
import com.guillaumegonnet.scorekeeperV2.db.Shot.ShotDb;

import java.util.List;

public class StatisticsViewModel extends AndroidViewModel {

    private ScoreRepository mRepository;
    private LiveData<List<ShotDb>> mShots;

    public StatisticsViewModel(Application application) {
        super(application);
        mRepository = new ScoreRepository(application);
        mShots = mRepository.getShots();

    }

    public LiveData<List<ShotDb>> getShots() {
        return mShots;
    }
}