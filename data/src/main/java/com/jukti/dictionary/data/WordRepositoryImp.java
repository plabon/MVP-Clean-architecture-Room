package com.jukti.dictionary.data;

import com.jukti.dictionary.data.local.WordLocalDataSource;
import com.jukti.dictionary.data.remote.WordRemoteDataSource;
import com.jukti.dictionary.domain.interactors.words.AddFavoriteUseCase;
import com.jukti.dictionary.domain.interactors.words.GetWordListUseCase;
import com.jukti.dictionary.domain.repository.WordRepository;
import android.support.annotation.NonNull;

/**
 * Created by Pl@b0n on 11,August,2018
 */
public class WordRepositoryImp implements WordRepository {
    private final WordLocalDataSource wordLocalDataSource;
    private final WordRemoteDataSource wordRemoteDataSource;
    private static WordRepositoryImp instance;

    public WordRepositoryImp(@NonNull WordLocalDataSource localDataSource, @NonNull WordRemoteDataSource wordRemoteDataSource) {
        this.wordLocalDataSource = localDataSource;
        this.wordRemoteDataSource = wordRemoteDataSource;
    }

    @Override
    public void getAllWords(GetWordListUseCase.GetWordListRequest requestValues, GetWordsCallback callback) {
        wordLocalDataSource.getWords(requestValues,callback);
    }

    @Override
    public void getAllFavWords(GetWordListUseCase.GetWordListRequest requestValues, GetWordsCallback callback) {
        wordLocalDataSource.getWords(requestValues,callback);
    }

    @Override
    public void addFav(AddFavoriteUseCase.AddFavRequestValues requestValues, AddFavCallBack callback) {
        wordLocalDataSource.addFav(requestValues,callback);
    }




    public static WordRepositoryImp getInstance(WordLocalDataSource localDataSource,WordRemoteDataSource remoteDataSource){
        if(instance==null){
            instance = new WordRepositoryImp(localDataSource,remoteDataSource);
        }
        return instance;
    }
}
