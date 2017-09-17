package com.poutchinskaya.malik.sotunisia.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poutchinskaya.malik.sotunisia.BR;
import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.activities.HomeActvity;
import com.poutchinskaya.malik.sotunisia.appmanagement.AppParamEnum;
import com.poutchinskaya.malik.sotunisia.model.Language;

import java.util.List;


/**
 *
 */

public class LanguageListAdapter extends RecyclerView.Adapter<LanguageListAdapter.LanguageViewHolder> {
    private Context mContext;
    private List<Language> languages;


    /**
     * le viewHolder
     */
    class LanguageViewHolder extends RecyclerView.ViewHolder {


        /**
         * le binding de la vue
         */
        private ViewDataBinding binding;

        LanguageViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        ViewDataBinding getBinding() {
            return binding;
        }


    }

    public LanguageListAdapter(Context mContext, List<Language> languages) {
        this.mContext = mContext;
        this.languages = languages;
    }

    @Override
    public LanguageViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_language, parent, false);

        return new LanguageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LanguageViewHolder holder, final int position) {
        final Language language = languages.get(position);
        holder.getBinding().setVariable(BR.language, language);
        holder.getBinding().executePendingBindings();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = mContext.getSharedPreferences(AppParamEnum.APP_PREFERENCE.name(), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putLong(AppParamEnum.LANGUAGE.name(), language.getId());
                editor.apply();

                //FIXME: quick fix
                if (language.getName().equals("Libanais")) {
                    // 1. Instantiate an AlertDialog.Builder with its constructor
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                    // 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage("Bientôt disponible !")
                            .setTitle("Libanais :")
                            .setNegativeButton("Très bien",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });


                    // 3. Get the AlertDialog from create()
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else {
                    Intent intent = new Intent(v.getContext(), HomeActvity.class);
                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return languages.size();
    }


}
