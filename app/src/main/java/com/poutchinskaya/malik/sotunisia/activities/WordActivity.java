package com.poutchinskaya.malik.sotunisia.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;

import com.poutchinskaya.malik.sotunisia.BR;
import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.adapters.ExpandableListAdapter;
import com.poutchinskaya.malik.sotunisia.appmanagement.AppParamEnum;
import com.poutchinskaya.malik.sotunisia.databinding.ActivityWordsBinding;
import com.poutchinskaya.malik.sotunisia.fragments.WordFragment;
import com.poutchinskaya.malik.sotunisia.helpers.EntityHelper;
import com.poutchinskaya.malik.sotunisia.model.Word;

import java.util.List;


/**
 * Binding activity
 */

public class WordActivity extends AbstractActivityBehavior {

    private EntityHelper entityHelper;

    private DrawerLayout mDrawerLayout;
    private ExpandableListView mDrawerList;

    private Long categoryId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActivityWordsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_words);
        // However, if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
        if (savedInstanceState != null) {
            return;
        }

        Fragment wordFragment = new WordFragment();
        // adding fragment to relative layout by using layout id
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.word_fragment, wordFragment);
        transaction.commit();

        entityHelper = new EntityHelper(this, getApplication());
        Bundle b = getIntent().getExtras();
        if (b != null) {
            categoryId = b.getLong(AppParamEnum.CATEGORY.toString());
            binding.setVariable(BR.category, entityHelper.getCategory(categoryId));

        }
        //drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ExpandableListView) findViewById(R.id.left_drawer);


        // Set the adapter for the list view
        mDrawerList.setAdapter(new ExpandableListAdapter(this, entityHelper.getPrononciations(), entityHelper.getDetailsPrononciation()));



        findViewById(R.id.action_filter_fragment_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });
    }

    public void openDrawer() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    public List<Word> getWords() {
        return entityHelper.getWords(categoryId);
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
