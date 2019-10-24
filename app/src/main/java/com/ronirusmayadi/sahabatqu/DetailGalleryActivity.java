package com.ronirusmayadi.sahabatqu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ronirusmayadi.sahabatqu.Adapter.AdapterGallery;

public class DetailGalleryActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gallery);

        imageView =findViewById(R.id.images);

        Intent intent = getIntent();
        int gambar = intent.getExtras().getInt(AdapterGallery.KEY_FOTO);

        Glide.with(this).load(gambar)
                .into(imageView);
    }
}
