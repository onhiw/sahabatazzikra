package com.ronirusmayadi.sahabatqu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ronirusmayadi.sahabatqu.Adapter.AdapterGallery;
import com.ronirusmayadi.sahabatqu.Adapter.QuranAdapter;

import javax.microedition.khronos.opengles.GL;

public class InfoProgramActivity extends AppCompatActivity {

    private ImageView mImage;
    private TextView mOverview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_program);

        setTitle("Informasi Program");

        mImage = findViewById(R.id.image);
        mOverview = findViewById(R.id.overview);

        Intent intent = getIntent();
        int gambar = intent.getExtras().getInt("param");
        String overview = intent.getStringExtra("title");

        Glide.with(this).load(gambar)
                .into(mImage);

        mOverview.setText(overview);
    }
}
