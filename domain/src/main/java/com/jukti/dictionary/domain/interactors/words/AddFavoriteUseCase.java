package com.jukti.dictionary.domain.interactors.words;

import com.jukti.dictionary.domain.interactors.UseCase;
import com.jukti.dictionary.domain.repository.WordRepository;

/**
 * Created by Pl@b0n on 14,August,2018
 */
public class AddFavoriteUseCase extends UseCase<AddFavoriteUseCase.AddFavRequestValues,AddFavoriteUseCase.AddFavResponse>{

    private WordRepository wordRepository;

    public AddFavoriteUseCase(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public void execute(final AddFavoriteUseCase.AddFavRequestValues requestValues) {
        wordRepository.addFav(requestValues, new WordRepository.AddFavCallBack() {

            @Override
            public void onFavAddComplete(int id) {
                getmCallback().onSuccess(new AddFavResponse(id));
            }

            @Override
            public void onFavUpdateFailed() {
                getmCallback().onError();
            }
        });
    }

    public static final class AddFavRequestValues implements UseCase.RequestValues{
        private int id;
        private boolean flag;


        public AddFavRequestValues(int id, boolean flag) {
            this.id = id;
            this.flag = flag;
        }

        public int getId() {
            return id;
        }

        public boolean isFlag() {
            return flag;
        }
    }

    public final class AddFavResponse implements UseCase.ResponseValue{

        private int id;

        public AddFavResponse(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
