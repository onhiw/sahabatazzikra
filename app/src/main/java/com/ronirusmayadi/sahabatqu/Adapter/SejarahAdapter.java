package com.ronirusmayadi.sahabatqu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ronirusmayadi.sahabatqu.DetailSejarahActivity;
import com.ronirusmayadi.sahabatqu.Model.SejarahModel;
import com.ronirusmayadi.sahabatqu.R;

import java.util.List;

public class SejarahAdapter extends RecyclerView.Adapter<SejarahAdapter.ViewHolder> {

    public static final String KEY_TITLE = "judul";
    public static final String KEY_LINK = "link";

    private List<SejarahModel> mSejarah;
    private Context context;

    public SejarahAdapter(List<SejarahModel> mSejarah, Context context){
        this.mSejarah = mSejarah;
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

        final SejarahModel sejarahModel = mSejarah.get(position);

        holder.mJudul.setText(sejarahModel.getJudul());
        holder.mPublish.setText(sejarahModel.getPublish());
        holder.mKategori.setText("Sejarah Islam");

        Glide.with(context).load(sejarahModel.getGambar())
                .into(holder.mImage);

        holder.openWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailSejarahActivity.class);
                intent.putExtra(KEY_TITLE, sejarahModel.getJudul());
                intent.putExtra(KEY_LINK, sejarahModel.getLink());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSejarah.size();
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
