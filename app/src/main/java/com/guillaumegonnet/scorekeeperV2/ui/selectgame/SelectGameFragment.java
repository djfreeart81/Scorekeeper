package com.guillaumegonnet.scorekeeperV2.ui.selectgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.guillaumegonnet.scorekeeperV2.R;
import com.guillaumegonnet.scorekeeperV2.db.Game.GameDb;
import com.guillaumegonnet.scorekeeperV2.db.Match.MatchDb;
import com.guillaumegonnet.scorekeeperV2.ui.scores.ScoresFragment;

import static java.lang.Integer.valueOf;

public class SelectGameFragment extends Fragment {

    public static final String BUNDLE_KEY_TEAM_1 = "Team 1";
    public static final String BUNDLE_KEY_TEAM_2 = "Team 2";

    private SelectGameViewModel mViewModel;
    private EditText mTeam1View;
    private EditText mTeam2View;
    private String mTeam1;
    private String mTeam2;
    private int mRaceTo;
    private EditText mRaceToView;
    private String mBillardType;

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.guillaumegonnet.scorekeeper";

    private SelectGameViewModel mSelectGameViewModel;

    public static SelectGameFragment newInstance() {
        return new SelectGameFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.select_game_fragment, container, false);

        mTeam1View = root.findViewById(R.id.team1_edit);
        mTeam2View = root.findViewById(R.id.team2_edit);
        mRaceToView = root.findViewById(R.id.raceto_edit);

        mSelectGameViewModel = new ViewModelProvider(this).get(SelectGameViewModel.class);

        mPreferences = getActivity().getSharedPreferences(sharedPrefFile, getActivity().MODE_PRIVATE);

        // Set by default the last team names from Shared Preferences
        if (mPreferences != null) {
            String team1 = mPreferences.getString(BUNDLE_KEY_TEAM_1, getString(R.string.team_1));
            String team2 = mPreferences.getString(BUNDLE_KEY_TEAM_2, getString(R.string.team_2));
            if (team1 != null) {
                mTeam1View.setText(team1);
                mTeam2View.setText(team2);
            }
        }

        final Spinner spinner = root.findViewById(R.id.billard_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.billard_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Action on click of Play Button: save Team Name in Preference, reset scores
        ImageButton button = root.findViewById(R.id.play_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                mTeam1 = mTeam1View.getText().toString();
                mTeam2 = mTeam2View.getText().toString();
                mRaceTo = valueOf(mRaceToView.getText().toString());
                mBillardType = spinner.getSelectedItem().toString();

                MatchDb matchDb = new MatchDb(mTeam1, mTeam2, 0, 0);
                mSelectGameViewModel.insertMatch(matchDb);

                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putInt(ScoresFragment.STATE_SCORE_MATCH_1, 0);
                editor.putInt(ScoresFragment.STATE_SCORE_MATCH_2, 0);
                editor.putString(ScoresFragment.STATE_TEAM_1, mTeam1);
                editor.putString(ScoresFragment.STATE_TEAM_2, mTeam2);
                editor.putString(ScoresFragment.STATE_SCORE_BILLARD_TYPE, mBillardType);
                editor.putInt(ScoresFragment.STATE_RACETO, mRaceTo);
                editor.remove("scorelist");
                editor.apply();

                //TODO: remove execution from main thread by creating async function or using coroutine, or converting LiveData
                int matchId = mSelectGameViewModel.getOngoingMatchIdInteger();

                GameDb gameDb = new GameDb(0, 0, matchId);
                mSelectGameViewModel.insertGame(gameDb);

                Fragment scoresFragment = new ScoresFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, scoresFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SelectGameViewModel.class);
        // TODO: Use the ViewModel
    }

    public class SpinnerActivity extends Fragment implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mBillardType = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
