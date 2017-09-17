package com.poutchinskaya.malik.sotunisia.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.ads.MobileAds;
import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.adapters.LanguageListAdapter;
import com.poutchinskaya.malik.sotunisia.helpers.EntityHelper;

/**
 *
 */

public class LanguageAcivity extends AbstractActivityBehavior {
    private RecyclerView recyclerView;
    private EntityHelper entityHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        //TODO: mettre dans une variable global
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-7633719124004269~1259537909");


        entityHelper = new EntityHelper(this, getApplication());

        LanguageListAdapter languageAdapter = new LanguageListAdapter(LanguageAcivity.this, entityHelper.getLanguages());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_image_text);
        recyclerView.setAdapter(languageAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

    }
}
