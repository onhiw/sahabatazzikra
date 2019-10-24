package com.ronirusmayadi.sahabatqu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LupaPassword extends AppCompatActivity {

    private TextView mBack;
    private EditText mEmail;
    private Button mBtnReset;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        mBack = findViewById(R.id.btn_back);
        mEmail = findViewById(R.id.xusername);
        mBtnReset = findViewById(R.id.btn_reset);

        progressDialog = new ProgressDialog(this);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LupaPassword.this, LoginActivity.class);
                startActivity(i);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Masukkan email Anda!", Toast.LENGTH_LONG).show();
                    return;
                }

                progressDialog.setMessage("Please Wait...");
                progressDialog.show();

                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Periksa email untuk mengatur ulang kata sandi Anda!", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Gagal mengirim ulang kata sandi email!", Toast.LENGTH_LONG).show();
                                }
                                mEmail.setText("");

                            }
                        });
            }
        });
    }
}
