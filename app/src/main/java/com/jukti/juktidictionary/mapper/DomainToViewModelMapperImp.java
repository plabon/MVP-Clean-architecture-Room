package com.jukti.juktidictionary.mapper;

import com.jukti.dictionary.domain.models.WordDomainModel;
import com.jukti.juktidictionary.models.WordModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pl@b0n on 12,August,2018
 */
public class DomainToViewModelMapperImp implements DomainToViewModelMapper {
    @Override
    public WordModel mapWordDomainToWordViewModel(WordDomainModel wordDomainModel) {
        return new WordModel(wordDomainModel.getId(),wordDomainModel.getEnglishWord(),wordDomainModel.getBanglaWord(),wordDomainModel.isFav());
    }

    @Override
    public List<WordModel> mapWordDomainListToWordViewModelList(List<WordDomainModel> wordDomainList) {
        List<WordModel> wordList=new ArrayList<>();
        for(WordDomainModel domainModel:wordDomainList)
            wordList.add(mapWordDomainToWordViewModel(domainModel));
        return wordList;
    }
}
