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

import java.util.LinkedList;

public class ScoresFragment extends Fragment implements View.OnClickListener, MainActivity.ListenFromActivity {

    public static final String STATE_SCORELIST = "ScoreList";
    public static final String STATE_SCORE_MATCH_1 = "Team 1 Match Score";
    public static final String STATE_SCORE_MATCH_2 = "Team 2 Match Score";
    public static final String STATE_SCORE_BILLARD_TYPE = "Billard Type";
    public static final String STATE_TEAM_1 = "Team 1";
    public static final String STATE_TEAM_2 = "Team 2";
    public static final String STATE_RACETO = "Race To";

    private ScoresViewModel mScoresViewModel;
    private int mScoreMatch1;
    private int mScoreMatch2;
    private int mRaceTo;
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
    private TextView mRaceToText;
    private TextView mBallsScoredText;

    private Button mCancelBtn;
    private Match match;

    private LinkedList<LinkedList<Integer>> mScoreList = new LinkedList<LinkedList<Integer>>();

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
        mRaceToText = root.findViewById(R.id.raceto);
        mBallsScoredText = root.findViewById(R.id.balls_scored);

        mPreferences = getActivity().getSharedPreferences(sharedPrefFile, getActivity().MODE_PRIVATE);

        if (mPreferences != null) {
            mScoreMatch1 = mPreferences.getInt(STATE_SCORE_MATCH_1, 0);
            mScoreMatch2 = mPreferences.getInt(STATE_SCORE_MATCH_2, 0);
            mTeamName1 = mPreferences.getString(SelectGameFragment.BUNDLE_KEY_TEAM_1, getString(R.string.team_1));
            mTeamName2 = mPreferences.getString(SelectGameFragment.BUNDLE_KEY_TEAM_2, getString(R.string.team_2));
            mBillardType = mPreferences.getString(STATE_SCORE_BILLARD_TYPE, "Billard");
            mRaceTo = mPreferences.getInt(STATE_RACETO, 1);
        }

        match = new Match(mBillardType, mTeamName1, mTeamName2, mRaceTo);

        mScoreMatchText1.setText(String.valueOf(mScoreMatch1));
        mScoreMatchText2.setText(String.valueOf(mScoreMatch2));
        mScoreGameText1.setText(String.valueOf(match.getScoreGame(1, mScoreList)));
        mScoreGameText2.setText(String.valueOf(match.getScoreGame(2, mScoreList)));
        mTeamNameText1.setText(mTeamName1);
        mTeamNameText2.setText(mTeamName2);
        mRemainingPointsText.setText(getString(R.string.remaining_points, match.getRemainingPoints(mScoreList)));
        mRaceToText.setText(getString(R.string.race_to, mRaceTo));

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
                changeScore(1, 1, 1);
                break;
            case R.id.faultTeam1By2:
                changeScore(1, 1, 2);
                break;
            case R.id.faultTeam1By3:
                changeScore(1, 1, 3);
                break;
            case R.id.faultTeam1By4:
                changeScore(1, 1, 4);
                break;
            case R.id.faultTeam1By5:
                changeScore(1, 1, 5);
                break;
            case R.id.faultTeam1By6:
                changeScore(1, 1, 6);
                break;
            case R.id.faultTeam1By7:
                changeScore(1, 1, 7);
                break;
            case R.id.faultTeam2By1:
                changeScore(2, 1, 1);
                break;
            case R.id.faultTeam2By2:
                changeScore(2, 1, 2);
                break;
            case R.id.faultTeam2By3:
                changeScore(2, 1, 3);
                break;
            case R.id.faultTeam2By4:
                changeScore(2, 1, 4);
                break;
            case R.id.faultTeam2By5:
                changeScore(2, 1, 5);
                break;
            case R.id.faultTeam2By6:
                changeScore(2, 1, 6);
                break;
            case R.id.faultTeam2By7:
                changeScore(2, 1, 7);
                break;
            case R.id.increaseTeam1By1:
                changeScore(1, 0, 1);
                break;
            case R.id.increaseTeam1By2:
                changeScore(1, 0, 2);
                break;
            case R.id.increaseTeam1By3:
                changeScore(1, 0, 3);
                break;
            case R.id.increaseTeam1By4:
                changeScore(1, 0, 4);
                break;
            case R.id.increaseTeam1By5:
                changeScore(1, 0, 5);
                break;
            case R.id.increaseTeam1By6:
                changeScore(1, 0, 6);
                break;
            case R.id.increaseTeam1By7:
                changeScore(1, 0, 7);
                break;
            case R.id.increaseTeam2By1:
                changeScore(2, 0, 1);
                break;
            case R.id.increaseTeam2By2:
                changeScore(2, 0, 2);
                break;
            case R.id.increaseTeam2By3:
                changeScore(2, 0, 3);
                break;
            case R.id.increaseTeam2By4:
                changeScore(2, 0, 4);
                break;
            case R.id.increaseTeam2By5:
                changeScore(2, 0, 5);
                break;
            case R.id.increaseTeam2By6:
                changeScore(2, 0, 6);
                break;
            case R.id.increaseTeam2By7:
                changeScore(2, 0, 7);
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

