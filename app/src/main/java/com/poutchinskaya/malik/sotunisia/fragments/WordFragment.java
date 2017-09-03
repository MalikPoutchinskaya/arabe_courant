package com.poutchinskaya.malik.sotunisia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.activities.WordActivity;
import com.poutchinskaya.malik.sotunisia.adapters.WordListAdapter;
import com.poutchinskaya.malik.sotunisia.model.Word;

import java.util.List;

public class WordFragment extends Fragment {
    private List<Word> words;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private WordListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.words_list);
        view.findViewById(R.id.floatingActionButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment newFragment = new ExerciceFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack if needed
                transaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_bottom);
                transaction.replace(R.id.word_fragment, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        words = ((WordActivity) getActivity()).getWords();
        recyclerView.setNestedScrollingEnabled(true);
        adapter = new WordListAdapter(getContext(), words);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);
    }
}