package com.example.newsapi.UI.Fragments.News;

import android.arch.lifecycle.MutableLiveData;

import com.example.newsapi.Base.BaseModel.BaseFlowerLoading;
import com.example.newsapi.Base.BaseViewModel;
import com.example.newsapi.Constants;
import com.example.newsapi.Model.Articles.ArticlesItem;
import com.example.newsapi.Model.Articles.ArticlesResponse;
import com.example.newsapi.rest.APIManager;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsViewModel extends BaseViewModel<NewsNavigator> {

    MutableLiveData<List<ArticlesItem>> mListOfArticles;

    public NewsViewModel() {
        super();
        mListOfArticles = new MutableLiveData<>();
    }

    public void getNewsArticles(String keyword, String language, String sortBy, String sources){
        flowerLoading.setValue(new BaseFlowerLoading("Loading..", true));

        APIManager.getApis().getNews(sources, "", Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ArticlesResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(ArticlesResponse articlesResponse) {
                flowerLoading.setValue(new BaseFlowerLoading(null, false));
                if ("ok".equals(articlesResponse.getStatus())) {
                    mListOfArticles.setValue(articlesResponse.getArticles());
                } else {
                }
            }

            @Override
            public void onError(Throwable e) {
                flowerLoading.setValue(new BaseFlowerLoading(null, false));
                message.setValue("Oops Error: \n" + e.getLocalizedMessage());
            }
        });
    }


}
