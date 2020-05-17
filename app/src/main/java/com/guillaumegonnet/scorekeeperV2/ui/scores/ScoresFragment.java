package com.guillaumegonnet.scorekeeperV2.ui.scores;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.guillaumegonnet.scorekeeperV2.MainActivity;
import com.guillaumegonnet.scorekeeperV2.Match;
import com.guillaumegonnet.scorekeeperV2.R;
import com.guillaumegonnet.scorekeeperV2.ui.selectgame.SelectGameFragment;

import java.util.LinkedHashMap;

public class ScoresFragment extends Fragment implements View.OnClickListener, MainActivity.ListenFromActivity {

    public static final String BUNDLE_KEY_TEAM_1 = "Team 1";
    public static final String BUNDLE_KEY_TEAM_2 = "Team 2";
    public static final String BUNDLE_KEY_BILLARD = "Billard Type";
    public static final String BUNDLE_KEY_RACE_TO = "Race To";
    public static final String BUNDLE_KEY_INIT = "New Match";
    public static final String STATE_SCORE_GAME_1_BEFORE = "Team 1 Score Before";
    public static final String STATE_SCORE_GAME_2_BEFORE = "Team 2 Score Before";
    public static final String STATE_SCORE_GAME_1 = "Team 1 Game Score";
    public static final String STATE_SCORE_MATCH_1 = "Team 1 Match Score";
    public static final String STATE_SCORE_GAME_2 = "Team 2 Score";
    public static final String STATE_SCORE_MATCH_2 = "Team 2 Match Score";
    public static final String STATE_SCORE_BILLARD_TYPE = "Billard Type";
    public static final String STATE_TEAM_1 = "Team 1";
    public static final String STATE_TEAM_2 = "Team 2";
    public static final String STATE_REMAINING_POINTS = "Remaining Points";

    private ScoresViewModel mScoresViewModel;
    private int mScoreGame1;
    private int mScoreMatch1;
    private int mScoreGame1Before; //store the last score
    private int mScoreGame2;
    private int mScoreMatch2;
    private int mScoreGame2Before; //store the last score
    private int mRaceTo;
    private int mRemainingPoints = 99;
    private String mBillardType;
    private String mTeamName1;
    private String mTeamName2;
    private TextView mScoreMatchText1;
    private TextView mScoreGameText1;
    private TextView mScoreMatchText2;
    private TextView mScoreGameText2;
    private TextView mTeamNameText1;
    private TextView mTeamNameText2;
    private TextView mRemainingPointsText;
    private Button mCancelBtn;
    private Match match;
    private LinkedHashMap<Integer, Integer> mBallScoredMap = new LinkedHashMap<>(); //will store balls scored with K=Team# & V=Score

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.guillaumegonnet.scorekeeper";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).setActivityListener(ScoresFragment.this);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mScoresViewModel =
                ViewModelProviders.of(this).get(ScoresViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scores, container, false);

        mScoreMatchText1 = root.findViewById(R.id.scoreMatch1);
        mScoreGameText1 = root.findViewById(R.id.scoreGame1);
        mScoreMatchText2 = root.findViewById(R.id.scoreMatch2);
        mScoreGameText2 = root.findViewById(R.id.scoreGame2);
        mTeamNameText1 = root.findViewById(R.id.team1);
        mTeamNameText2 = root.findViewById(R.id.team2);
        mCancelBtn = root.findViewById(R.id.cancel_btn);
        mRemainingPointsText = root.findViewById(R.id.remaining_points);

        mPreferences = getActivity().getSharedPreferences(sharedPrefFile, getActivity().MODE_PRIVATE);


        // if (getArguments() != null && getArguments().containsKey(BUNDLE_KEY_INIT)) {
        //     mTeamName1 = getArguments().getString(BUNDLE_KEY_TEAM_1);
        //     mTeamName2 = getArguments().getString(BUNDLE_KEY_TEAM_2);
        //     mRaceTo = getArguments().getInt(BUNDLE_KEY_RACE_TO, 0);
        //     mBillardType = getArguments().getString(BUNDLE_KEY_BILLARD);
        //     getArguments().remove(BUNDLE_KEY_INIT);
        //
        // } else {

