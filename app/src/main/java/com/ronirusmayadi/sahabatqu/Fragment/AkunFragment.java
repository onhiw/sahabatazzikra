package com.ronirusmayadi.sahabatqu.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ronirusmayadi.sahabatqu.AboutActivity;
import com.ronirusmayadi.sahabatqu.EditProfilActivity;
import com.ronirusmayadi.sahabatqu.LoginActivity;
import com.ronirusmayadi.sahabatqu.MainActivity;
import com.ronirusmayadi.sahabatqu.Model.User;
import com.ronirusmayadi.sahabatqu.R;
import com.ronirusmayadi.sahabatqu.SettingActivity;
import com.ronirusmayadi.sahabatqu.TopUpActivity;

import java.text.NumberFormat;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class AkunFragment extends Fragment {

    private TextView mNama, mJmlSaldo;
    private CircleImageView mImage;
    private Button mTopUp;
    private LinearLayout mSetting;
    private LinearLayout mAbout;

    private DatabaseReference reference;
    private FirebaseAuth auth;
    private String GetUserID;
    private ProgressBar mProgressBar;

    private String mTag;

    public AkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_akun, container, false);
        getActivity().setTitle("Akun Saya");
        setHasOptionsMenu(true);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        if (savedInstanceState != null) {

            mTag = savedInstanceState.getString("tag");
        }

        mNama = view.findViewById(R.id.nama);
        mJmlSaldo = view.findViewById(R.id.jml_saldo);
        mImage = view.findViewById(R.id.img_profil);
        mTopUp = view.findViewById(R.id.topup);
        mSetting = view.findViewById(R.id.setting);
        mAbout = view.findViewById(R.id.about);

        mProgressBar = view.findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            GetUserID = user.getUid();

            mProgressBar.setVisibility(View.VISIBLE);

            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(GetUserID);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    User user = dataSnapshot.getValue(User.class);
                    mNama.setText(user.getNama());
                    Locale localeID = new Locale("in", "ID");
                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                    mJmlSaldo.setText(formatRupiah.format(Long.valueOf(user.getJmlh_saldo())));
                    Glide.with(getActivity().getApplicationContext())
                            .load(dataSnapshot.child("photo")
                                    .getValue().toString()).apply(new RequestOptions().placeholder(R.drawable.akun))
                            .into(mImage);
                    mProgressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mTopUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), TopUpActivity.class);
                    startActivity(intent);
                }
            });

            mSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), EditProfilActivity.class);
                    startActivity(intent);
                }
            });

            mAbout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AboutActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tag", mTag);
    }

}
