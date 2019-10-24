package com.ronirusmayadi.sahabatqu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ronirusmayadi.sahabatqu.DetailDoaActivity;
import com.ronirusmayadi.sahabatqu.Model.DoaModel;
import com.ronirusmayadi.sahabatqu.Model.QuranModel;
import com.ronirusmayadi.sahabatqu.R;

import org.w3c.dom.Text;

import java.util.List;

public class DoaAdapter extends RecyclerView.Adapter<DoaAdapter.ViewHolder> {

    public static final String KEY_ARTI = "arti";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_ARAB = "arab";

    private List<DoaModel> mDoa;
    private Context context;

    public DoaAdapter(List<DoaModel> mDoa, Context context){
        this.mDoa = mDoa;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_doa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final DoaModel doaModel = mDoa.get(position);
        holder.mNomor.setText(doaModel.getNomor());
        holder.mNama.setText(doaModel.getNama());

        holder.mLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailDoaActivity.class);
                intent.putExtra(KEY_NAMA, doaModel.getNama());
                intent.putExtra(KEY_ARAB, doaModel.getArab());
                intent.putExtra(KEY_ARTI, doaModel.getArti());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDoa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mNama;
        private TextView mNomor;
        private LinearLayout mLihat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLihat = itemView.findViewById(R.id.lihat);
            mNama = itemView.findViewById(R.id.nama);
            mNomor = itemView.findViewById(R.id.nomor);
        }

    }
}
