package com.jukti.juktidictionary.wordList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jukti.juktidictionary.R;
import com.jukti.juktidictionary.utils.ActiivtyUtils;

public class WordListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        WordListFragment wordsFragment =
                (WordListFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (wordsFragment == null) {
            // Create the fragment
            wordsFragment = WordListFragment.newInstance();
            ActiivtyUtils.addFragmentToActivity(
                    getSupportFragmentManager(), wordsFragment, R.id.container);
        }

    }
}
