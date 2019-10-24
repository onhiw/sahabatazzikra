package com.ronirusmayadi.sahabatqu;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.ronirusmayadi.sahabatqu.Model.TransaksiModel;
import com.ronirusmayadi.sahabatqu.Notification.OreoNotification;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TopUpActivity extends AppCompatActivity {

    public static final String KEY_DONASI = "donasi";

    private TextView mNama, mSaldo;
    private EditText topUp;

    private Button mSend;

    private FirebaseAuth auth;
    private String GetUserID;
    private StorageReference storage;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        setTitle("Top Up Infaq");

        mNama = findViewById(R.id.namaakun);
        mSaldo = findViewById(R.id.saldo);

        topUp = findViewById(R.id.inputsaldo);
        mSend = findViewById(R.id.btn_topup);

        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        GetUserID = user.getUid();

        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(GetUserID);

        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Locale localeID = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                mNama.setText(dataSnapshot.child("nama").getValue().toString());
                mSaldo.setText(formatRupiah.format(Long.valueOf(dataSnapshot.child("jmlh_saldo").getValue().toString())));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    sendOreoNotification();
                } else {
                    sendNotification();
                }
                topup();
            }
        });

    }

    private void topup(){
        final String jml_topup = topUp.getText().toString().trim();
        String value = "Menunggu Konfirmasi";

        if (TextUtils.isEmpty(jml_topup)){
            Toast.makeText(getApplicationContext(), "Masukan Nominal Top Up Anda", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("infosaldo").child(GetUserID);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
        final String id = databaseReference.push().getKey();

        TransaksiModel transaksiModel = new TransaksiModel();

        transaksiModel.setTotal_donasi(jml_topup.trim());
        transaksiModel.setStatus(value);
        transaksiModel.setIdMember(id);
        transaksiModel.setTgl(simpleDateFormat.format(date));

        databaseReference.child(id).setValue(transaksiModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Buat Top Up Sukses", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(TopUpActivity.this, TransferActivity.class);
                intent.putExtra(KEY_DONASI, jml_topup);
                startActivity(intent);
                finish();
                progressDialog.dismiss();
                sendNotification();
            }
        });
    }

    private void sendOreoNotification() {

        PendingIntent pendingIntent = null;
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        OreoNotification oreoNotification = new OreoNotification(getApplicationContext());
        Notification.Builder builder = oreoNotification.getOreoNotification(pendingIntent,
                defaultSound);

        oreoNotification.getManager().notify(1, builder.build());

    }
    private void sendNotification() {
        PendingIntent pendingIntent = null;

        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.icon_round)
                .setContentTitle("Transfer Ke No. Rek BCA")
                .setContentText("Silahkan transfer ke nomor rekening BCA a/n Andri Handriyana")
                .setAutoCancel(true)
                .setSound(defaultSound)
                .setContentIntent(pendingIntent);
        NotificationManager noti = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        noti.notify(1, builder.build());
    }
}
