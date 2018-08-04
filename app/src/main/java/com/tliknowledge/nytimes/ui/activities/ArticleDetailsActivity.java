package com.tliknowledge.nytimes.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tliknowledge.nytimes.R;
import com.tliknowledge.nytimes.constants.AppConstants;
import com.tliknowledge.nytimes.model.Result;

public class ArticleDetailsActivity extends AppCompatActivity {
    private WebView webView;
    private boolean showLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_web_view);
        showLoading = true;
        initViews();
        loadWebUrl();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_web_view, menu);
        invalidateOptionsMenu();
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem loadingItem = menu.findItem(R.id.itemLoading);
        if(showLoading){
            loadingItem.setVisible(true);
        }else{
            loadingItem.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        webView = (WebView) findViewById(R.id.web_view);
        if (webView != null) {
            webView.getSettings().setUseWideViewPort(true);
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    // Turn it on
                    showLoading = true;
                    invalidateOptionsMenu();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    // Turn it of
                    showLoading = false;
                    invalidateOptionsMenu();
                }
            });
        }
    }

    private void loadWebUrl() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(AppConstants.IntentData.ARTICLE)) {
                Result article = intent.getParcelableExtra(AppConstants.IntentData.ARTICLE);
                setToolbar(article.getTitle());
                String url = article.getUrl();
                if(!TextUtils.isEmpty(url)){
                    url = url.replace("\\", "");
                    webView.loadUrl(url);
                }
            }
        }
    }

    public void setToolbar(String title){
        /* Toolbar */
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle(title);
            // Back icon
            actionBar.setDisplayHomeAsUpEnabled(true);

            // Show
            actionBar.show();
        }

    } // Toolbar
}
