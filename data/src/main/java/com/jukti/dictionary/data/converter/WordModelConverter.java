package com.jukti.dictionary.data.converter;

import com.jukti.dictionary.data.entity.WordEntity;
import com.jukti.dictionary.domain.models.WordDomainModel;

import java.util.List;

public interface WordModelConverter {

    WordDomainModel modelToDomain(WordEntity entity);
    List<WordDomainModel> modelListToDomainList(List<WordEntity> entityList);

}
