package com.ronirusmayadi.sahabatqu.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ronirusmayadi.sahabatqu.Adapter.ArtikelAdapter;
import com.ronirusmayadi.sahabatqu.Adapter.SejarahAdapter;
import com.ronirusmayadi.sahabatqu.Model.ArtikelModel;
import com.ronirusmayadi.sahabatqu.Model.SejarahModel;
import com.ronirusmayadi.sahabatqu.R;

import java.util.ArrayList;
import java.util.List;

public class ArtikelFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<ArtikelModel> artikelModelList;

    public ArtikelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artikel, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);

        artikelModelList = new ArrayList<>();
        ArtikelAdapter artikelAdapter = new ArtikelAdapter(artikelModelList, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(artikelAdapter);

        loadArtikel();
        return view;
    }

    private void loadArtikel() {
        artikelModelList.add(new ArtikelModel(
                "Adab-Adab Safar (Bepergian Jauh)",
                "muslim.or.id",
                R.drawable.artikel1,
                ""
        ));

        artikelModelList.add(new ArtikelModel(
                "Waktu Sahur Bukan Untuk Ngobrol dan Nonton, Tapi Doa dan istigfar",
                "muslim.or.id",
                R.drawable.artikel2,
                "https://muslim.or.id/47005-waktu-sahur-bukan-untuk-ngobrol-dan-nonton-tapi-doa-dan-istigfar.html"
        ));

        artikelModelList.add(new ArtikelModel(
                "Rajin Shalat Tarawih Tapi Tidak Shalat Wajib Berjamaah di Masjid",
                "muslim.or.id",
                R.drawable.artikel3,
                "https://muslim.or.id/46932-rajin-shalat-tarawih-tapi-tidak-shalat-wajib-berjamaah-di-masjid.html"
        ));

        artikelModelList.add(new ArtikelModel(
                "Memprioritaskan Akhlak kepada Allah",
                "muslim.or.id",
                R.drawable.artikel4,
                "https://muslim.or.id/46861-memprioritaskan-akhlak-kepada-allah.html"
        ));

        artikelModelList.add(new ArtikelModel(
                "Meraih Surga dan Menjauh dari Neraka dengan Ilmu Syar’i",
                "muslim.or.id",
                R.drawable.artikel5,
                "https://muslim.or.id/46859-meraih-surga-dan-menjauh-dari-neraka-dengan-ilmu-syari.html"
        ));

        artikelModelList.add(new ArtikelModel(
                "Ketidaksempurnaan Iman Kaum Musyrikin Terhadap Rububiyyah Allah",
                "muslim.or.id",
                R.drawable.artikel6,
                "https://muslim.or.id/46734-ketidaksempurnaan-iman-kaum-musyrikin-terhadap-rububiyyah-allah.html"
        ));
    }

}
