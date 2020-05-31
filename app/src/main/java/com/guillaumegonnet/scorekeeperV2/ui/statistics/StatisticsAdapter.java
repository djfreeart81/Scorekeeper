package com.guillaumegonnet.scorekeeperV2.ui.statistics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guillaumegonnet.scorekeeperV2.R;
import com.guillaumegonnet.scorekeeperV2.db.ShotDb;

import java.util.List;

/**
 * Created by Guillaume Gonnet on 31/05/20.
 */
public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder> {


    class StatisticsViewHolder extends RecyclerView.ViewHolder {
        private final TextView shotItemView;

        private StatisticsViewHolder(View itemView) {
            super(itemView);
            shotItemView=itemView.findViewById(R.id.statistics_item_view);
        }
    }
    private final LayoutInflater mInflater;
    private List<ShotDb> mShot;

    StatisticsAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public StatisticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.shot_recyclerview_item,parent,false);
        return new StatisticsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsViewHolder holder, int position) {
        if(mShot != null) {
            ShotDb shot = mShot.get(position);
            holder.shotItemView.setText("Team : " + shot.getTeam() + "Fault : "+ shot.getFault() + "Point : " + shot.getPoint());
        } else {
            holder.shotItemView.setText("No statistics yet");
        }
    }

    void setStatistics(List<ShotDb> shots) {
        mShot = shots;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mShot!= null) {
            return mShot.size();
        } else {
            return 0;
        }
    }
}
