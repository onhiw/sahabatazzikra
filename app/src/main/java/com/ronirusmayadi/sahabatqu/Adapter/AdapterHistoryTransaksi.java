package com.ronirusmayadi.sahabatqu.Adapter;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.ronirusmayadi.sahabatqu.DetailHistoryTransaksi;
import com.ronirusmayadi.sahabatqu.Model.TransaksiModel;
import com.ronirusmayadi.sahabatqu.R;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterHistoryTransaksi extends RecyclerView.Adapter<AdapterHistoryTransaksi.ViewHolder> {

    public static final String KEY_SALDO = "saldo";
    public static final String KEY_STATUS = "status";
    private List<TransaksiModel> transaksiModels;
    private Context context;
    private FirebaseAuth auth;
    private String GetUserID;

    public AdapterHistoryTransaksi(List<TransaksiModel> transaksiModels, Context context){
        this.transaksiModels = transaksiModels;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_transaksi, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final TransaksiModel transaksiModel = transaksiModels.get(position);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.mTgl.setText(transaksiModel.getTgl());
        holder.mTagihan.setText(formatRupiah.format(Long.valueOf(transaksiModel.getTotal_donasi())));
        if (transaksiModel.getStatus().equals("Selesai")){
            holder.mStatus.setText(transaksiModel.getStatus());
            holder.mStatus.setTextColor(R.color.colorPrimary);
            holder.mIcon.setImageResource(R.drawable.ic_check_circle);
        }

        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailHistoryTransaksi.class);
                intent.putExtra(KEY_SALDO, transaksiModel.getTotal_donasi());
                intent.putExtra(KEY_STATUS, transaksiModel.getStatus());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (transaksiModels != null){
            return transaksiModels.size();
        } else {
            Toast.makeText(context, "Belum Ada History Transaksi", Toast.LENGTH_SHORT).show();
            return  -1;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTgl, mTagihan, mStatus;
        private ImageView mIcon;
        private LinearLayout mLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTgl = itemView.findViewById(R.id.tgl);
            mTagihan = itemView.findViewById(R.id.total_donasi);
            mStatus = itemView.findViewById(R.id.status);
            mIcon = itemView.findViewById(R.id.icon_confirm);

            mLayout = itemView.findViewById(R.id.transaksi);
        }
    }
}
