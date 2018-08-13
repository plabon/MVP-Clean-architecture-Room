package com.jukti.juktidictionary;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jukti.dictionary.data.AppExecutors;
import com.jukti.dictionary.data.WordRepositoryImp;
import com.jukti.dictionary.data.local.DictionaryDatabase;
import com.jukti.dictionary.data.local.WordLocalDataSource;
import com.jukti.dictionary.data.remote.WordRemoteDataSource;
import com.jukti.dictionary.domain.interactors.words.AddFavoriteUseCase;
import com.jukti.dictionary.domain.interactors.words.GetWordListUseCase;
import com.jukti.dictionary.domain.repository.WordRepository;

/**
 * Created by Pl@b0n on 12,August,2018
 */
public class Injection {

    public static GetWordListUseCase provideGetWords(@NonNull Context context) {
        return new GetWordListUseCase(provideWordsRepository(context));
    }
    public static AddFavoriteUseCase provideAddFavUseCase(@NonNull Context context) {
        return new AddFavoriteUseCase(provideWordsRepository(context));
    }
    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    public static WordRepository provideWordsRepository(Context context) {
        DictionaryDatabase database = DictionaryDatabase.getInstance(context);
        return WordRepositoryImp.getInstance(WordLocalDataSource.getInstance(new AppExecutors(), database.wordDao()), new WordRemoteDataSource());
    }
}
