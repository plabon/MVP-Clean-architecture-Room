package com.jukti.dictionary.domain.repository;

import com.jukti.dictionary.domain.interactors.words.AddFavoriteUseCase;
import com.jukti.dictionary.domain.interactors.words.GetWordListUseCase;
import com.jukti.dictionary.domain.models.WordDomainModel;

import java.util.List;

/**
 * Created by Pl@b0n on 11,August,2018
 */
public interface WordRepository {

    interface GetWordsCallback {

        void onFetchWordsComplete(List<WordDomainModel> tasks);

        void onDataNotAvailable();
    }

    interface AddFavCallBack {
        void onFavAddComplete(int id);

        void onFavUpdateFailed();
    }

    void getAllWords(GetWordListUseCase.GetWordListRequest requestValues, GetWordsCallback callback);
    void getAllFavWords(GetWordListUseCase.GetWordListRequest requestValues, GetWordsCallback callback);
    void addFav(AddFavoriteUseCase.AddFavRequestValues requestValues, AddFavCallBack callback);
}
