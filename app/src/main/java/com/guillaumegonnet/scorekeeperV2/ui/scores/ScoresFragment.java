package com.guillaumegonnet.scorekeeperV2.ui.scores;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.guillaumegonnet.scorekeeperV2.R;

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
    private EditText mTeamNameText1;
    private EditText mTeamNameText2;
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
                mScore1--;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decreaseTeam1By2:
                mScore1 -= 2;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decreaseTeam1By3:
                mScore1 -= 3;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decreaseTeam1By4:
                mScore1 -= 4;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decreaseTeam1By5:
                mScore1 -= 5;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decreaseTeam1By6:
                mScore1 -= 6;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decreaseTeam1By7:
                mScore1 -= 7;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decreaseTeam2By1:
                mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            case R.id.decreaseTeam2By2:
                mScore2 -= 2;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            case R.id.decreaseTeam2By3:
                mScore2 -= 3;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            case R.id.decreaseTeam2By4:
                mScore2 -= 4;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            case R.id.decreaseTeam2By5:
                mScore2 -= 5;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            case R.id.decreaseTeam2By6:
                mScore2 -= 6;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            case R.id.decreaseTeam2By7:
                mScore2 -= 7;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            case R.id.increaseTeam1By1:
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.increaseTeam1By2:
                mScore1 += 2;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.increaseTeam1By3:
                mScore1 += 3;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.increaseTeam1By4:
                mScore1 += 4;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.increaseTeam1By5:
                mScore1 += 5;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.increaseTeam1By6:
                mScore1 += 6;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.increaseTeam1By7:
                mScore1 += 7;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.increaseTeam2By1:
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            case R.id.increaseTeam2By2:
                mScore2 += 2;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            case R.id.increaseTeam2By3:
                mScore2 += 3;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            case R.id.increaseTeam2By4:
                mScore2 += 4;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            case R.id.increaseTeam2By5:
                mScore2 += 5;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            case R.id.increaseTeam2By6:
                mScore2 += 6;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            case R.id.increaseTeam2By7:
                mScore2 += 7;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }
}
