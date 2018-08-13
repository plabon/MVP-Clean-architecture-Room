package com.jukti.juktidictionary.mapper;

import com.jukti.dictionary.domain.models.WordDomainModel;
import com.jukti.juktidictionary.models.WordModel;

import java.util.List;

/**
 * Created by Pl@b0n on 12,August,2018
 */
public interface DomainToViewModelMapper {
    WordModel mapWordDomainToWordViewModel(WordDomainModel wordDomainModel);
    List<WordModel> mapWordDomainListToWordViewModelList(List<WordDomainModel> wordDomainList);
}
