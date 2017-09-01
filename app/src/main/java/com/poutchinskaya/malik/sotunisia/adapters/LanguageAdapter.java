package com.poutchinskaya.malik.sotunisia.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.poutchinskaya.malik.sotunisia.BR;
import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.databinding.AdapterLanguageBinding;

import java.util.List;

/**
 *
 */
public class LanguageAdapter<T> extends BaseAdapter{

    private Context mContext;
    private List<T> listObject;

    public LanguageAdapter(Context context, List<T> listObject) {
        mContext = context;
        this.listObject = listObject;
    }

    @Override
    public int getCount() {
        return listObject.size();
    }

    @Override
    public Object getItem(int i) {
        return listObject.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        AdapterLanguageBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(
                    LayoutInflater.from(mContext),
                    R.layout.adapter_language, parent, false);
            convertView = binding.getRoot();
        } else {
            binding = (AdapterLanguageBinding) convertView.getTag();
        }

        binding.setVariable(BR.language, this.getItem(i));
        convertView.setTag(binding);
        return convertView;
    }


}