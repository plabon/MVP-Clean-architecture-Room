package com.jukti.dictionary.domain.interactors.words;

import com.jukti.dictionary.domain.interactors.UseCase;
import com.jukti.dictionary.domain.models.WordDomainModel;
import com.jukti.dictionary.domain.repository.WordRepository;

import java.util.List;

/**
 * Created by Pl@b0n on 11,August,2018
 */
public class GetWordListUseCase extends UseCase<GetWordListUseCase.GetWordListRequest, GetWordListUseCase.GetWordListResponse>{


    private WordRepository wordRepository;

    public GetWordListUseCase(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public void execute(final GetWordListRequest requestValues) {
        wordRepository.getAllWords(requestValues, new WordRepository.GetWordsCallback() {
            @Override
            public void onFetchWordsComplete(List<WordDomainModel> words) {
                GetWordListResponse response = new GetWordListResponse(words);
                getmCallback().onSuccess(response);
            }

            @Override
            public void onDataNotAvailable() {
                getmCallback().onError();
            }
        });
    }

    public static final class GetWordListRequest implements UseCase.RequestValues{
        private String searchKey;


        public GetWordListRequest(String searchKey) {
            this.searchKey = searchKey;
        }

        public String getSearchKey() {
            return searchKey;
        }
    }

    public final class GetWordListResponse implements UseCase.ResponseValue{

        private final List<WordDomainModel> mWords;

        public GetWordListResponse(List<WordDomainModel> words) {
            mWords = words;
        }

        public List<WordDomainModel> getWords() {
            return mWords;
        }

    }

}
