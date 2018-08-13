package com.jukti.dictionary.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.jukti.dictionary.data.entity.WordEntity;

import java.util.List;

/**
 * Created by Pl@b0n on 11,August,2018
 */
@Dao
public interface WordDao {
    /**
     * Select all word from the Words table.
     *
     * @return all Word.
     */
    @Query("SELECT * FROM Dictionary")
    List<WordEntity> getWords();

    @Query("SELECT * FROM Dictionary WHERE IsFav=1")
    List<WordEntity> getAllFavWords();

    @Query("UPDATE Dictionary SET IsFav=:flag WHERE Id=:id")
    int setFav(int id, int flag);
}
