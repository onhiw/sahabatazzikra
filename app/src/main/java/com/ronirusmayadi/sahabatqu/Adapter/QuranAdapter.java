package com.ronirusmayadi.sahabatqu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ronirusmayadi.sahabatqu.Model.JadwalModel;
import com.ronirusmayadi.sahabatqu.Model.QuranModel;
import com.ronirusmayadi.sahabatqu.PlayQuranActivity;
import com.ronirusmayadi.sahabatqu.R;

import java.io.IOException;
import java.util.List;

public class QuranAdapter extends RecyclerView.Adapter<QuranAdapter.ViewHolder> {

    public static final String KEY_URL = "url";
    public static final String KEY_NAMA = "nama";

    private List<QuranModel> mQuran;
    private Context context;


    public QuranAdapter(List<QuranModel> mQuran, Context context) {
        this.mQuran = mQuran;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_quran, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final QuranModel quranModel = mQuran.get(position);

        holder.mNama.setText(quranModel.getNama());
        holder.mArti.setText(quranModel.getArti());
        holder.mAyat.setText("("+quranModel.getAyat() + ")");
        holder.mAsma.setText(quranModel.getAsma());
        holder.mNomor.setText(quranModel.getNomor());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlayQuranActivity.class);
                intent.putExtra(KEY_URL, quranModel.getAudio());
                intent.putExtra(KEY_NAMA, quranModel.getNama());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mQuran.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mNama;
        private TextView mArti;
        private TextView mAsma;
        private TextView mAyat;
        private TextView mNomor;
        private LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mNama = itemView.findViewById(R.id.nama);
            mArti = itemView.findViewById(R.id.arti);
            mAsma = itemView.findViewById(R.id.asma);
            mAyat = itemView.findViewById(R.id.jml_ayat);
            mNomor = itemView.findViewById(R.id.nomor);
            linearLayout = itemView.findViewById(R.id.playquran);

        }


    }
}
