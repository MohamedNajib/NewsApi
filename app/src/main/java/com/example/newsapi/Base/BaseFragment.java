package com.example.newsapi.Base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

import cc.cloudist.acplibrary.ACProgressFlower;

public abstract class BaseFragment extends Fragment {

    protected BaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
    }

    public MaterialDialog showMessage(int titleResId, int messageResId, int posResText){
        return activity.showMessage(titleResId,messageResId,posResText);

    }

    public MaterialDialog showConfirmationMessage(int titleResId, int messageResId, int posResText,
                                                  MaterialDialog.SingleButtonCallback onPosAction){
        return activity.showConfirmationMessage(titleResId,messageResId,posResText,onPosAction);

    }
    public MaterialDialog showMessage(String title,String message,String posText){

        return activity.showMessage(title,message,posText);
    }
    public MaterialDialog showProgressBar(int message){

        return activity.showProgressBar(message);
    }
    public void hideProgressBar() {
        activity.hideProgressBar();
    }

    public void openActivity(Class<?> cls) {
        activity.openActivity(cls);
    }

    public boolean isInternetConnection() {
        return activity.isInternetConnection();
    }

    public void checkNetworkAvailable() {
        activity.checkNetworkAvailable();
    }

    public ACProgressFlower showFlowerLoading(String message) {
        return activity.showFlowerLoading(message);
    }

    public void hideFlowerLoading() {
        activity.hideFlowerLoading();
    }

    public void shawErrorToast(String message) {
        activity.shawErrorToast(message);
    }

    public void shawSuccessToast(String message) {
        activity.shawSuccessToast(message);
    }

    public void shawInfoToast(String message) {
        activity.shawInfoToast(message);
    }

    public void shawWarningToast(String message) {
        activity.shawWarningToast(message);
    }

    public void shawErrorToast(int message) {
        activity.shawErrorToast(message);
    }

    public void shawSuccessToast(int message) {
        activity.shawSuccessToast(message);
    }

    public void shawInfoToast(int message) {
        activity.shawInfoToast(message);
    }

    public void shawWarningToast(int message) {
        activity.shawWarningToast(message);
    }
}
