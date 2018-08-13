package com.jukti.dictionary.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Pl@b0n on 11,August,2018
 */
@Entity(tableName = "Dictionary")
public class WordEntity {
        @PrimaryKey
        @ColumnInfo(name = "Id")
        private int id;

        @ColumnInfo(name = "English")
        private String englishWord;

        @ColumnInfo(name = "Hindi")
        private String banglaWord;
        @ColumnInfo(name = "IsFav")
        private int isFav;


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



        public int getIsFav() {
                return isFav;
        }

        public void setIsFav(int isFav) {
                this.isFav = isFav;
        }

        @Override
        public String toString() {
                return "WordEntity{" +
                        "id=" + id +
                        ", englishWord='" + englishWord + '\'' +
                        ", banglaWord='" + banglaWord + '\'' +
                        ", isFav=" + isFav +
                        '}';
        }
}
