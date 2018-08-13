package com.jukti.juktidictionary.wordList;

import com.jukti.dictionary.domain.interactors.words.AddFavoriteUseCase;
import com.jukti.dictionary.domain.interactors.words.GetWordListUseCase;
import com.jukti.juktidictionary.UseCaseHandler;
import com.jukti.juktidictionary.mapper.DomainToViewModelMapperImp;

/**
 * Created by Pl@b0n on 09,August,2018
 */
public class WordListPresenter implements WordListContract.Presenter {

    private WordListContract.View view;
    private UseCaseHandler mUseCaseHandler;
    private GetWordListUseCase mGetWordListUseCase;
    private AddFavoriteUseCase mAddFavoriteUseCase;

    public WordListPresenter(UseCaseHandler useCaseHandler,
                             GetWordListUseCase getWordListUseCase,
                             AddFavoriteUseCase addFavoriteUseCase,
                             WordListContract.View wordListView) {
        mUseCaseHandler = useCaseHandler;
        mGetWordListUseCase = getWordListUseCase;
        mAddFavoriteUseCase = addFavoriteUseCase;
        view = wordListView;
    }



    @Override
    public void doSearch(String searchKey) {

    }

    @Override
    public void getAllWords() {
        view.showLoadingProgress(true);
        GetWordListUseCase.GetWordListRequest requestValues = new GetWordListUseCase.GetWordListRequest("");
        mUseCaseHandler.execute(mGetWordListUseCase,requestValues, new GetWordListUseCase.Callback<GetWordListUseCase.GetWordListResponse>() {
            @Override
            public void onSuccess(GetWordListUseCase.GetWordListResponse response) {
                view.showWords(new DomainToViewModelMapperImp().mapWordDomainListToWordViewModelList(response.getWords()));
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void addFav(int id, boolean flag) {
        AddFavoriteUseCase.AddFavRequestValues requestValues = new AddFavoriteUseCase.AddFavRequestValues(id,flag);
        mUseCaseHandler.execute(mAddFavoriteUseCase,requestValues,new AddFavoriteUseCase.Callback<AddFavoriteUseCase.AddFavResponse>(){

            @Override
            public void onSuccess(AddFavoriteUseCase.AddFavResponse returnObject) {
                view.notifyData(returnObject.getId());
            }

            @Override
            public void onError() {

            }
        });
    }


    @Override
    public void start() {

    }
}
