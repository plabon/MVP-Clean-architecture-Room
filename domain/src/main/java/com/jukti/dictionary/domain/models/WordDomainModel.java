package com.jukti.dictionary.domain.models;

/**
 * Created by Pl@b0n on 11,August,2018
 */
public class WordDomainModel {

    private int id;
    private String englishWord;
    private String banglaWord;
    private boolean isFav;

    public WordDomainModel(int id, String englishWord, String banglaWord,int isFav) {
        this.id = id;
        this.englishWord = englishWord;
        this.banglaWord = banglaWord;
        this.isFav= isFav==1?true:false;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getBanglaWord() {
        return banglaWord;
    }

    public void setBanglaWord(String banglaWord) {
        this.banglaWord = banglaWord;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    @Override
    public String toString() {
        return "WordDomainModel{" +
                "id=" + id +
                ", englishWord='" + englishWord + '\'' +
                ", banglaWord='" + banglaWord + '\'' +
                ", isFav=" + isFav +
                '}';
    }
}
