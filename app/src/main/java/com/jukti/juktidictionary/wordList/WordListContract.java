package com.jukti.juktidictionary.wordList;

import com.jukti.juktidictionary.base.BasePresenter;
import com.jukti.juktidictionary.base.BaseView;
import com.jukti.juktidictionary.models.WordModel;

import java.util.List;

/**
 * Created by Pl@b0n on 09,August,2018
 */
public interface WordListContract {

    public interface View extends BaseView<Presenter>{
        void showWords(List<WordModel> words);
        void showLoadingProgress(boolean isShow);
        void notifyData(int id);
        void showErrorMsg();
    }
    public interface Presenter extends BasePresenter{
        void doSearch(String searchKey);
        void getAllWords();
        void addFav(int id, boolean flag);
    }

}
