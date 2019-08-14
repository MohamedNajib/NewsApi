package com.example.newsapi.UI.Fragments.News;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.newsapi.Adapter.ArticlesAdapter;
import com.example.newsapi.Base.BaseAPIFragment;
import com.example.newsapi.Model.Articles.ArticlesItem;
import com.example.newsapi.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseAPIFragment<NewsViewModel> implements NewsNavigator {

    @BindView(R.id.search_view)
    SearchView searchView;
    Unbinder unbinder;
    @BindView(R.id.txtHeader)
    TextView txtHeader;
    @BindView(R.id.ArticleRecyclerView)
    RecyclerView ArticleRecyclerView;

    //var
    private String mSource_id;
    private ArticlesAdapter mArticlesAdapter;

    private NavController controller;


    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        viewModel.setNavigator(this);
        unbinder = ButterKnife.bind(this, view);
        initSearchView();


        if (viewModel.mListOfArticles.getValue() == null) {
            if (getArguments() != null) {
                NewsFragmentArgs args = NewsFragmentArgs.fromBundle(getArguments());
                viewModel.getNewsArticles("", "", "", args.getId());
            }
        }

        subscribeToLiveData();

        return view;
    }

    private void subscribeToLiveData() {
        viewModel.mListOfArticles.observe(this, new Observer<List<ArticlesItem>>() {
            @Override
            public void onChanged(@Nullable List<ArticlesItem> articlesItems) {
                initRecyclerView(articlesItems);
            }
        });
    }

    private void initRecyclerView(final List<ArticlesItem> articlesItems) {
        ArticleRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        ArticleRecyclerView.setHasFixedSize(true);
        mArticlesAdapter = new ArticlesAdapter(activity, articlesItems, new ArticlesAdapter.OnClickListener() {
            @Override
            public void onArticleClicked(int position) {
                ArticlesItem articlesItem = articlesItems.get(position);

                NewsFragmentDirections.ActionNewsFragmentToNewsArticleFragment action =
                        NewsFragmentDirections.actionNewsFragmentToNewsArticleFragment();
                action.setUrl(articlesItem.getUrl());
                action.setTitle(articlesItem.getTitle());
                if (articlesItem.getAuthor() == null){
                    action.setAuthor("");
                }else {
                    action.setAuthor(articlesItem.getAuthor());
                }
                action.setDate(articlesItem.getPublishedAt());
                action.setImg(articlesItem.getUrlToImage());
                action.setSource(articlesItem.getSource().getName());
                controller.navigate(action);
            }
        });
        ArticleRecyclerView.setAdapter(mArticlesAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
    }

    @Override
    public NewsViewModel getFViewModel() {
        return ViewModelProviders.of(activity).get(NewsViewModel.class);
    }

    private void initSearchView() {
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtHeader.setVisibility(View.GONE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                txtHeader.setVisibility(View.VISIBLE);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.length() > 2) {
                    //onLoadingSwipeRefresh(query);
                    //shawSuccessToast(s);
                } else {
                    shawWarningToast("Type more than two letters!");
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
