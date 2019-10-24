package com.ronirusmayadi.sahabatqu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.ronirusmayadi.sahabatqu.Adapter.ImageAdapter;

public class ViewGambar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gambar);

        int imgPosition = this.getIntent().getExtras().getInt("posisi");
        ImageView iv = findViewById(R.id.viewImage);
        int getImage = ImageAdapter.gambar[imgPosition];
        iv.setImageResource(getImage);
    }
}
