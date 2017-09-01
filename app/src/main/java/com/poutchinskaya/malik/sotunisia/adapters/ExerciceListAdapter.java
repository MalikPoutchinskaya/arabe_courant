package com.poutchinskaya.malik.sotunisia.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poutchinskaya.malik.sotunisia.BR;
import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.fragments.IExerciceResponse;
import com.poutchinskaya.malik.sotunisia.model.Word;

import java.util.List;


/**
 *
 */

public class ExerciceListAdapter extends RecyclerView.Adapter<ExerciceListAdapter.WordViewHolder> {
    private Context mContext;
    private List<Word> wordList;
    private IExerciceResponse onResponseSelected;
    private View viewToFind;

    /**
     * le viewHolder
     */
    class WordViewHolder extends RecyclerView.ViewHolder {


        /**
         * le binding de la vue
         */
        private ViewDataBinding binding;

        WordViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        ViewDataBinding getBinding() {
            return binding;
        }


    }

    public ExerciceListAdapter(Context mContext, List<Word> wordList, Word viewToFind, IExerciceResponse onResponseSelected) {
        this.mContext = mContext;
        this.wordList = wordList;
//        this.viewToFind = viewToFind;
        this.onResponseSelected = onResponseSelected;
    }

    @Override
    public WordViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_list_exercice, parent, false);

        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, final int position) {
        final Word word = wordList.get(position);
        //init le back à blanc (non selectionné)
        holder.itemView.findViewById(R.id.list_exercice_arabic_word).setBackgroundColor(Color.TRANSPARENT);
        holder.getBinding().setVariable(BR.word, word);
        holder.getBinding().executePendingBindings();
        // si c'est l'élément à trouver, on le persist pour le resultat
        if (word.isQuestion()) {
            viewToFind = holder.itemView.findViewById(R.id.list_exercice_arabic_word);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View responseView = v.findViewById(R.id.list_exercice_arabic_word);
                onResponseSelected.onResponseSelected(position, word);
                if (word.isQuestion()) {
                    responseView.setBackground(v.getResources().getDrawable(R.drawable.background_response_true_selector));
                } else {
                    responseView.setBackground(v.getResources().getDrawable(R.drawable.background_response_false_selector));
                    viewToFind.setBackground(v.getResources().getDrawable(R.drawable.background_response_true_selector));

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }


}