            if (mPreferences != null) {
                mScoreGame1 = mPreferences.getInt(STATE_SCORE_GAME_1, 0);
                mScoreGame2 = mPreferences.getInt(STATE_SCORE_GAME_2, 0);
                mScoreGame1Before = mPreferences.getInt(STATE_SCORE_GAME_1_BEFORE, 0);
                mScoreGame2Before = mPreferences.getInt(STATE_SCORE_GAME_2_BEFORE, 0);
                mScoreMatch1 = mPreferences.getInt(STATE_SCORE_MATCH_1, 0);
                mScoreMatch2 = mPreferences.getInt(STATE_SCORE_MATCH_2, 0);
                mTeamName1 = mPreferences.getString(SelectGameFragment.BUNDLE_KEY_TEAM_1, getString(R.string.team_1));
                mTeamName2 = mPreferences.getString(SelectGameFragment.BUNDLE_KEY_TEAM_2, getString(R.string.team_2));
                mBillardType = mPreferences.getString(STATE_SCORE_BILLARD_TYPE, "Billard");
                mRemainingPoints = mPreferences.getInt(STATE_REMAINING_POINTS, 99);
            }
        // }
        Match match = new Match(mBillardType, mTeamName1, mTeamName2, mRaceTo);

        mScoreMatchText1.setText(String.valueOf(mScoreMatch1));
        mScoreMatchText2.setText(String.valueOf(mScoreMatch2));
        mScoreGameText1.setText(String.valueOf(mScoreGame1));
        mScoreGameText2.setText(String.valueOf(mScoreGame2));
        mTeamNameText1.setText(mTeamName1);
        mTeamNameText2.setText(mTeamName2);
        mRemainingPointsText.setText(getString(R.string.remaining_points, mRemainingPoints));