        int scoreGame1 = match.getScoreGame(1, mScoreList);
        int scoreGame2 = match.getScoreGame(2, mScoreList);

        if (scoreGame1 > scoreGame2) {
            Toast.makeText(getContext(), mTeamName1 + " won the game", Toast.LENGTH_SHORT).show();
            mScoreMatch1++;
            mScoreMatchText1.setText(String.valueOf(mScoreMatch1));
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putInt(STATE_SCORE_MATCH_1, mScoreMatch1);
            editor.apply();
        } else if (scoreGame2 > scoreGame1) {
            Toast.makeText(getContext(), mTeamName2 + " won the game", Toast.LENGTH_SHORT).show();
            mScoreMatch2++;
            mScoreMatchText2.setText(String.valueOf(mScoreMatch2));
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putInt(STATE_SCORE_MATCH_2, mScoreMatch2);
            editor.apply();
        }

        if (mScoreMatch1 == mRaceTo || mScoreMatch2 == mRaceTo) {
            endMatch();
        }

        mScoreList.clear();
        mScoreGameText1.setText(String.valueOf(match.getScoreGame(1, mScoreList)));
        mScoreGameText2.setText(String.valueOf(match.getScoreGame(2, mScoreList)));
        mRemainingPointsText.setText(getString(R.string.remaining_points, match.getRemainingPoints(mScoreList)));
        mCancelBtn.setEnabled(false);
        savePreferences();
    }

    private void endMatch() {
        if (mScoreMatch1 > mScoreMatch2) {
            Toast.makeText(getContext(), getString(R.string.winning_match, mTeamName1), Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getContext(), getString(R.string.winning_match, mTeamName2), Toast.LENGTH_LONG).show();
        }
        EndMatchDialogFragment dialog = new EndMatchDialogFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "EndMatchDialogFragment");
    }

    // Methods executed on Listener defined in Main Activity for EndMatchDialogFragment
    @Override
    public void startNewMatch() {

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(STATE_SCORE_MATCH_1, 0);
        editor.putInt(STATE_SCORE_MATCH_2, 0);
        editor.apply();
        getActivity().getSupportFragmentManager().beginTransaction()
                .detach(ScoresFragment.this)
                .attach(ScoresFragment.this)
                .commit();
    }

    @Override
    public void goHome() {

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(STATE_SCORE_MATCH_2, mScoreMatch2);
        editor.apply();

        getActivity().getSupportFragmentManager().beginTransaction()
                .remove(this)
                .commit();
    }

    public void cancelLastAction(View view) {
        mScoreList.removeLast();
        match.getRemainingPoints(mScoreList);
        mScoreGameText1.setText(String.valueOf(match.getScoreGame(1, mScoreList)));
        mScoreGameText2.setText(String.valueOf(match.getScoreGame(2, mScoreList)));

        if (mScoreList.size() == 0) {
            mCancelBtn.setEnabled(false);
        }

        mBallsScoredText.setText(getBallsScored(mScoreList));
        savePreferences();
    }

    public void changeScore(int team, int fault, int point) {

        //add ball scored in the List
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(team);
        linkedList.add(fault);
        linkedList.add(point);
        mScoreList.add(linkedList);

        mCancelBtn.setEnabled(true);

        switch (team) {
            case 1:
                mScoreGameText1.setText(String.valueOf(match.getScoreGame(1, mScoreList)));
                break;
            case 2:
                mScoreGameText2.setText(String.valueOf(match.getScoreGame(2, mScoreList)));
                break;
        }
        mRemainingPointsText.setText(getString(R.string.remaining_points, match.getRemainingPoints(mScoreList)));
        mBallsScoredText.setText(getBallsScored(mScoreList));
        savePreferences();
    }

    public String getBallsScored(LinkedList<LinkedList<Integer>> scoreList) {
        String resultStringTeam1 = "Team 1: ";
        String resultStringTeam2 = "Team 2: ";
        for (LinkedList<Integer> list : scoreList) {
            if (list.get(0) == 1) {
                if (list.get(1) == 1) {
                    resultStringTeam1 = resultStringTeam1 + list.get(2) + "F ";
                } else {
                    resultStringTeam1 = resultStringTeam1 + list.get(2) + " ";
                }
            } else {
                resultStringTeam2 = resultStringTeam2 + list.get(2) + " ";
            }
        }
        return resultStringTeam1 + "\n" + resultStringTeam2;
    }


    public void savePreferences() {
        SharedPreferences.Editor editor = mPreferences.edit();
        //    editor.putInt(STATE_SCORE_GAME_1, mScoreGame1);


        editor.commit();
    }
}