package com.guillaumegonnet.scorekeeperV2.db.Game;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Created by Guillaume Gonnet on 07/06/20.
 */
@androidx.room.Dao
public interface GameDao {
    @Insert
    void insert(GameDb game);

    @Delete
    void delete(GameDb game);

    @Query("DELETE FROM game_table")
    void deleteAll();

    @Query("SELECT * FROM game_table")
    LiveData<List<GameDb>> getAllGames();

    @Query("SELECT * FROM game_table ORDER BY ID DESC LIMIT 1")
    LiveData<GameDb> getOngoingGame();

}