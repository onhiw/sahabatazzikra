package com.ronirusmayadi.sahabatqu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ronirusmayadi.sahabatqu.DetailGalleryActivity;
import com.ronirusmayadi.sahabatqu.Model.ModelGallery;
import com.ronirusmayadi.sahabatqu.PlayQuranActivity;
import com.ronirusmayadi.sahabatqu.R;

import java.util.List;

public class AdapterGallery extends RecyclerView.Adapter<AdapterGallery.ViewHolder> {

    public static final String KEY_FOTO = "foto";

    private List<ModelGallery> modelGalleries;
    private Context context;

    public AdapterGallery(List<ModelGallery> modelGalleries, Context context){
        this.modelGalleries = modelGalleries;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final ModelGallery modelGallery = modelGalleries.get(position);

        Glide.with(context).load(modelGallery.getGambar())
                .into(viewHolder.imageView);

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailGalleryActivity.class);
                intent.putExtra(KEY_FOTO, modelGallery.getGambar());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelGalleries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_gallery);
        }
    }
}
