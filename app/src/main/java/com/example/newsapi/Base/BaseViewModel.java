package com.example.newsapi.Base;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.newsapi.Base.BaseModel.BaseFlowerLoading;


public class BaseViewModel <T> extends ViewModel {

    public MutableLiveData<String> message;
    public MutableLiveData<Boolean> progress;
    public MutableLiveData<BaseFlowerLoading> flowerLoading;
    private T navigator;

    public BaseViewModel(){
        message = new MutableLiveData<>();
        progress = new MutableLiveData<>();
        flowerLoading = new MutableLiveData<>();
    }

    public T getNavigator() {
        return navigator;
    }

    public void setNavigator(T navigator) {
        this.navigator = navigator;
    }
}
