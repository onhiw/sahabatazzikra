package com.ronirusmayadi.sahabatqu;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.ronirusmayadi.sahabatqu.Adapter.ImageAdapter;

public class GalleryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    protected GridView TampilanGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        setupWindowAnimations();
        getSupportActionBar().setTitle("Gallery Foto");
        TampilanGrid = findViewById(R.id.gridview);
        //Mengset/Menerapkan adapter pada GridView
        TampilanGrid.setAdapter(new ImageAdapter(this));

        //Membuat Listener untuk menangani kejadian saat salah satu item di dalam GrdiView diklik
        TampilanGrid.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent nextPage = new Intent(this, ViewGambar.class);
        Bundle bundle = new Bundle();
        //Menyimpan nilai dari padameter position kedalah key posisi.
        //Yang akan dikirimkan pada Activiy ViewGambar
        bundle.putInt("posisi", position);
        nextPage.putExtras(bundle);
        startActivity(nextPage);

    }

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(fade);
        }
    }
}
