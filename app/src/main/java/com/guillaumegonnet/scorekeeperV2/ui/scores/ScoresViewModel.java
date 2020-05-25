package com.guillaumegonnet.scorekeeperV2.ui.scores;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoresViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ScoresViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is scores fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}