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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ronirusmayadi.sahabatqu.Model.User;

public class DaftarActivity extends AppCompatActivity {

    private LinearLayout mBack;
    private EditText mUsername, mPassword, mEmail;
    private Button mSignUp;
    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    private String value = "0";
    private String value2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        mBack = findViewById(R.id.btn_back);
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mEmail = findViewById(R.id.email_address);
        mSignUp = findViewById(R.id.btn_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProsesDaftar();
            }
        });
    }

    private void ProsesDaftar(){
        final String email = mEmail.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String username = mUsername.getText().toString().trim();


        if (TextUtils.isEmpty(username)){
            Toast.makeText(getApplicationContext(), "Masukan Nama Anda", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Masukan Alamat Email Anda", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Masukan Password Baru", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User m_user = new User(
                            username,
                            email,
                            value2,
                            value
                    );
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(m_user)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getApplicationContext(), "Proses Daftar Sukses", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(DaftarActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                } else {
                    Toast.makeText(getApplicationContext(), "Proses Daftar Error", Toast.LENGTH_LONG).show();
                    mUsername.setText("");
                    mEmail.setText("");
                    mPassword.setText("");
                }
                progressDialog.dismiss();
            }
        });
    }
}
