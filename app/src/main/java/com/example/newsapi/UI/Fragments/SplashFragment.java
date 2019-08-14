package com.example.newsapi.UI.Fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.newsapi.Base.BaseFragment;
import com.example.newsapi.Base.BaseModel.BaseFlowerLoading;
import com.example.newsapi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends BaseFragment {

    public static final int SPLASH_TIME = 1000;

    // var
    private NavOptions navOptions;
    private NavController controller;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // nav Splash screen
                navOptions = new NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build();
                controller.navigate(R.id.action_splashFragment_to_sourcesFragment, null, navOptions);
            }
        }, SPLASH_TIME);
    }
}
