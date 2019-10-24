package com.ronirusmayadi.sahabatqu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TransferActivity extends AppCompatActivity {

    private Button mKonfirmasi;
    private TextView mTagihan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        setTitle("Metode Pembayaran");

        mKonfirmasi = findViewById(R.id.btn_konfir);
        mTagihan = findViewById(R.id.jml_tagihan);

        Intent intent = getIntent();
        final String tagihan = intent.getStringExtra(TopUpActivity.KEY_DONASI);

        mTagihan.setText("Rp. " + tagihan);

        mKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone=6285223140369" ;
                Intent bukabrowser = new Intent(Intent. ACTION_VIEW);
                bukabrowser.setData(Uri. parse(url));
                startActivity(bukabrowser);
            }
        });
    }
}
