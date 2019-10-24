package com.ronirusmayadi.sahabatqu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ronirusmayadi.sahabatqu.Adapter.AdapterHistoryTransaksi;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailHistoryTransaksi extends AppCompatActivity {

    private TextView mTagihan;
    private TextView mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history_transaksi);

        setTitle("Detail History");

        mTagihan = findViewById(R.id.jml_tagihan);
        mButton = findViewById(R.id.btn_konfir);

        Intent intent = getIntent();
        final String tagihan = intent.getStringExtra(AdapterHistoryTransaksi.KEY_SALDO);
        final String status = intent.getStringExtra(AdapterHistoryTransaksi.KEY_STATUS);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        mTagihan.setText(formatRupiah.format(Long.valueOf(tagihan)));

        if (status.equals("Selesai")){
            mButton.setText("Sudah Ditransfer");
            mButton.setBackgroundResource(R.drawable.bg_btn_topup);
            mButton.setEnabled(false);
        }

        mButton.setOnClickListener(new View.OnClickListener() {
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
