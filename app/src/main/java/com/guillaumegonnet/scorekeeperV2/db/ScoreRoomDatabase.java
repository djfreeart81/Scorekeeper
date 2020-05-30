package com.guillaumegonnet.scorekeeperV2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Guillaume Gonnet on 30/05/20.
 */
@Database(entities = {ShotDb.class}, version = 1, exportSchema = false)
public abstract class ScoreRoomDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile ScoreRoomDatabase INSTANCE;

    static ScoreRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ScoreRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ScoreRoomDatabase.class, "score_database").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract ScoreDao scoreDao();
}
