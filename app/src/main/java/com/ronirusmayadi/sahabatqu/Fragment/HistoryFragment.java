package com.ronirusmayadi.sahabatqu.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ronirusmayadi.sahabatqu.Adapter.AdapterHistoryTransaksi;
import com.ronirusmayadi.sahabatqu.LoginActivity;
import com.ronirusmayadi.sahabatqu.Model.TransaksiModel;
import com.ronirusmayadi.sahabatqu.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryFragment extends Fragment {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    AdapterHistoryTransaksi adapter;
    List<TransaksiModel> transaksiModelList;
    private FirebaseAuth auth;
    private String GetUserID;

    private ProgressBar progressBar;
    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        getActivity().setTitle("History Infaq");
        setHasOptionsMenu(true);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null){
            GetUserID = user.getUid();
            recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
            progressBar = view.findViewById(R.id.progressBar);

            recyclerView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.GONE);

            transaksiModelList = new ArrayList<>();
            adapter = new AdapterHistoryTransaksi(transaksiModelList, getActivity().getApplicationContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            loadData();
        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        return view;
    }

    private void loadData(){
        progressBar.setVisibility(View.VISIBLE);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("infosaldo").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                transaksiModelList.clear();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                    TransaksiModel transaksiModel = noteDataSnapshot.getValue(TransaksiModel.class);
                    transaksiModelList.add(transaksiModel);
                }

                Collections.reverse(transaksiModelList);
                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(), "Database Error"+databaseError, Toast.LENGTH_SHORT).show();

            }
        });
    }

}
