package com.example.newsapi.UI.Fragments.Sources;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.newsapi.Adapter.CustomSpinnerAdapter;
import com.example.newsapi.Constants;
import com.example.newsapi.Model.CustomSpinnerItem;
import com.example.newsapi.R;

import java.util.ArrayList;

public final class FilterDialog {

    public static final String CATEGORY = "Category";
    public static final String LANGUAGE = "Language";
    public static final String COUNTRY = "Country";

    private Context mContext;
    private CustomSpinnerAdapter customAdapter;
    private Dialog dialog;

    private String mCategory;
    private String mLanguage;
    private String mCountry;

    private SourcesViewModel viewModel;

    public FilterDialog(Context mContext, SourcesViewModel viewModel) {
        this.mContext = mContext;
        this.viewModel = viewModel;
    }

    public void filterSourcesResult() {

        dialog = new Dialog(mContext);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.filter_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(mContext.getResources().getColor(R.color.dialogBackground)));
        dialog.setCancelable(true);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        ConstraintLayout CLPOP = dialog.findViewById(R.id.CLPOP);

        Spinner CategorySpinner = dialog.findViewById(R.id.CategorySpinner);
        Spinner LanguageSpinner = dialog.findViewById(R.id.LanguageSpinner);
        Spinner CountrySpinner = dialog.findViewById(R.id.CountrySpinner);

        setCategorySpinner(CategorySpinner, Constants.getCategoryList(), CATEGORY);
        setCategorySpinner(LanguageSpinner, Constants.getLanguageList(), LANGUAGE);
        setCategorySpinner(CountrySpinner, Constants.getCountryList(), COUNTRY);

        ConstraintLayout end = dialog.findViewById(R.id.end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (mCategory.equals("All")) {
                    mCategory = "";
                }if (mLanguage.equals("All")) {
                    mLanguage = "";
                }if (mCountry.equals("All")) {
                    mCountry = "";
                }
                viewModel.loadNewsSources(mCategory, mLanguage, mCountry);

                Toast.makeText(mContext, mCategory + "/" + mLanguage + "/" + mCountry, Toast.LENGTH_SHORT).show();
            }
        });


        Animation mFromBottom;
        mFromBottom = AnimationUtils.loadAnimation(mContext, R.anim.frombottom);
        CLPOP.setAnimation(mFromBottom);
        dialog.show();

        dialog.getWindow().setAttributes(layoutParams);
    }

    private void setCategorySpinner(Spinner spinner, ArrayList<CustomSpinnerItem> customList, final String state) {
        customAdapter = new CustomSpinnerAdapter(mContext, customList);
        if (spinner != null) {
            spinner.setAdapter(customAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    CustomSpinnerItem customSpinnerItem = (CustomSpinnerItem) parent.getSelectedItem();
                    String data = customSpinnerItem.getSpinnerItemName();

                    switch (state) {
                        case CATEGORY:
                            mCategory = data;
                            break;
                        case LANGUAGE:
                            mLanguage = data;
                            break;
                        case COUNTRY:
                            mCountry = data;
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
}
