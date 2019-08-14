package com.example.newsapi.UI.Fragments.Sources;

import android.arch.lifecycle.MutableLiveData;

import com.example.newsapi.Base.BaseModel.BaseFlowerLoading;
import com.example.newsapi.Base.BaseViewModel;
import com.example.newsapi.Constants;
import com.example.newsapi.Model.Sources.SourcesItem;
import com.example.newsapi.Model.Sources.SourcesResponse;
import com.example.newsapi.rest.APIManager;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SourcesViewModel extends BaseViewModel<SourcesNavigator> {

    public MutableLiveData<List<SourcesItem>> mSourcesItemList;

    public SourcesViewModel() {
        mSourcesItemList = new MutableLiveData<>();
    }

    /**
     * Call Sources Api
     */
    public void loadNewsSources(String category, String language, String country) {
        //flowerLoading.postValue(new BaseFlowerLoading("Loading..", true));

        APIManager.getApis().getSources(category, language, country, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SourcesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SourcesResponse sourcesResponse) {
                       // flowerLoading.postValue(new BaseFlowerLoading(null, false));

                        if ("ok".equals(sourcesResponse.getStatus())) {
                            mSourcesItemList.postValue(sourcesResponse.getSources());
                        }else {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //flowerLoading.postValue(new BaseFlowerLoading(null, false));
                        message.postValue("Oops Error: \n" + e.getLocalizedMessage());
                    }
                });
    }
}
