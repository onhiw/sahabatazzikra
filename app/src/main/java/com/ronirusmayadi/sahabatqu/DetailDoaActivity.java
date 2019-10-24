package com.ronirusmayadi.sahabatqu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ronirusmayadi.sahabatqu.Adapter.DoaAdapter;

public class DetailDoaActivity extends AppCompatActivity {

    private TextView mArab, mArti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_doa);

        mArab = findViewById(R.id.arab);
        mArti = findViewById(R.id.arti);

        Intent intent = getIntent();
        final String nama = intent.getStringExtra(DoaAdapter.KEY_NAMA);
        final String arab = intent.getStringExtra(DoaAdapter.KEY_ARAB);
        final String arti = intent.getStringExtra(DoaAdapter.KEY_ARTI);

        mArab.setText(arab);
        mArti.setText(arti);

        setTitle(nama);

    }
}
