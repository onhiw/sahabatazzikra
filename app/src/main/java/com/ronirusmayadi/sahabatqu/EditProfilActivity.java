package com.ronirusmayadi.sahabatqu;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfilActivity extends AppCompatActivity {

    private Button mSignOut;
    private Button mSave;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;
    private EditText mNama;
    private CircleImageView mFoto;
    private FirebaseAuth auth;
    private String GetUserID;
    private StorageReference storage;
    private ProgressDialog progressDialog;

    private Uri photo_location;
    private static final int photo_max = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        setTitle("Edit Profil");

        mSignOut = findViewById(R.id.btn_sign_out);
        mSave = findViewById(R.id.btn_save);
        firebaseAuth = FirebaseAuth.getInstance();
        mNama = findViewById(R.id.xusername);
        mFoto = findViewById(R.id.photo_profil);

        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        GetUserID = user.getUid();

        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(GetUserID);
        storage = FirebaseStorage.getInstance().getReference().child("PhotoUsers").child(GetUserID);

        mFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temukanFoto();
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Loading...");
                progressDialog.show();

                firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("nama").setValue(mNama.getText().toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                if (photo_location != null) {
                    StorageReference storageReference =
                            storage.child(System.currentTimeMillis() + "." +
                                    getFileExtension(photo_location));

                    storageReference.child(GetUserID).putFile(photo_location)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    String uri_photo = taskSnapshot.getDownloadUrl().toString();
                                    firebaseDatabase.getRef().child("photo").setValue(uri_photo);
                                }
                            }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            progressDialog.dismiss();
                            Intent i = new Intent(EditProfilActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                            Toast.makeText(getApplicationContext(), "Edit Data Sukses", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mNama.setText(dataSnapshot.child("nama").getValue().toString());
                Glide.with(getApplicationContext())
                        .load(dataSnapshot.child("photo")
                                .getValue().toString()).apply(new RequestOptions().error(R.drawable.akun))
                        .into(mFoto);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    private void signOut() {
        firebaseAuth.signOut();
        Intent intent = new Intent(EditProfilActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(getApplicationContext(), "Anda Keluar", Toast.LENGTH_LONG).show();
    }

    public String getFileExtension(Uri uri) {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(resolver.getType(uri));
    }

    public void temukanFoto() {
        Intent pic = new Intent();
        pic.setType("image/*");
        pic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(pic, "Select Picture"), photo_max);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == photo_max && resultCode == RESULT_OK && data != null && data.getData() != null) {
            photo_location = data.getData();
            Glide.with(this).load(photo_location)
                    .apply(new RequestOptions().error(R.drawable.akun))
                    .into(mFoto);
        }
    }
}
