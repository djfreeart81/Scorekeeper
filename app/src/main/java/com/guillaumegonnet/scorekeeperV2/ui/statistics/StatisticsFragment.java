package com.guillaumegonnet.scorekeeperV2.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guillaumegonnet.scorekeeperV2.R;
import com.guillaumegonnet.scorekeeperV2.db.ShotDb;

import java.util.List;

public class StatisticsFragment extends Fragment {

    private StatisticsViewModel mStatisticsViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mStatisticsViewModel = new ViewModelProvider(this).get(StatisticsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.statistics_recyclerview);
        final StatisticsAdapter adapter = new StatisticsAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        //final TextView textView = root.findViewById(R.id.text_slideshow);
        mStatisticsViewModel.getShots().observe(getViewLifecycleOwner(), new Observer<List<ShotDb>>() {
            @Override
            public void onChanged(@Nullable final List<ShotDb> shot) {
                // Update the cached copy of the shots in the adapter.
              adapter.setStatistics(shot);
            }
        });
        return root;
    }
}
