package com.guillaumegonnet.scorekeeperV2.db.Match;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by Guillaume Gonnet on 07/06/20.
 */
@androidx.room.Dao
public interface MatchDao {
    @Insert
    void insert(MatchDb match);

    @Delete
    void delete(MatchDb match);

    @Update
    void update(MatchDb match);

    @Query("DELETE FROM match_table")
    void deleteAll();

    @Query("SELECT * FROM match_table")
    LiveData<List<MatchDb>> getAllMatches();

    @Query("SELECT * FROM match_table ORDER BY ID DESC LIMIT 1")
    LiveData<MatchDb> getOngoingMatch();

    @Query("SELECT ID FROM match_table ORDER BY ID DESC LIMIT 1")
    LiveData<Integer> getOngoingMatchId();

    @Query("SELECT ID FROM match_table ORDER BY ID DESC LIMIT 1")
    int getOngoingMatchIdInteger();

}