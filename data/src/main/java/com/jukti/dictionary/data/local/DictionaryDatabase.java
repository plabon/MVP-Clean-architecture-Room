package com.jukti.dictionary.data.local;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.jukti.dictionary.data.assetHelper.AssetSQLiteOpenHelperFactory;
import com.jukti.dictionary.data.entity.WordEntity;

/**
 * Created by Pl@b0n on 12,August,2018
 */
@Database(entities = {WordEntity.class},version = 1, exportSchema = false)
public abstract class DictionaryDatabase extends RoomDatabase{
    public abstract WordDao wordDao();

    private static DictionaryDatabase instance;
    private static final Object sLock = new Object();
    public static DictionaryDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        DictionaryDatabase.class, "Bangla.db")
                        .openHelperFactory(new AssetSQLiteOpenHelperFactory())
                        .build();
            }
            return instance;
        }
    }
}
