package com.poutchinskaya.malik.sotunisia.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.poutchinskaya.malik.sotunisia.BR;
import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.adapters.CategoryAdapter;
import com.poutchinskaya.malik.sotunisia.appmanagement.Storage;
import com.poutchinskaya.malik.sotunisia.databinding.ActivityHomeBinding;
import com.poutchinskaya.malik.sotunisia.fragments.SearchFragment;
import com.poutchinskaya.malik.sotunisia.helpers.ChartDataHelper;
import com.poutchinskaya.malik.sotunisia.helpers.EntityHelper;
import com.poutchinskaya.malik.sotunisia.model.Category;
import com.poutchinskaya.malik.sotunisia.model.Language;
import com.poutchinskaya.malik.sotunisia.widgets.floatingbutton.DialogFloatingActionButton;
import com.poutchinskaya.malik.sotunisia.widgets.floatingbutton.IDialogFloatingResponse;
import com.poutchinskaya.malik.sotunisia.widgets.graph.ScoreLineChart;
import com.poutchinskaya.malik.sotunisia.widgets.graph.ScoreLineDataSet;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */

public class HomeActvity extends AbstractActivityBehavior {
    static final int HOME_REQUEST = 1;  // The request code

    private List<Category> categories;

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private Language languageSelected;
    private EntityHelper entityHelper;
    private ScoreLineChart chart;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);


        long languageSelectedId = new Storage(getApplicationContext()).getLanguage();

        initCollapsingToolbar();

        entityHelper = new EntityHelper(this, getApplication());
        languageSelected = entityHelper.getLanguageSelected(languageSelectedId);
        binding.setVariable(BR.language, languageSelected);
        categories = new ArrayList<>(entityHelper.getCategories());

        adapter = new CategoryAdapter(this, categories);


        //search fragment
        findViewById(R.id.action_search_fragment_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment searchFragment = new SearchFragment();
                // adding fragment to relative layout by using layout id
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_bottom);
                transaction.replace(R.id.search_fragment, searchFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //All
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        //Graph de progression
        chart = (ScoreLineChart) findViewById(R.id.chart);
        buildProgressChart();


        //gestion du filtre
        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFloatingActionButton dialog = new DialogFloatingActionButton(HomeActvity.this, new IDialogFloatingResponse() {
                    @Override
                    public void onSortSelected(List<Category> categoriesSorted) {
                        categories.clear();
                        categories.addAll(categoriesSorted);
                        adapter.notifyDataSetChanged();
                    }
                }, entityHelper, new ArrayList<Category>(categories));

                dialog.show();

            }
        });
    }


    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    private class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == HOME_REQUEST) {
            categories.clear();
            categories.addAll(entityHelper.getCategories());
            adapter.notifyDataSetChanged();

            //progression utilisateur
            buildProgressChart(); // refresh

        }
    }

    private void buildProgressChart() {
        ChartDataHelper helper = new ChartDataHelper(this, getApplication());
        List<Entry> entries = helper.getProgressionEntries();
        if (entries.size() > 0) {
            ScoreLineDataSet dataSet = new ScoreLineDataSet(entries, "", getApplicationContext()); // add entries to dataset

            LineData lineData = new LineData(dataSet);
            chart.setData(lineData);
            chart.invalidate(); // refresh
        }
    }
}


