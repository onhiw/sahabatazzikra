package com.ronirusmayadi.sahabatqu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import java.net.URL;

public class AboutActivity extends AppCompatActivity {

    private ImageView mFb, mIg, mWa;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setTitle("Tentang Kami");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFb = findViewById(R.id.fb);
        mIg = findViewById(R.id.ig);
        mWa = findViewById(R.id.wa);

        mFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/sahabat.azzikra/" ;
                Intent bukabrowser = new Intent(Intent. ACTION_VIEW);
                bukabrowser.setData(Uri. parse(url));
                startActivity(bukabrowser);
            }
        });

        mIg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.instagram.com/sahabat.azzikra/" ;
                Intent bukabrowser = new Intent(Intent. ACTION_VIEW);
                bukabrowser.setData(Uri. parse(url));
                startActivity(bukabrowser);
            }
        });

        mWa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone=6285223140369" ;
                Intent bukabrowser = new Intent(Intent. ACTION_VIEW);
                bukabrowser.setData(Uri. parse(url));
                startActivity(bukabrowser);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
