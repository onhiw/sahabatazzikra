package com.ronirusmayadi.sahabatqu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ronirusmayadi.sahabatqu.Adapter.DoaAdapter;
import com.ronirusmayadi.sahabatqu.Model.DoaModel;

import java.util.ArrayList;
import java.util.List;

public class DoaActivity extends AppCompatActivity {

    private List<DoaModel> doaModelList;
    private RecyclerView recyclerView;
    private DoaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doa);
        setTitle("Kumpulan Doa-doa");
        recyclerView = findViewById(R.id.recycler_view);
        doaModelList = new ArrayList<>();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new DoaAdapter(doaModelList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        if (recyclerView!=null){
            loadDoa();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void loadDoa(){
        doaModelList.add(new DoaModel("1","Doa Masuk Masjid",  "اَللّٰهُمَّ افْتَحْ لِيْ اَبْوَابَ رَحْمَتِكٍَ", "Ya Allah, bukalah untukku pintu-pintu rahmat-Mu."));
        doaModelList.add(new DoaModel( "2","Doa Keluar Masjid", "اَللّٰهُمَّ اِنِّى اَسْأَلُكَ مِنْ فَضْلِكَ", "Ya Allah, sesungguhnya aku memohon keutamaan dari-Mu"));
        doaModelList.add(new DoaModel( "3","Doa Sebelum Makan", "الَّلهُمَّ بَارِكْ لَنَا فِيمَا رَزَقْتَنَا، وَقِنَا عَذَابَ النَّارِ", "Ya Allah, berkatilah rezeki yang engkau berikan kepada kami, dan peliharalah kami dari siksa api neraka"));
        doaModelList.add(new DoaModel( "4","Doa Sesudah Makan", "اَلْحَمْدُ ِللهِ الَّذِيْنَ اَطْعَمَنَا وَسَقَانَا وَجَعَلَنَا مِنَ الْمُسْلِمِيْنَِ", "Segala puji bagi Allah yang memberi kami makan dan minum serta menjadikan kami memeluk agama islam"));
        doaModelList.add(new DoaModel( "5","Doa Sesudah Wudhu", "اللَّهُمَّ اجْعَلْنِي مِنَ التَّوَّابِيْنَ وَاجْعَلْنِي مِنَ الْمُتَطَهِّرِيْنَِ", "Ya Allah jadikanlah aku termasuk orang-orang yang bertaubat dan orang-orang yang bersuci”"));
        doaModelList.add(new DoaModel( "6","Doa Ketika Mendapat Mimpi Baik", "بِسْمِكَ اللّهُمَّ اَحْيَا وَ بِسْمِكَ اَمُوْتُ", "Segala puji bagi Allah yang telah memberi hajatku"));
        doaModelList.add(new DoaModel( "7","Doa Masuk Kamar Mandi", "باَللّٰهُمَّ اِنّىْ اَعُوْذُبِكَ مِنَ الْخُبُثِ وَالْخَبَآئِثِ", "Ya Allah, aku berlindung pada-Mu dari godaan syetan  laki-laki dan syetan perempuan"));
        doaModelList.add(new DoaModel( "8","Doa Istinja", "اَللّٰهُمَّ طَهِّرُ قَلْبِىْ مِنَ النِّفَاقِ وَحَصِّنْ فَرْخِىْ مِنَ الْفَوَاحِشِ", "Wahai Tuhanku, sucikanlah hatiku dari sifat kepura-puraan (munafiq) serta peliharalah kemaluanku dari perbuatan keji"));
        doaModelList.add(new DoaModel("9","Doa Masuk WC", "اَللّهُمَّ اِنِّيْ أَعُوْذُبِكَ مِنَ الْخُبُثِ وَالْخَبَا إِثِ.ُ", "Wahai Tuhanku, sesungguhnya aku berlindung kepada-Mu dari segala kejahatan dan kotoran"));
        doaModelList.add(new DoaModel("10","Doa Keluar WC", "اَلْحَمْدُ الِلّهِ الَّذِيْ أَذْ هَبَ عَنِّى اْلأَذَاى وَعَافَانِيْ.ُ", "Segala puji bagi Allah yang telah menghilangkan kotoranku dan membuatku sehat"));
    }
}
