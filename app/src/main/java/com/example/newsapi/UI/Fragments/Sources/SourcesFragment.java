package com.example.newsapi.UI.Fragments.Sources;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.newsapi.Adapter.SourcesAdapter;
import com.example.newsapi.Base.BaseAPIFragment;
import com.example.newsapi.Model.Sources.SourcesItem;
import com.example.newsapi.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SourcesFragment extends BaseAPIFragment<SourcesViewModel> implements SourcesNavigator {

    @BindView(R.id.RecyclerSources)
    RecyclerView RecyclerSources;
    Unbinder unbinder;
    @BindView(R.id.SourcesShimmerView)
    ShimmerFrameLayout SourcesShimmerView;

    //var
    private SourcesAdapter mSourcesAdapter;
    private NavController controller;

    public SourcesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sources, container, false);
        unbinder = ButterKnife.bind(this, view);
        viewModel.setNavigator(this);

        showShimmerLayout();

        if (viewModel.mSourcesItemList.getValue() == null) {
            viewModel.loadNewsSources("", "", "");
        }

        subscribeToLiveData();
        return view;
    }

    private void subscribeToLiveData() {
        viewModel.mSourcesItemList.observe(this, new Observer<List<SourcesItem>>() {
            @Override
            public void onChanged(@Nullable List<SourcesItem> sourcesItems) {
                if (sourcesItems != null) {
                    hideShimmerLayout();
                }
                initRecyclerView(sourcesItems);
            }
        });
    }

    private void initRecyclerView(final List<SourcesItem> sourcesItems) {
        RecyclerSources.setLayoutManager(new LinearLayoutManager(activity));
        RecyclerSources.setHasFixedSize(true);
        mSourcesAdapter = new SourcesAdapter(activity, sourcesItems, new SourcesAdapter.OnItemClick() {
            @Override
            public void onSourcesItemClickedListener(int position) {
                SourcesItem sourcesItem = sourcesItems.get(position);

                // Safe Args (passing data) Navigation
                SourcesFragmentDirections.ActionSourcesFragmentToNewsFragment action =
                        SourcesFragmentDirections.actionSourcesFragmentToNewsFragment();
                action.setId(sourcesItem.getId());
                controller.navigate(action);
            }
        });
        RecyclerSources.setAdapter(mSourcesAdapter);
    }

    @Override
    public SourcesViewModel getFViewModel() {
        return ViewModelProviders.of(activity).get(SourcesViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.TV_Filter)
    public void onViewClicked() {
        new FilterDialog(activity, viewModel).filterSourcesResult();
    }

    public void hideShimmerLayout() {
        SourcesShimmerView.setVisibility(View.GONE);
        SourcesShimmerView.stopShimmer();
    }

    public void showShimmerLayout() {
        SourcesShimmerView.setVisibility(View.VISIBLE);
        SourcesShimmerView.startShimmer();
    }
}
