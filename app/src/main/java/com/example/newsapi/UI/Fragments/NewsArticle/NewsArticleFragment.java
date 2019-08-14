package com.example.newsapi.UI.Fragments.NewsArticle;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsapi.Base.BaseAPIFragment;
import com.example.newsapi.R;
import com.example.newsapi.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsArticleFragment extends BaseAPIFragment<NewsArticleViewModel> implements NewsArticleNavigator {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.title_on_appbar)
    TextView titleOnAppbar;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.date)
    TextView date;
    Unbinder unbinder;

    public NewsArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_article, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (getArguments() != null) {
            NewsArticleFragmentArgs args = NewsArticleFragmentArgs.fromBundle(getArguments());

            title.setText(args.getTitle());

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.error(Utils.getRandomDrawbleColor());

            Glide.with(activity)
                    .load(args.getImg())
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(backdrop);

            date.setText(Utils.DateFormat(args.getDate()));
            titleOnAppbar.setText(args.getSource());

            String author;
            if (args.getAuthor() != null){
                author = " \u2022 " + args.getAuthor();
            } else {
                author = "";
            }

            time.setText(args.getSource() + author + " \u2022 " + Utils.DateToTimeFormat(args.getDate()));
            initWebView(args.getUrl(), view);
        }

        return view;
    }

    private void initWebView(String url, View v){
        WebView webView = v.findViewById(R.id.webView);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public NewsArticleViewModel getFViewModel() {
        return ViewModelProviders.of(activity).get(NewsArticleViewModel.class);
    }
}
