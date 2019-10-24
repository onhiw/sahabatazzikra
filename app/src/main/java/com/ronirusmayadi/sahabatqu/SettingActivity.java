package com.ronirusmayadi.sahabatqu;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.ronirusmayadi.sahabatqu.Alarm.AlarmReceiverAdzan;
import com.ronirusmayadi.sahabatqu.Api.ApiService;
import com.ronirusmayadi.sahabatqu.Api.ApiUrl;
import com.ronirusmayadi.sahabatqu.Fragment.SetFragment;
import com.ronirusmayadi.sahabatqu.Model.ModelJadwal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingActivity extends AppCompatActivity {

    private Switch aSwitch;
    private AlarmReceiverAdzan alarmReceiverAdzan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Pengaturan");

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(android.R.id.content, new SetFragment()).commit();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        aSwitch = findViewById(R.id.mySwitch);
//        alarmReceiverAdzan = new AlarmReceiverAdzan();
//
//        aSwitch.setChecked(false);
//
//        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    getData();
//                } else {
//                }
//            }
//        });
//
//        if (aSwitch.isChecked()) {
//            getData();
//        } else {
//
//        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
//    private void getData() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ApiUrl.URL_ROOT_HTTP)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiService apiService = retrofit.create(ApiService.class);
//        Call<ModelJadwal> call = apiService.getJadwal();
//
//        call.enqueue(new Callback<ModelJadwal>() {
//            @Override
//            public void onResponse(Call<ModelJadwal> call, retrofit2.Response<ModelJadwal> response) {
//                if (response.isSuccessful()) {
//                    SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
//
//                    SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");
//
//                    Date date = new Date();
//
//                    String mSubuh = "";
//                    try {
//                        mSubuh = date24Format.format(date12Format.parse(response.body().getItems().get(0).getFajr()));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    String mShuruq = "";
//                    try {
//                        mShuruq = date24Format.format(date12Format.parse(response.body().getItems().get(0).getShurooq()));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    String mDzuhur = "";
//                    try {
//                        mDzuhur = date24Format.format(date12Format.parse(response.body().getItems().get(0).getDhuhr()));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    String mAshar = "";
//                    try {
//                        mAshar = date24Format.format(date12Format.parse(response.body().getItems().get(0).getAsr()));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    String mMagrib = "";
//                    try {
//                        mMagrib = date24Format.format(date12Format.parse(response.body().getItems().get(0).getMaghrib()));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    String mIsya = "";
//                    try {
//                        mIsya = date24Format.format(date12Format.parse(response.body().getItems().get(0).getIsha()));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//
//                    String message = " pukul :";
//                    String[] waktu = {mSubuh, mShuruq, mDzuhur, mAshar, mMagrib, mIsya};
//
//                    String[] jadwal = {"Subuh", "Shuruq", "Dzuhur", "Ashar", "Magrib", "Isya"};
//
//                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
//                    alarmReceiverAdzan.setOneTimeAlarm(getApplicationContext(), AlarmReceiverAdzan.TYPE_ONE_TIME,
//                            simpleDateFormat2.format(date),
//                            waktu,
//                            message,
//                            jadwal);
//
//
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ModelJadwal> call, Throwable t) {
//
//            }
//        });
//    }
}
