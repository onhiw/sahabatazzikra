package com.ronirusmayadi.sahabatqu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ronirusmayadi.sahabatqu.DetailSejarahActivity;
import com.ronirusmayadi.sahabatqu.Model.ArtikelModel;
import com.ronirusmayadi.sahabatqu.Model.SejarahModel;
import com.ronirusmayadi.sahabatqu.R;

import java.util.List;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.ViewHolder> {

    public static final String KEY_TITLE = "judul";
    public static final String KEY_LINK = "link";

    private List<ArtikelModel> mArtikel;
    private Context context;

    public ArtikelAdapter(List<ArtikelModel> mArtikel, Context context){
        this.mArtikel = mArtikel;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_sejarah, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ArtikelModel artikelModel = mArtikel.get(position);

        holder.mJudul.setText(artikelModel.getJudul());
        holder.mPublish.setText(artikelModel.getPublish());
        holder.mKategori.setText("Artikel Islam");

        Glide.with(context).load(artikelModel.getGambar())
                .into(holder.mImage);

        holder.openWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailSejarahActivity.class);
                intent.putExtra(KEY_TITLE, artikelModel.getJudul());
                intent.putExtra(KEY_LINK, artikelModel.getLink());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArtikel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mJudul, mPublish, mKategori;
        private ConstraintLayout openWeb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.image_news);
            mJudul = itemView.findViewById(R.id.judul);
            mPublish = itemView.findViewById(R.id.publish);
            mKategori = itemView.findViewById(R.id.kategori);
            openWeb = itemView.findViewById(R.id.gotoweb);
        }
    }
}

