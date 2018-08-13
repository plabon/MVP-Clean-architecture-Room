package com.jukti.juktidictionary.wordList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jukti.juktidictionary.R;
import com.jukti.juktidictionary.models.WordModel;

import java.util.List;

/**
 * Created by Pl@b0n on 13,August,2018
 */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>{
    List<WordModel> words;
    WordListPresenter mPresenter;

    public WordListAdapter(List<WordModel> words, WordListPresenter presenter) {
        this.words = words;
        this.mPresenter = presenter;
    }

    public void updateFav(int id){
        for(WordModel wordModel:words){
            if(wordModel.getId()== id){
                wordModel.setFav(!wordModel.isFav());
            }
        }
        notifyDataSetChanged();
    }

    public void setWords(List<WordModel> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_list_item, parent, false);
        return new WordViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, final int position) {
        holder.updateItem(words.get(position));
        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordModel word=words.get(position);
                mPresenter.addFav(word.getId(),!word.isFav());
            }
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }




    static final class WordViewHolder extends RecyclerView.ViewHolder{
        TextView englishWordText, banglaWordText;
        ImageButton favBtn;

        public WordViewHolder(View itemView) {
            super(itemView);
            englishWordText = (TextView) itemView.findViewById(R.id.english_word);
            banglaWordText = (TextView) itemView.findViewById(R.id.bangla_word);
            favBtn = (ImageButton) itemView.findViewById(R.id.fav_btn);
        }

        public void updateItem(WordModel word){
            englishWordText.setText(word.getEnglishWord());
            banglaWordText.setText(word.getBanglaWord());
            if(word.isFav()){
                favBtn.setImageResource(R.drawable.fav_icon_fill);
            }else{
                favBtn.setImageResource(R.drawable.fav_icon);
            }

        }
    }
}
