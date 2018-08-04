package com.tliknowledge.nytimes.ui.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tliknowledge.nytimes.R;
import com.tliknowledge.nytimes.listeners.OnSingleClickListener;
import com.tliknowledge.nytimes.model.MediaMetadata;
import com.tliknowledge.nytimes.model.Result;
import com.tliknowledge.nytimes.modules.image.PicassoImageUtil;

import java.util.ArrayList;

public class ArticlesAdapter extends
        BaseRecyclerAdapter<Result, RecyclerView.ViewHolder> {
    private Context mContext;
    private PicassoImageUtil picassoImageUtil;
    private ArticleSelectedListener articleSelectedListener;

    public ArticlesAdapter(Context mContext, ArrayList<Result> articleArrayList,
                           ArticleSelectedListener articleSelectedListener) {
        super(articleArrayList);
        this.mContext = mContext;
        this.picassoImageUtil = new PicassoImageUtil(mContext);
        this.articleSelectedListener = articleSelectedListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_article_view, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        final SimpleViewHolder holder = (SimpleViewHolder) viewHolder;

        final Result article = getItem(holder.getAdapterPosition());
        if (!TextUtils.isEmpty(article.getTitle())) {
            holder.tvArticleHeader.setVisibility(View.VISIBLE);
            holder.tvArticleHeader.setText(article.getTitle());
        } else {
            holder.tvArticleHeader.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(article.getByline())) {
            holder.tvAuthor.setVisibility(View.VISIBLE);
            holder.tvAuthor.setText(article.getByline());
        } else {
            holder.tvAuthor.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(article.getPublishedDate())) {
            holder.tvDate.setVisibility(View.VISIBLE);
            holder.tvDate.setText(article.getPublishedDate());
        } else {
            holder.tvDate.setVisibility(View.GONE);
        }

        if (article.getMedia() != null && article.getMedia().size() > 0) {
            MediaMetadata thumbnailMetaData = article.getMedia().get(0).getThumbnailMetaData(article.getMedia().get(0).getMediaMetadata());
            if (thumbnailMetaData != null) {
                String thumbnailUrl = thumbnailMetaData.getUrl().replace("\\", "");
                picassoImageUtil.setImageByLinkAndTransformToCircle(thumbnailUrl,
                        holder.ivArticleThumbnail, R.drawable.ic_placeholder);
            } else {
                holder.ivArticleThumbnail.setImageResource(R.drawable.ic_placeholder);
            }
        } else {
            holder.ivArticleThumbnail.setImageResource(R.drawable.ic_placeholder);
        }

        holder.cvArticle.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (articleSelectedListener != null) {
                    articleSelectedListener.onArticleSelected(article);
                }
            }
        });
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        private TextView tvArticleHeader, tvAuthor, tvDate;
        private ImageView ivArticleThumbnail;
        private CardView cvArticle;

        public SimpleViewHolder(View view) {
            super(view);
            ivArticleThumbnail = (ImageView) view.findViewById(R.id.iv_article_cover);
            tvArticleHeader = (TextView) view.findViewById(R.id.tv_article_title);
            tvAuthor = (TextView) view.findViewById(R.id.tv_author_name);
            tvDate = (TextView) view.findViewById(R.id.tv_published_date);
            cvArticle = (CardView) view.findViewById(R.id.cv_article);
        }
    }

    public interface ArticleSelectedListener {
        void onArticleSelected(Result article);
    }

}