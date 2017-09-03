package com.poutchinskaya.malik.sotunisia.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.databinding.FragmentSearchBinding;
import com.poutchinskaya.malik.sotunisia.helpers.EntityHelper;
import com.poutchinskaya.malik.sotunisia.model.Word;

import java.util.List;

/**
 *
 */

public class SearchFragment extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final FragmentSearchBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_search, container, false);
        View view = binding.getRoot();

        final EntityHelper entityHelper = new EntityHelper(getContext(), getActivity().getApplication());

        final FloatingSearchView floatingSearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);

        //affiche la suggestion
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                //get suggestions based on newQuery
                List<Word> suggestions = entityHelper.getWordsByName(newQuery);

                //pass them on to the search view
                floatingSearchView.swapSuggestions(suggestions);
            }
        });

        //Selection de la suggestion
        floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                Word wordSearched = entityHelper.getWordsByName(searchSuggestion.getBody()).get(0);
                floatingSearchView.clearSearchFocus();
                binding.setWord(wordSearched);
            }

            @Override
            public void onSearchAction(String currentQuery) {

            }
        });


        return view;
    }
}
