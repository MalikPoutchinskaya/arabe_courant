package com.poutchinskaya.malik.sotunisia.widgets.floatingbutton;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.widget.RadioGroup;

import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.helpers.EntityHelper;
import com.poutchinskaya.malik.sotunisia.model.Category;

import java.util.List;

/**
 *
 */

public class DialogFloatingActionButton extends Dialog {

    private IDialogFloatingResponse iDialogFloatingResponse;
    private EntityHelper entityHelper;
    private List<Category> categories;

    public DialogFloatingActionButton(@NonNull Context context, IDialogFloatingResponse iDialogFloatingResponse, EntityHelper entityHelper, List<Category> categoriesToSort) {
        super(context);
        this.iDialogFloatingResponse = iDialogFloatingResponse;
        this.entityHelper = entityHelper;
        this.categories = categoriesToSort;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.filter_menu);
        setUpListner();

    }


    private void init() {
        // it remove the dialog title
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//
        this.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//        Window window = this.getWindow();
//        WindowManager.LayoutParams param = window.getAttributes();
//        // set the layout at right bottom
////        param.gravity = Gravity.BOTTOM;
////        param.width =WindowManager.LayoutParams.MATCH_PARENT;
////        param.height = WindowManager.LayoutParams.MATCH_PARENT;
//        window.setAttributes(param);
//        // it dismiss the dialog when click outside the dialog frame
//        this.setCanceledOnTouchOutside(true);
    }

    private void setUpListner() {
        ((RadioGroup) this.findViewById(R.id.menu_filter_radiogroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.filter_asc_abc:
                        iDialogFloatingResponse.onSortSelected(entityHelper.getCategoriesSortByAscTitle(categories));
                        break;
                    case R.id.filter_desc_alph:
                        iDialogFloatingResponse.onSortSelected(entityHelper.getCategoriesSortByDescTitle(categories));
                        break;
                    case R.id.filter_asc_stars:
                        iDialogFloatingResponse.onSortSelected(entityHelper.getCategoriesSortByAscStars(categories));
                        break;
                    case R.id.filter_desc_stars:
                        iDialogFloatingResponse.onSortSelected(entityHelper.getCategoriesSortByDescStars(categories));
                        break;
                    case R.id.filter_asc_hist:
                        iDialogFloatingResponse.onSortSelected(entityHelper.getCategoriesSortByAscDates(categories));
                        break;
                    default:
                        //do nothing
                }
            }
        });
    }

}