        // Create listeners for - buttons
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 3; j++) {
                String buttonId = "faultTeam" + j + "By" + i;
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
        root.findViewById(R.id.endGame_btn).setOnClickListener(this);
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
            case R.id.faultTeam1By1:
                changeScoreFault(1, 1);
                break;
            case R.id.faultTeam1By2:
                changeScoreFault(1, 2);
                break;
            case R.id.faultTeam1By3:
                changeScoreFault(1, 3);
                break;
            case R.id.faultTeam1By4:
                changeScoreFault(1, 4);
                break;
            case R.id.faultTeam1By5:
                changeScoreFault(1, 5);
                break;
            case R.id.faultTeam1By6:
                changeScoreFault(1, 6);
                break;
            case R.id.faultTeam1By7:
                changeScoreFault(1, 7);
                break;
            case R.id.faultTeam2By1:
                changeScoreFault(2, 1);
                break;
            case R.id.faultTeam2By2:
                changeScoreFault(2, 2);
                break;
            case R.id.faultTeam2By3:
                changeScoreFault(2, 3);
                break;
            case R.id.faultTeam2By4:
                changeScoreFault(2, 4);
                break;
            case R.id.faultTeam2By5:
                changeScoreFault(2, 5);
                break;
            case R.id.faultTeam2By6:
                changeScoreFault(2, 6);
                break;
            case R.id.faultTeam2By7:
                changeScoreFault(2, 7);
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
            case R.id.endGame_btn:
                endGame(v);
                break;
        }
    }

    public void endGame(View view) {
        // EndGameDialogFragment dialog = new EndGameDialogFragment();
        // dialog.show(getParentFragmentManager(), "EndGameDialogFragment");
        if (mScoreGame1 > mScoreGame2) {
            Toast.makeText(getContext(), mTeamName1 + " won the game", Toast.LENGTH_SHORT).show();
            mScoreMatch1++;
            mScoreMatchText1.setText(String.valueOf(mScoreMatch1));
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putInt(STATE_SCORE_MATCH_1, mScoreMatch1);
            editor.apply();
        } else if (mScoreGame2 > mScoreGame1) {
            Toast.makeText(getContext(), mTeamName2 + " won the game", Toast.LENGTH_SHORT).show();
            mScoreMatch2++;
            mScoreMatchText2.setText(String.valueOf(mScoreMatch2));
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putInt(STATE_SCORE_MATCH_2, mScoreMatch2);
            editor.apply();
        }
        mScoreGame1 = 0;
        mScoreGame2 = 0;
        mScoreGame1Before = 0;
        mScoreGame2Before = 0;
        mScoreGameText1.setText(String.valueOf(mScoreGame1));
        mScoreGameText2.setText(String.valueOf(mScoreGame2));
        mCancelBtn.setEnabled(false);
        savePreferences();
    }

    // Methods executed on Listener defined in Main Activity for EndGameDialogFragment
    @Override
    public void IncreaseScoreMatch1() {
        mScoreMatch1++;
        mScoreMatchText1.setText(Integer.toString(mScoreMatch1));
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(STATE_SCORE_MATCH_1, mScoreMatch1);
        editor.apply();
    }

    @Override
    public void IncreaseScoreMatch2() {
        mScoreMatch2++;
        mScoreMatchText2.setText(Integer.toString(mScoreMatch2));
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(STATE_SCORE_MATCH_2, mScoreMatch2);
        editor.apply();
    }

    public void cancelLastAction(View view) {
        calculateRemainingPoints(-Math.max(mScoreGame2 - mScoreGame2Before, mScoreGame1 - mScoreGame1Before));
        mScoreGame2 = mScoreGame2Before;
        mScoreGame1 = mScoreGame1Before;
        mScoreGameText1.setText(String.valueOf(mScoreGame1));
        mScoreGameText2.setText(String.valueOf(mScoreGame2));
        mCancelBtn.setEnabled(false);
        savePreferences();
    }

    public void changeScore(int team, int point) {
        calculateRemainingPoints(point);
        //save current Score to be able to cancel
        mScoreGame1Before = mScoreGame1;
        mScoreGame2Before = mScoreGame2;
        mCancelBtn.setEnabled(true);

        switch (team) {
            case 1:
                mScoreGame1 += point;
                mScoreGameText1.setText(String.valueOf(mScoreGame1));
                break;
            case 2:
                mScoreGame2 += point;
                mScoreGameText2.setText(String.valueOf(mScoreGame2));
        }
        mBallScoredMap.put(team, point);
        savePreferences();
    }

    public void changeScoreFault(int team, int point) {
        mScoreGame1Before = mScoreGame1;
        mScoreGame2Before = mScoreGame2;
        mCancelBtn.setEnabled(true);

        switch (team) {
            case 1:
                mScoreGame2 += point;
                mScoreGameText2.setText(String.valueOf(mScoreGame2));
                break;
            case 2:
                mScoreGame1 += point;
                mScoreGameText1.setText(String.valueOf(mScoreGame1));
        }
        savePreferences();
    }

    private void calculateRemainingPoints(int point) {

        int previousBallScored = Math.max(mScoreGame1 - mScoreGame1Before, mScoreGame2 - mScoreGame2Before);

        if (point == 1 || point == -1) {
            if (previousBallScored == 1) {
                mRemainingPoints -= 7 * Integer.signum(point); //reflect the color ball missed during previous turn
                mRemainingPoints -= 1 * Integer.signum(point);
            } else {
                mRemainingPoints -= 1 * Integer.signum(point);
            }
        } else {
            mRemainingPoints -= 7 * Integer.signum(point);
        }

        mRemainingPointsText.setText(getString(R.string.remaining_points, mRemainingPoints));
        savePreferences();
    }

    public void savePreferences() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(STATE_SCORE_GAME_1, mScoreGame1);
        editor.putInt(STATE_SCORE_GAME_2, mScoreGame2);
        editor.putInt(STATE_SCORE_GAME_1_BEFORE, mScoreGame1Before);
        editor.putInt(STATE_SCORE_GAME_2_BEFORE, mScoreGame2Before);
        editor.putInt(STATE_REMAINING_POINTS, mRemainingPoints);
        editor.apply();
    }
}
