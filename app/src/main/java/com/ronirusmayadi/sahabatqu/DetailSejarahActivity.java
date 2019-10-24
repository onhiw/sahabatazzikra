package com.ronirusmayadi.sahabatqu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ronirusmayadi.sahabatqu.Adapter.SejarahAdapter;

public class DetailSejarahActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sejarah);
        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progressBar);
        loadData();
    }

    private class MyBrowser extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void loadData(){
        Intent intent = getIntent();
        final String mLink = intent.getStringExtra(SejarahAdapter.KEY_LINK);
        final String mTitle = intent.getStringExtra(SejarahAdapter.KEY_TITLE);
        setTitle(mTitle);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new DetailSejarahActivity.MyBrowser());
        webView.loadUrl(mLink);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
    }
}
