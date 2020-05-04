package com.guillaumegonnet.scorekeeperV2.ui.scores;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.guillaumegonnet.scorekeeperV2.R;
import com.guillaumegonnet.scorekeeperV2.ui.selectgame.SelectGameFragment;

public class ScoresFragment extends Fragment implements View.OnClickListener {

    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";
    static final String STATE_SCORE_1_BEFORE = "Team 1 Score Before";
    static final String STATE_SCORE_2_BEFORE = "Team 2 Score Before";
    static final String STATE_NAME_1 = "Team 1 Name";
    static final String STATE_NAME_2 = "Team 2 Name";
    private ScoresViewModel mScoresViewModel;
    private int mScore1;
    private int mScore1Before; //store the last score
    private int mScore2;
    private int mScore2Before; //store the last score
    private String mTeamName1;
    private String mTeamName2;
    private TextView mScoreText1;
    private TextView mScoreText2;
    private TextView mTeamNameText1;
    private TextView mTeamNameText2;
    private Button mCancelBtn;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.guillaumegonnet.scorekeeper";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mScoresViewModel =
                ViewModelProviders.of(this).get(ScoresViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scores, container, false);

        mScoreText1 = root.findViewById(R.id.score1);
        mScoreText2 = root.findViewById(R.id.score2);
        mTeamNameText1 = root.findViewById(R.id.team1);
        mTeamNameText2 = root.findViewById(R.id.team2);
        mCancelBtn = root.findViewById(R.id.cancel_btn);

        mPreferences = getActivity().getSharedPreferences(sharedPrefFile, getActivity().MODE_PRIVATE);


        if (mPreferences != null) {
            mScore1 = mPreferences.getInt(STATE_SCORE_1, 0);
            mScore2 = mPreferences.getInt(STATE_SCORE_2, 0);
            mScore1Before = mPreferences.getInt(STATE_SCORE_1_BEFORE, 0);
            mScore2Before = mPreferences.getInt(STATE_SCORE_2_BEFORE, 0);
            mTeamName1 = mPreferences.getString(SelectGameFragment.BUNDLE_KEY_TEAM_1, getString(R.string.team_1));
            mTeamName2 = mPreferences.getString(SelectGameFragment.BUNDLE_KEY_TEAM_2, getString(R.string.team_2));

            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
            mTeamNameText1.setText(mTeamName1);
            mTeamNameText2.setText(mTeamName2);
        }

        // Create listeners for - buttons
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 3; j++) {
                String buttonId = "decreaseTeam" + j + "By" + i;
                int resId = getResources().getIdentifier(buttonId, "id", getActivity().getPackageName());
                root.findViewById(resId).setOnClickListener(this);
            }
        }
        // Create listeners for + buttons
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 3; j++) {
                String buttonId = "increaseTeam" + j + "By" + i;
                int resId = getResources().getIdentifier(buttonId, "id", getActivity().getPackageName());
                root.findViewById(resId).setOnClickListener(this);
            }
        }
        root.findViewById(R.id.reset_btn).setOnClickListener(this);
        root.findViewById(R.id.cancel_btn).setOnClickListener(this);

        /*final TextView textView = root.findViewById(R.id.text_scores);
        mScoresViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        return root;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        switch (viewId) {
            case R.id.decreaseTeam1By1:
                changeScore(1, -1);
                break;
            case R.id.decreaseTeam1By2:
                changeScore(1, -2);
                break;
            case R.id.decreaseTeam1By3:
                changeScore(1, -3);
                break;
            case R.id.decreaseTeam1By4:
                changeScore(1, -4);
                break;
            case R.id.decreaseTeam1By5:
                changeScore(1, -5);
                break;
            case R.id.decreaseTeam1By6:
                changeScore(1, -6);
                break;
            case R.id.decreaseTeam1By7:
                changeScore(1, -7);
                break;
            case R.id.decreaseTeam2By1:
                changeScore(2, -1);
                break;
            case R.id.decreaseTeam2By2:
                changeScore(2, -2);
                break;
            case R.id.decreaseTeam2By3:
                changeScore(2, -3);
                break;
            case R.id.decreaseTeam2By4:
                changeScore(2, -4);
                break;
            case R.id.decreaseTeam2By5:
                changeScore(2, -5);
                break;
            case R.id.decreaseTeam2By6:
                changeScore(2, -6);
                break;
            case R.id.decreaseTeam2By7:
                changeScore(2, -7);
                break;
            case R.id.increaseTeam1By1:
                changeScore(1, 1);
                break;
            case R.id.increaseTeam1By2:
                changeScore(1, 2);
                break;
            case R.id.increaseTeam1By3:
                changeScore(1, 3);
                break;
            case R.id.increaseTeam1By4:
                changeScore(1, 4);
                break;
            case R.id.increaseTeam1By5:
                changeScore(1, 5);
                break;
            case R.id.increaseTeam1By6:
                changeScore(1, 6);
                break;
            case R.id.increaseTeam1By7:
                changeScore(1, 7);
                break;
            case R.id.increaseTeam2By1:
                changeScore(2, 1);
                break;
            case R.id.increaseTeam2By2:
                changeScore(2, 2);
                break;
            case R.id.increaseTeam2By3:
                changeScore(2, 3);
                break;
            case R.id.increaseTeam2By4:
                changeScore(2, 4);
                break;
            case R.id.increaseTeam2By5:
                changeScore(2, 5);
                break;
            case R.id.increaseTeam2By6:
                changeScore(2, 6);
                break;
            case R.id.increaseTeam2By7:
                changeScore(2, 7);
                break;
            case R.id.cancel_btn:
                cancelLastAction(v);
                break;
            case R.id.reset_btn:
                resetScores(v);
                break;
        }
    }

    public void resetScores(View view) {
        mScore1 = 0;
        mScoreText1.setText(String.valueOf(mScore1));
        mScore2 = 0;
        mScoreText2.setText(String.valueOf(mScore2));

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void cancelLastAction(View view) {
        mScore2 = mScore2Before;
        mScore1 = mScore1Before;
        mScoreText1.setText(String.valueOf(mScore1));
        mScoreText2.setText(String.valueOf(mScore2));
        mCancelBtn.setEnabled(false);
    }

    public void changeScore(int team, int score) {
        mScore1Before = mScore1;
        mScore2Before = mScore2;
        mCancelBtn.setEnabled(true);

        switch (team) {
            case 1:
                mScore1 += score;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case 2:
                mScore2 += score;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(STATE_SCORE_1, mScore1);
        editor.putInt(STATE_SCORE_2, mScore2);
        editor.putInt(STATE_SCORE_1_BEFORE, mScore1Before);
        editor.putInt(STATE_SCORE_2_BEFORE, mScore2Before);
        editor.apply();
    }
}
