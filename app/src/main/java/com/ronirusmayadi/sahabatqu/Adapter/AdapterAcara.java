package com.ronirusmayadi.sahabatqu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ronirusmayadi.sahabatqu.Model.ModelAcara;
import com.ronirusmayadi.sahabatqu.R;

import java.util.List;

public class AdapterAcara extends RecyclerView.Adapter<AdapterAcara.ViewHolder> {

    private List<ModelAcara> modelAcaras;
    private Context context;

    public AdapterAcara(List<ModelAcara> modelAcaras, Context context){
        this.modelAcaras = modelAcaras;
        this.context = context;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_acara, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final ModelAcara modelAcara = modelAcaras.get(position);

        Glide.with(context).load(modelAcara.getGambar())
                .into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return modelAcaras.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_acara);
        }
    }
}
