package com.example.newsapi.Base;

import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import es.dmoral.toasty.Toasty;


public abstract class BaseActivity extends AppCompatActivity {

    protected AppCompatActivity thisActivity;
    public MaterialDialog dialog;
    public ACProgressFlower progressFlower;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = this;
    }

    public MaterialDialog showMessage(int titleResId, int messageResId, int posResText) {
        new MaterialDialog.Builder(this)
                .title(titleResId)
                .content(messageResId)
                .positiveText(posResText)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).show();

        return dialog;
    }

    public MaterialDialog showConfirmationMessage(int titleResId, int messageResId, int posResText,
                                                  MaterialDialog.SingleButtonCallback onPosAction) {
        new MaterialDialog.Builder(this)
                .title(titleResId)
                .content(messageResId)
                .positiveText(posResText)
                .onPositive(onPosAction)
                .show();

        return dialog;
    }

    public MaterialDialog showMessage(String title, String message, String posText) {
        dialog = new MaterialDialog.Builder(this)
                .title(title)
                .content(message)
                .positiveText(posText)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).show();

        return dialog;
    }

    public MaterialDialog showProgressBar(int message) {
        dialog = new MaterialDialog.Builder(this)
                .progress(true, 0)
                .content(message)
                .cancelable(false)
                .show();

        return dialog;
    }

    public void hideProgressBar() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    public void openActivity(Class<?> cls) {
        Intent in = new Intent(this, cls);
        this.startActivity(in);
    }

    public boolean isInternetConnection() {
        ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void checkNetworkAvailable() {
        if (isInternetConnection()) {
            return;
        } else {
            shawErrorToast("No Internet Connection");
        }
    }

    public ACProgressFlower showFlowerLoading(String message) {
        progressFlower = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text(message)
                .fadeColor(Color.DKGRAY).build();
        progressFlower.show();
        return progressFlower;
    }

    public void hideFlowerLoading() {
        if (progressFlower != null && progressFlower.isShowing())
            progressFlower.dismiss();
    }

    public void shawErrorToast(String message) {
        Toasty.error(thisActivity, message, Toast.LENGTH_LONG, true).show();
    }

    public void shawSuccessToast(String message) {
        Toasty.success(thisActivity, message, Toast.LENGTH_LONG, true).show();
    }

    public void shawInfoToast(String message) {
        Toasty.info(thisActivity, message, Toast.LENGTH_LONG, true).show();
    }

    public void shawWarningToast(String message) {
        Toasty.warning(thisActivity, message, Toast.LENGTH_LONG, true).show();
    }

    public void shawErrorToast(int message) {
        Toasty.error(thisActivity, message, Toast.LENGTH_LONG, true).show();
    }

    public void shawSuccessToast(int message) {
        Toasty.success(thisActivity, message, Toast.LENGTH_LONG, true).show();
    }

    public void shawInfoToast(int message) {
        Toasty.info(thisActivity, message, Toast.LENGTH_LONG, true).show();
    }

    public void shawWarningToast(int message) {
        Toasty.warning(thisActivity, message, Toast.LENGTH_LONG, true).show();
    }

}
