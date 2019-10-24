package com.ronirusmayadi.sahabatqu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ronirusmayadi.sahabatqu.Model.JadwalModel;
import com.ronirusmayadi.sahabatqu.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {

    public static final String KEY_DATE = "date_for";
    public static final String KEY_SUBUH = "fajr";
    public static final String KEY_SHUROOQ = "shurooq";
    public static final String KEY_DZUHUR = "dhuhr";
    public static final String KEY_ASHAR = "asr";
    public static final String KEY_MAGHRIB = "maghrib";
    public static final String KEY_ISYA = "isha";
    private List<JadwalModel> mJadwal;
    private Context context;

    public JadwalAdapter(List<JadwalModel> mJadwal, Context context){
        this.mJadwal = mJadwal;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_jadwal_sholat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final JadwalModel jadwalModel = mJadwal.get(position);

        Date date = new Date();

        SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");

        SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM YYYY");
        holder.mDate.setText(simpleDateFormat.format(date));
        try {
            holder.mSubuh.setText(date24Format.format(date12Format.parse(jadwalModel.getFajr())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            holder.mShurooq.setText(date24Format.format(date12Format.parse(jadwalModel.getShurooq())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            holder.mDzuhur.setText(date24Format.format(date12Format.parse(jadwalModel.getDhuhr())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            holder.mAshar.setText(date24Format.format(date12Format.parse(jadwalModel.getAsr())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            holder.mMaghrib.setText(date24Format.format(date12Format.parse(jadwalModel.getMaghrib())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            holder.mIsya.setText(date24Format.format(date12Format.parse(jadwalModel.getIsha())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mJadwal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mDate;
        private TextView mSubuh;
        private TextView mShurooq;
        private TextView mDzuhur;
        private TextView mAshar;
        private TextView mMaghrib;
        private TextView mIsya;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mDate = itemView.findViewById(R.id.date);
            mSubuh = itemView.findViewById(R.id.subuh);
            mShurooq = itemView.findViewById(R.id.shurooq);
            mDzuhur = itemView.findViewById(R.id.dzuhur);
            mAshar = itemView.findViewById(R.id.ashar);
            mMaghrib = itemView.findViewById(R.id.maghrib);
            mIsya = itemView.findViewById(R.id.isya);

        }
    }
}
