package com.jukti.dictionary.data.local;

import android.support.annotation.NonNull;

import com.jukti.dictionary.data.AppExecutors;
import com.jukti.dictionary.data.converter.WordModelConverterImpl;
import com.jukti.dictionary.data.entity.WordEntity;
import com.jukti.dictionary.domain.interactors.words.AddFavoriteUseCase;
import com.jukti.dictionary.domain.interactors.words.GetWordListUseCase;
import com.jukti.dictionary.domain.models.WordDomainModel;
import com.jukti.dictionary.domain.repository.WordRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pl@b0n on 11,August,2018
 */
public class WordLocalDataSource {
    private static volatile WordLocalDataSource INSTANCE;

    private WordDao mWordDao;
    private AppExecutors mAppExecutors;

    // Prevent direct instantiation.
    private WordLocalDataSource(@NonNull AppExecutors appExecutors,
                                 @NonNull WordDao wordDao) {
        mAppExecutors = appExecutors;
        mWordDao = wordDao;
    }

    public static WordLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull WordDao wordDao) {
        if (INSTANCE == null) {
            synchronized (WordLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WordLocalDataSource(appExecutors, wordDao);
                }
            }
        }
        return INSTANCE;
    }

    public void addFav(final AddFavoriteUseCase.AddFavRequestValues requestValues, final WordRepository.AddFavCallBack callback){
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final int id = mWordDao.setFav(requestValues.getId(),requestValues.isFlag()?1:0);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(id>0){
                            callback.onFavAddComplete(requestValues.getId());
                        }else{
                            callback.onFavUpdateFailed();
                        }
                    }
                });

            }
        });
    }

    public void getFavWords(GetWordListUseCase.GetWordListRequest requestValues, final WordRepository.GetWordsCallback callback){
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<WordEntity> list = mWordDao.getAllFavWords();
                final List<WordDomainModel> wordList;
                wordList = new WordModelConverterImpl().modelListToDomainList(list);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(wordList.isEmpty() ){
                            callback.onDataNotAvailable();
                        }else{
                            callback.onFetchWordsComplete(wordList);
                        }
                    }
                });

            }
        });
    }

    public void getWords(GetWordListUseCase.GetWordListRequest requestValues, final WordRepository.GetWordsCallback callback){
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<WordEntity> list = mWordDao.getWords();
                final List<WordDomainModel> wordList;
                wordList = new WordModelConverterImpl().modelListToDomainList(list);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(wordList.isEmpty() ){
                            callback.onDataNotAvailable();
                        }else{
                            callback.onFetchWordsComplete(wordList);
                        }
                    }
                });

            }
        });
    }
}
