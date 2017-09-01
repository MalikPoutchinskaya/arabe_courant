package com.poutchinskaya.malik.sotunisia.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poutchinskaya.malik.sotunisia.BR;
import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.model.Word;

import java.util.List;


/**
 *
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private Context mContext;
    private List<Word> wordList;


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

    public WordListAdapter(Context mContext, List<Word> wordList) {
        this.mContext = mContext;
        this.wordList = wordList;
    }

    @Override
    public WordViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_list_word, parent, false);

        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, final int position) {
        Word word = wordList.get(position);
        holder.getBinding().setVariable(BR.word, word);
        holder.getBinding().executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }


}
