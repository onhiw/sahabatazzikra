package com.ronirusmayadi.sahabatqu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ronirusmayadi.sahabatqu.DetailTeknologiActivity;
import com.ronirusmayadi.sahabatqu.Model.BeritaModel;
import com.ronirusmayadi.sahabatqu.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.ViewHolder> {

    public static final String KEY_TITLE = "title";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_TIME = "time";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_URL = "image";
    public static final String KEY_URLWEB = "web";

    private List<BeritaModel> mBerita;
    private Context context;

    public  BeritaAdapter(List<BeritaModel> mBerita, Context context){
        this.mBerita = mBerita;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final BeritaModel beritaModel = mBerita.get(position);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        holder.title_news.setText(beritaModel.getTitle_news());
        holder.author.setText(beritaModel.getAuthor());

        holder.time.setText(simpleDateFormat.format(date));

        Glide.with(context).load(beritaModel.getLink_img())
                .into(holder.link_img);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsDetail = new Intent(v.getContext(), DetailTeknologiActivity.class);
                newsDetail.putExtra(KEY_TITLE, beritaModel.getTitle_news());
//                newsDetail.putExtra(KEY_AUTHOR,news.getAuthor());
//                newsDetail.putExtra(KEY_TIME, news.getTime());
//                newsDetail.putExtra(KEY_DESCRIPTION, news.getDescription());
//                newsDetail.putExtra(KEY_URL,news.getLink_img());
                newsDetail.putExtra(KEY_URLWEB,beritaModel.getLink());
                v.getContext().startActivity(newsDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBerita.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title_news;
        public TextView author;
        public TextView time;
        public TextView description_news;
        public ImageView link_img;
        public LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_news = itemView.findViewById(R.id.title_news);
            author = itemView.findViewById(R.id.author);
            time = itemView.findViewById(R.id.publish);
            link_img = itemView.findViewById(R.id.img_news);
            title_news = itemView.findViewById(R.id.title_news);
            linearLayout = itemView.findViewById(R.id.layout_news);
        }
    }
}
