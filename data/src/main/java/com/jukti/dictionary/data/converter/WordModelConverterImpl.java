package com.jukti.dictionary.data.converter;

import com.jukti.dictionary.data.entity.WordEntity;
import com.jukti.dictionary.domain.models.WordDomainModel;

import java.util.ArrayList;
import java.util.List;

public final class WordModelConverterImpl implements WordModelConverter {


    @Override
    public WordDomainModel modelToDomain(WordEntity entity) {
        return new WordDomainModel(entity.getId(),entity.getEnglishWord(),entity.getBanglaWord(),entity.getIsFav());
    }

    @Override
    public List<WordDomainModel> modelListToDomainList(List<WordEntity> entityList) {
        List<WordDomainModel> wordList=new ArrayList<>();
        for(WordEntity entity:entityList){
            wordList.add(modelToDomain(entity));
        }
        return wordList;
    }


}
