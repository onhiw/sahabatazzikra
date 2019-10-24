package com.ronirusmayadi.sahabatqu.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ronirusmayadi.sahabatqu.Adapter.SejarahAdapter;
import com.ronirusmayadi.sahabatqu.Model.SejarahModel;
import com.ronirusmayadi.sahabatqu.R;

import java.util.ArrayList;
import java.util.List;

public class SejarahFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<SejarahModel> sejarahModelList;
    public SejarahFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sejarah, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

        sejarahModelList = new ArrayList<>();
        SejarahAdapter sejarahAdapter = new SejarahAdapter(sejarahModelList, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(sejarahAdapter);

        loadSejarah();
        return view;
    }

    private void loadSejarah(){
        sejarahModelList.add(new SejarahModel(
                "Panglima Khalid bin Walid Diganti Karena Kemaslahatan Tauhid",
                "muslim.or.id",
                 R.drawable.sejarah1,
                "https://muslim.or.id/45570-panglima-khalid-bin-walid-diganti-karena-kemashlahatan-tauhid.html"
        ));

        sejarahModelList.add(new SejarahModel(
                "Potret Kesederhanaan Rasulullah Shallallahu’alaihi Wasallam",
                "muslim.or.id",
                 R.drawable.sejarah2,
                "https://muslim.or.id/43655-potret-kesederhanaan-rasulullah-shallallahualaihi-wasallam.html"
        ));

        sejarahModelList.add(new SejarahModel(
                "Turunnya Wahyu Pertama Kepada Rasulullah Shallallahu’alaihi Wasallam",
                "muslim.or.id",
                 R.drawable.sejarah3,
                "https://muslim.or.id/43060-turunnya-wahyu-pertama-kepada-rasulullah-shallallahualaihi-wasallam.html"
        ));

        sejarahModelList.add(new SejarahModel(
                "Apakah Dzulqarnain Seorang Nabi?",
                "muslim.or.id",
                 R.drawable.sejarah4,
                "https://muslim.or.id/29430-apakah-dzulqarnain-seorang-nabi.html"
        ));

        sejarahModelList.add(new SejarahModel(
                "Dua Perang Yang Diabadikan Allah Dalam Al Qur’an",
                "muslim.or.id",
                 R.drawable.sejarah5,
                "https://muslim.or.id/28895-dua-perang-yang-diabadikan-allah-dalam-al-quran.html"
        ));

        sejarahModelList.add(new SejarahModel(
                "Nabi Nuh, Bapak Seluruh Manusia Setelah Nabi Adam",
                "muslim.or.id",
                 R.drawable.sejarah6,
                "https://muslim.or.id/28492-nabi-nuh-bapak-seluruh-manusia-setelah-nabi-adam.html"
        ));
    }

}
