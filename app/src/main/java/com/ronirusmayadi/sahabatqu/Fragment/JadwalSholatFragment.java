package com.ronirusmayadi.sahabatqu.Fragment;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.ronirusmayadi.sahabatqu.Alarm.AlarmReceiverAdzan;
import com.ronirusmayadi.sahabatqu.Api.ApiService;
import com.ronirusmayadi.sahabatqu.Api.ApiUrl;
import com.ronirusmayadi.sahabatqu.MainActivity;
import com.ronirusmayadi.sahabatqu.Model.ModelJadwal;
import com.ronirusmayadi.sahabatqu.Notification.MyAlarm;
import com.ronirusmayadi.sahabatqu.Notification.OreoNotification;
import com.ronirusmayadi.sahabatqu.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JadwalSholatFragment extends Fragment {

    private TextView tv_lokasi_value, tv_fajr_value, tv_shurooq_value,
            tv_dhuhr_value, tv_asr_value, tv_maghrib_value, tv_isha_value;
    private ProgressDialog progressDialog;
    private LinearLayout linearLayout;
    private ViewFlipper viewFlipper;
    private AnalogClock textClock;
    private String mTag;

//    private AlarmReceiverAdzan alarmReceiverAdzan;

    public JadwalSholatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_jadwal_sholat, container, false);
        if (savedInstanceState != null){

            mTag = savedInstanceState.getString("tag");
        }

        viewFlipper = view.findViewById(R.id.sunset);
        tv_lokasi_value = (TextView) view.findViewById(R.id.date);
        tv_fajr_value = (TextView) view.findViewById(R.id.subuh);
        tv_shurooq_value = (TextView) view.findViewById(R.id.shurooq);
        tv_dhuhr_value = (TextView) view.findViewById(R.id.dzuhur);
        tv_asr_value = (TextView) view.findViewById(R.id.ashar);
        tv_maghrib_value = (TextView) view.findViewById(R.id.maghrib);
        tv_isha_value = (TextView) view.findViewById(R.id.isya);

        textClock = (AnalogClock) view.findViewById(R.id.textClock1);

        linearLayout = view.findViewById(R.id.layou1);

//        alarmReceiverAdzan = new AlarmReceiverAdzan();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        int images[] = {R.drawable.sunrise, R.drawable.sunset};

        for (int i = 0; i < images.length; i++) {
            fliverImages(images[i]);
        }
        for (int image : images)
            fliverImages(image);

        getJadwal();

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tag", mTag);
    }

    private void getJadwal() {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_HTTP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ModelJadwal> call = apiService.getJadwal();

        call.enqueue(new Callback<ModelJadwal>() {

            @Override
            public void onResponse(Call<ModelJadwal> call, retrofit2.Response<ModelJadwal> response) {

                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");

                    SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");

                    Date date = new Date();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");

                    ((MainActivity) getActivity())
                            .setActionBarTitle("Daerah " + response.body().getCity());

                    tv_lokasi_value.setText(response.body().getCity() + ", " + simpleDateFormat.format(date));

                    try {
                        tv_fajr_value.setText(date24Format.format(date12Format.parse(response.body().getItems().get(0).getFajr())));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        tv_shurooq_value.setText(date24Format.format(date12Format.parse(response.body().getItems().get(0).getShurooq())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        tv_dhuhr_value.setText(date24Format.format(date12Format.parse(response.body().getItems().get(0).getDhuhr())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        tv_asr_value.setText(date24Format.format(date12Format.parse(response.body().getItems().get(0).getAsr())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        tv_maghrib_value.setText(date24Format.format(date12Format.parse(response.body().getItems().get(0).getMaghrib())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        tv_isha_value.setText(date24Format.format(date12Format.parse(response.body().getItems().get(0).getIsha())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String message = " pukul :";
                    String[] waktu = {tv_fajr_value.getText().toString(), tv_shurooq_value.getText().toString(), tv_dhuhr_value.getText().toString(),
                            tv_asr_value.getText().toString(), tv_maghrib_value.getText().toString(), tv_isha_value.getText().toString()};

                    String[] jadwal = {"Subuh", "Shuruq", "Dzuhur", "Ashar", "Magrib", "Isya"};

                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

//                    for (int i = 0; i<waktu.length ; i++){
//                        alarmReceiverAdzan.setOneTimeAlarm(getActivity().getApplicationContext(), AlarmReceiverAdzan.TYPE_ONE_TIME,
//                                simpleDateFormat2.format(date),
//                                waktu,
//                                message,
//                                jadwal);
//                    }

                } else {

                }


            }

            @Override
            public void onFailure(Call<ModelJadwal> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Sorry, please try again... No Connection..", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void fliverImages(int images) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(images);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);

    }

    private void getTime(){

    }

}
