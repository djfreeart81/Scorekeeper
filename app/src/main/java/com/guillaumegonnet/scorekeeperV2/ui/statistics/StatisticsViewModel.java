package com.guillaumegonnet.scorekeeperV2.ui.statistics;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.guillaumegonnet.scorekeeperV2.db.ScoreRepository;
import com.guillaumegonnet.scorekeeperV2.db.ShotDb;

import java.util.List;

public class StatisticsViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
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
    public void insert(ShotDb shot) {mRepository.insert(shot);}
}