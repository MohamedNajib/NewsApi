package com.example.newsapi.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.newsapi.Model.Articles.ArticlesItem;
import com.example.newsapi.R;
import com.example.newsapi.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesHolder> {

    private Context mContext;
    private List<ArticlesItem> mArticlesItemList;
    private OnClickListener mOnClickListener;

    public ArticlesAdapter(Context mContext, List<ArticlesItem> articlesItemList, OnClickListener mOnClickListener) {
        this.mContext = mContext;
        this.mArticlesItemList = articlesItemList;
        this.mOnClickListener = mOnClickListener;
    }

    @NonNull
    @Override
    public ArticlesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ArticlesHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.articl_item, viewGroup, false), mOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticlesHolder holder, int i) {
        ArticlesItem articlesItem = mArticlesItemList.get(i);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();


        Glide.with(mContext)
                .load(articlesItem.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                        Target<Drawable> target, boolean isFirstResource) {
                holder.prograssLoadPhoto.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model,
                                           Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.prograssLoadPhoto.setVisibility(View.GONE);
                return false;
            }
        }).transition(DrawableTransitionOptions.withCrossFade())
          .into(holder.img);

        holder.title.setText(articlesItem.getTitle());
        holder.desc.setText(articlesItem.getDescription());
        holder.source.setText(articlesItem.getSource().getName());
        holder.time.setText(" \u2022 " + Utils.DateToTimeFormat(articlesItem.getPublishedAt()));
        holder.publishedAt.setText(Utils.DateFormat(articlesItem.getPublishedAt()));
        holder.author.setText(articlesItem.getAuthor());

    }

    @Override
    public int getItemCount() {
        return mArticlesItemList.size();
    }

    public class ArticlesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.prograss_load_photo)
        ProgressBar prograssLoadPhoto;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.publishedAt)
        TextView publishedAt;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.source)
        TextView source;
        @BindView(R.id.time)
        TextView time;

        private OnClickListener mOnClickListener;
        private final View view;

        public ArticlesHolder(@NonNull View itemView, OnClickListener onClickListener) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
            this.mOnClickListener = onClickListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (mOnClickListener != null && position != RecyclerView.NO_POSITION) {
                mOnClickListener.onArticleClicked(position);
            }
        }
    }

    public interface OnClickListener {
        void onArticleClicked(int position);
    }
}
