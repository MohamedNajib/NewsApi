package com.example.newsapi.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsapi.Model.Sources.SourcesItem;
import com.example.newsapi.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.SourcesHolder> {

    private Context mContext;
    private List<SourcesItem> mSourcesItem;
    private OnItemClick mOnItemClick;

    public SourcesAdapter(Context mContext, List<SourcesItem> mSourcesItem, OnItemClick mOnItemClick) {
        this.mContext = mContext;
        this.mSourcesItem = mSourcesItem;
        this.mOnItemClick = mOnItemClick;
    }

    @NonNull
    @Override
    public SourcesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SourcesHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.sources_item, viewGroup, false), mOnItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull SourcesHolder sourcesHolder, int i) {
        SourcesItem sourcesItem = mSourcesItem.get(i);

        sourcesHolder.name.setText(sourcesItem.getName());
        sourcesHolder.description.setText(sourcesItem.getDescription());
        sourcesHolder.url.setText(sourcesItem.getUrl());
        sourcesHolder.url.setPaintFlags(sourcesHolder.url.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        sourcesHolder.category.setText(sourcesItem.getCategory());
    }

    @Override
    public int getItemCount() {
        return mSourcesItem.size();
    }

    public class SourcesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.url)
        TextView url;
        @BindView(R.id.category)
        TextView category;
        private OnItemClick mOnItemClick;
        private final View view;

        public SourcesHolder(@NonNull View itemView, OnItemClick onItemClick) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
            this.mOnItemClick = onItemClick;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (mOnItemClick != null && position != RecyclerView.NO_POSITION) {
                mOnItemClick.onSourcesItemClickedListener(position);
            }
        }
    }

    public interface OnItemClick {
        void onSourcesItemClickedListener(int position);
    }
}
