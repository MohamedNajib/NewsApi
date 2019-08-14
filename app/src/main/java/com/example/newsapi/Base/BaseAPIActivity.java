package com.example.newsapi.Base;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.newsapi.Base.BaseModel.BaseFlowerLoading;


public abstract class BaseAPIActivity <T extends BaseViewModel> extends BaseActivity {

    public T viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = getViewModel();


        if (viewModel != null){
            viewModel.message.observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    showMessage("Error ..", s, "OH");
                }
            });

            viewModel.progress.observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean show) {
                    if (show){
                        showFlowerLoading("Loading..");
                    }else {
                        hideFlowerLoading();
                    }
                }
            });

            viewModel.flowerLoading.observe(this, new Observer<BaseFlowerLoading>() {
                @Override
                public void onChanged(@Nullable BaseFlowerLoading show) {
                    if (show.getProgress()){
                        showFlowerLoading(show.getMessag());
                    }else {
                        hideFlowerLoading();
                    }
                }
            });
        }
    }

    public abstract T getViewModel();
}
