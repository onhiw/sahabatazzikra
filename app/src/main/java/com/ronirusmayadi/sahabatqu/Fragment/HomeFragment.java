package com.ronirusmayadi.sahabatqu.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ronirusmayadi.sahabatqu.AboutActivity;
import com.ronirusmayadi.sahabatqu.Adapter.AdapterGallery;
import com.ronirusmayadi.sahabatqu.Adapter.SliderAdapter;
import com.ronirusmayadi.sahabatqu.Adapter.SliderProgramAdapter;
import com.ronirusmayadi.sahabatqu.AlquranActivity;
import com.ronirusmayadi.sahabatqu.AmalanActivity;
import com.ronirusmayadi.sahabatqu.ArtikelActivity;
import com.ronirusmayadi.sahabatqu.AsmaulActivity;
import com.ronirusmayadi.sahabatqu.DoaActivity;
import com.ronirusmayadi.sahabatqu.GalleryActivity;
import com.ronirusmayadi.sahabatqu.InfoSaldoActivity;
import com.ronirusmayadi.sahabatqu.JadwalSholatActivity;
import com.ronirusmayadi.sahabatqu.Model.ModelGallery;
import com.ronirusmayadi.sahabatqu.Model.SliderModel;
import com.ronirusmayadi.sahabatqu.Model.SliderProgramModel;
import com.ronirusmayadi.sahabatqu.R;
import com.ronirusmayadi.sahabatqu.WebActivity;
import com.ronirusmayadi.sahabatqu.WebViewActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    private RecyclerView recyclerView;
    private List<ModelGallery> modelGalleryList;
    private TextView lihatSemua;

    private ImageView acara1;
    private ImageView acara2;
    private LinearLayout mDoa;
    private LinearLayout mAsma;
    private LinearLayout mArtikel;
    private LinearLayout mSaldo;
    private LinearLayout mJadwal;
    private LinearLayout mQuran;
    private LinearLayout mAbout;
    private LinearLayout mMore;
    private LinearLayout mSunnah;

    private ViewFlipper viewFlipper;
    private String mTag;

    private Fragment fragment;
    private FragmentManager fragmentManager;

    ViewPager viewPager;
    SliderAdapter sliderAdapter;
    List<SliderModel> sliderModels;

    ViewPager viewPager2;
    SliderProgramAdapter sliderProgramAdapter;
    List<SliderProgramModel> sliderProgramModels;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Home");
        setHasOptionsMenu(true);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        if (savedInstanceState != null){

            mTag = savedInstanceState.getString("tag");
        }

        fragmentManager = getFragmentManager();
        viewFlipper = view.findViewById(R.id.sunset);
        lihatSemua = view.findViewById(R.id.lihat);
        acara1 = view.findViewById(R.id.acara1);
        acara2 = view.findViewById(R.id.acara2);
        mDoa = view.findViewById(R.id.card1);
        mAsma = view.findViewById(R.id.card2);
        mArtikel = view.findViewById(R.id.card3);
        mJadwal = view.findViewById(R.id.card6);
        mQuran = view.findViewById(R.id.card7);
        mAbout = view.findViewById(R.id.card8);
        mMore = view.findViewById(R.id.card9);
        mSunnah = view.findViewById(R.id.card10);

        mDoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DoaActivity.class);
                startActivity(intent);
            }
        });

        mAsma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AsmaulActivity.class);
                startActivity(intent);
            }
        });
        mArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArtikelActivity.class);
                startActivity(intent);
            }
        });

        mJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JadwalSholatActivity.class);
                startActivity(intent);
            }
        });

        mQuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AlquranActivity.class);
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

        mSunnah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AmalanActivity.class);
                startActivity(intent);
            }
        });

        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                MenuFragment menuFragment = MenuFragment.newInstance("Some Title");
                menuFragment.show(fm, "fragment_edit_name");
            }
        });

        sliderModels = new ArrayList<>();
        sliderModels.add(new SliderModel(R.drawable.mekkah, "Mekkah", ""));
        sliderModels.add(new SliderModel(R.drawable.madinah, "Madinah", ""));
        sliderModels.add(new SliderModel(R.drawable.aqso, "Palestina", ""));

        sliderAdapter = new SliderAdapter(sliderModels, getActivity());

        viewPager = view.findViewById(R.id.viewPager);
        TabLayout tabLayout = view.findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);
        viewPager.setAdapter(sliderAdapter);
        viewPager.setPadding(0, 0, 0, 0);

        sliderProgramModels = new ArrayList<>();
        sliderProgramModels.add(new SliderProgramModel(R.drawable.info_program, "SEDEKAH SERIBU SEHARI\n" +
                "\n" +
                "Assalaamu alaikum wa rahmatullahi wa barkaatuhu.\n" +
                ".\n" +
                "Sahabat2ku fillah yang dirahmati oleh Allah..\n" +
                "\n" +
                "Kami membuat Program Infaq 1.000/hari ini, berdasarkan perintah Allah..\n" +
                "\n" +
                "Simaklah Kalam Allah ini dg iman, “ Perumpamaan orang yg menginfakkan hartanya di jalan Allah seperti sebutir biji yg menumbuhkan tujuh tangkai, pada setiap tangkai ada seratus biji. Allah melipat gandakan bagi siapa yg Dia kehendaki, dan Allah Mahaluas, Maha Mengetahui” (QS Al-Baqarah 261)\n" +
                "\n" +
                "Rasulullah bersabda..\n" +
                "“Bersegeralah bersedekah, karena bala tidak pernah mendahului sedekah”\n" +
                "(HR Thabrani).\n" +
                "“Sedekah itu dapat menghapus dosa sebagaimana air itu memadamkan api“ (HR At-Tirmidzi).\n" +
                "\n" +
                "Jadi dengan dasar itulah kami ingin mengajak semuanya untuk melakukan perbaikan kebaikan melalui atau berupaya semampu mungkin dengan ber-infaq..\n" +
                "\n" +
                "Walaupun nilainya 1.000/hari, tapi berharap Ridho Allah ada dalam nilai itu, berharap Allah turunkan keberkahan pada Rezeki kita, dan berharap yang tentunya masih banyak lagi janji Allah tentang ber-infaq atau bersedekah..\n" +
                "\n" +
                "Jangan pernah remehkan sekecil apapun amal jariyah ini, walau serupiah, walau hanya doa, sahabat2ku..\n" +
                "\n" +
                "Selagi kita sehat..\n" +
                "Selagi kita mampu..\n" +
                "Selagi kita masih Allah berikan kesempatan hidup...\n" +
                "\n" +
                "Yuk kita bersedekah..\n" +
                "\n" +
                "Ya Allah..Berkahi amal Jariyah kami... Aamiin..."));
        sliderProgramModels.add(new SliderProgramModel(R.drawable.soon, ""));

        sliderProgramAdapter = new SliderProgramAdapter(sliderProgramModels, getActivity());
        viewPager2 = view.findViewById(R.id.viewPager2);
        TabLayout tabLayout2 = view.findViewById(R.id.tabDots2);
        tabLayout2.setupWithViewPager(viewPager2, true);
        viewPager2.setAdapter(sliderProgramAdapter);
        viewPager2.setPadding(0, 20, 60, 0);


        acara1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WebActivity.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
            }
        });

        acara2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WebViewActivity.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
            }
        });

        lihatSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GalleryActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view);

        modelGalleryList = new ArrayList<>();


        AdapterGallery adapterGallery = new AdapterGallery(modelGalleryList, getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterGallery);

        if (recyclerView!=null){
            loadGallery();
        }


        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tag", mTag);
    }

    private void loadGallery(){
        modelGalleryList.add(new ModelGallery(
                R.drawable.images1
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images2
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images3
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images4
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images5
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images6
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images7
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images8
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images9
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images10
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images11
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images12
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images13
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images14
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images15
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images16
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images17
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images18
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images19
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images20
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images21
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images22
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images23
        ));

        modelGalleryList.add(new ModelGallery(
                R.drawable.images24
        ));
    }

    public  void  fliverImages(int images){
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(images);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);

    }
}
