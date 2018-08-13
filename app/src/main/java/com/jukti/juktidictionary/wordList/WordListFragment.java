package com.jukti.juktidictionary.wordList;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jukti.juktidictionary.Injection;
import com.jukti.juktidictionary.R;
import com.jukti.juktidictionary.models.WordModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WordListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WordListFragment extends Fragment implements WordListContract.View {


    private WordListPresenter mPresenter;
    private RecyclerView wordRecyClerView;
    private ProgressBar loadingProgress;
    private List<WordModel> wordModels;
    private WordListAdapter mWordAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public WordListFragment() {


    }


    // TODO: Rename and change types and number of parameters
    public static WordListFragment newInstance() {
        WordListFragment fragment = new WordListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new WordListPresenter(Injection.provideUseCaseHandler(),Injection.provideGetWords(getActivity()),Injection.provideAddFavUseCase(getActivity()),this);
        wordModels = new ArrayList<>();
        mWordAdapter = new WordListAdapter(wordModels,mPresenter);
        mLayoutManager = new LinearLayoutManager(getActivity());
     }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word_list, container, false);
        wordRecyClerView = view.findViewById(R.id.word_recyclerview);
        loadingProgress = view.findViewById(R.id.loading_progress);
        wordRecyClerView.setLayoutManager(mLayoutManager);
        wordRecyClerView.setAdapter(mWordAdapter);
        mPresenter.getAllWords();
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showWords(List<WordModel> words) {
        wordModels.clear();
        wordModels.addAll(words);
        showLoadingProgress(false);
        mWordAdapter.setWords(wordModels);

    }

    @Override
    public void showLoadingProgress(boolean isShow) {
        if(isShow){
            loadingProgress.setVisibility(View.VISIBLE);
            wordRecyClerView.setVisibility(View.GONE);
        }else{
            loadingProgress.setVisibility(View.GONE);
            wordRecyClerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void notifyData(int id) {
        mWordAdapter.updateFav(id);
    }


    @Override
    public void showErrorMsg() {
        showLoadingProgress(false);
        Toast.makeText(getActivity(),"No data found",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setPresenter(WordListContract.Presenter presenter) {

    }


}
