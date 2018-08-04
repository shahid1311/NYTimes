package com.tliknowledge.nytimes.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.tliknowledge.nytimes.R;
import com.tliknowledge.nytimes.constants.APIConstants;
import com.tliknowledge.nytimes.constants.AppConstants;
import com.tliknowledge.nytimes.listeners.CustomSingleChoiceDialogListener;
import com.tliknowledge.nytimes.listeners.RetrofitListener;
import com.tliknowledge.nytimes.model.Article;
import com.tliknowledge.nytimes.model.ErrorModel;
import com.tliknowledge.nytimes.model.Result;
import com.tliknowledge.nytimes.modules.logger.Logger;
import com.tliknowledge.nytimes.modules.retrofitLibrary.RetroApiProvider;
import com.tliknowledge.nytimes.ui.adapters.ArticlesAdapter;
import com.tliknowledge.nytimes.ui.dialoges.DialogUtil;
import com.tliknowledge.nytimes.utils.NetworkUtil;

public class ArticleListActivity extends AppCompatActivity implements RetrofitListener,
        ArticlesAdapter.ArticleSelectedListener, CustomSingleChoiceDialogListener {
    private RetroApiProvider retroApiProvider;
    private ArticlesAdapter articlesAdapter;
    private SwipeRefreshLayout srArticles;
    private Logger logger;
    private RecyclerView rvArticlesList;
    private String selectedDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        setToolbar(getString(R.string.ny_times_most_popular));

        initData();
        initViews();
        getArticles(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_articles_list, menu);
        invalidateOptionsMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                //Show Dialog for displaying Filter Options
                DialogUtil dialogUtil = new DialogUtil(ArticleListActivity.this);
                dialogUtil.selectPeriodValue(selectedDays, this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initData() {
        retroApiProvider = new RetroApiProvider(this, this);
        logger = new Logger(ArticleListActivity.class);
        selectedDays = "7";
    }

    private void initViews() {
        rvArticlesList = (RecyclerView) findViewById(R.id.rv_articles);
        articlesAdapter = new ArticlesAdapter(this, null, this);
        rvArticlesList.setAdapter(articlesAdapter);
        rvArticlesList.setLayoutManager(new LinearLayoutManager(this));
        rvArticlesList.setHasFixedSize(true);

        srArticles = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_articles);
        srArticles.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getArticles(false);
            }
        });
    }

    private void getArticles(boolean showLoader) {
        if (NetworkUtil.isNetworkAvailable(this)) {
            if (showLoader) {
                srArticles.setRefreshing(true);
            }
            retroApiProvider.getMostViewedArticles(this, selectedDays, "all-sections");
        } else {
            showEmptyView();
            showSnackBarMessage(getString(R.string.networkProbTryAgain));
            srArticles.setRefreshing(false);
        }
    }

    @Override
    public void onResponseSuccess(String responseBodyString, int flag) {
        switch (flag) {
            case APIConstants.APIFlags.GET_MOST_VIEWED_ARTICLES:
                try {
                    Gson gson = new Gson();
                    Article articleResponse = gson.fromJson(responseBodyString, Article.class);
                    if (articleResponse.getResults() != null && articleResponse.getResults().size() > 0) {
                        articlesAdapter.setmObjects(articleResponse.getResults());
                        articlesAdapter.notifyDataSetChanged();
                        hideEmptyView();
                    } else {
                        showEmptyView();
                    }
                } catch (Exception e) {
                    logger.debug("Exception in parsing Patient Search Result");
                    logger.error(e);
                    showEmptyView();
                }

                srArticles.setRefreshing(false);
                break;
        }
    }

    @Override
    public void onResponseError(ErrorModel errorModel, Throwable throwable, int apiFlag) {
        switch (apiFlag) {
            case APIConstants.APIFlags.GET_MOST_VIEWED_ARTICLES:
                showSnackBarMessage(errorModel.getErrors().get(0));
                showEmptyView();
                srArticles.setRefreshing(false);
                break;
        }
    }

    @Override
    public void onArticleSelected(Result article) {
        //Call Next Activity
        Intent articleIntent = new Intent(this, ArticleDetailsActivity.class);
        articleIntent.putExtra(AppConstants.IntentData.ARTICLE, article);
        startActivity(articleIntent);
    }

    private void showEmptyView() {
        findViewById(R.id.tv_empty_view).setVisibility(View.VISIBLE);
        rvArticlesList.setVisibility(View.GONE);
    }

    private void hideEmptyView() {
        findViewById(R.id.tv_empty_view).setVisibility(View.GONE);
        rvArticlesList.setVisibility(View.VISIBLE);

    }

    private void showSnackBarMessage(String message) {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(String selectedItem) {
        if(!selectedDays.equalsIgnoreCase(selectedItem)){
            selectedDays= selectedItem;
            getArticles(true);
        }
    }

    public void setToolbar(String title){
        /* Toolbar */
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle(title);
            // Back icon
            actionBar.setDisplayHomeAsUpEnabled(false);

            // Show
            actionBar.show();
        }

    } // Toolbar
}
