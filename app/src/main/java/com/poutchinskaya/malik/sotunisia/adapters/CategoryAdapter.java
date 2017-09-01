package com.poutchinskaya.malik.sotunisia.adapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poutchinskaya.malik.sotunisia.BR;
import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.activities.HomeActvity;
import com.poutchinskaya.malik.sotunisia.activities.WordActivity;
import com.poutchinskaya.malik.sotunisia.appmanagement.AppParamEnum;
import com.poutchinskaya.malik.sotunisia.model.Category;

import java.util.List;


/**
 * Adapter d'une category card
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context mContext;
    private List<Category> categoryList;
    static final int HOME_REQUEST = 1;  // The request code



    /**
     * le viewHolder
     */
    class CategoryViewHolder extends RecyclerView.ViewHolder {


        /**
         * le binding de la vue
         */
        private ViewDataBinding binding;

        CategoryViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        ViewDataBinding getBinding() {
            return binding;
        }


    }

    public CategoryAdapter(Context mContext, List<Category> categoryList) {
        this.mContext = mContext;
        this.categoryList = categoryList;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_category, parent, false);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        Category category = categoryList.get(position);
        holder.getBinding().setVariable(BR.category, category);
        holder.getBinding().executePendingBindings();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WordActivity.class);
                Bundle b = new Bundle();
                b.putLong(AppParamEnum.CATEGORY.toString(), categoryList.get(position).getId()); //Your id
                intent.putExtras(b);
                ((HomeActvity) mContext).startActivityForResult(intent,HOME_REQUEST);

            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


}
