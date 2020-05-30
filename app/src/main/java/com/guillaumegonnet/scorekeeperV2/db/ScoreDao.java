package com.guillaumegonnet.scorekeeperV2.db;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.LinkedList;

/**
 * Created by Guillaume Gonnet on 30/05/20.
 */

@androidx.room.Dao
public interface ScoreDao {
    @Insert
    void insert(ShotDb shot);

    @Delete
    void delete(ShotDb Shot);

    @Query("SELECT * FROM shot_table WHERE fault='false'")
    LiveData<LinkedList<ShotDb>> getShots();

}
