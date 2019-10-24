package com.ronirusmayadi.sahabatqu.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.mancj.slideup.SlideUp;
import com.ronirusmayadi.sahabatqu.AboutActivity;
import com.ronirusmayadi.sahabatqu.AlquranActivity;
import com.ronirusmayadi.sahabatqu.AmalanActivity;
import com.ronirusmayadi.sahabatqu.ArtikelActivity;
import com.ronirusmayadi.sahabatqu.AsmaulActivity;
import com.ronirusmayadi.sahabatqu.DoaActivity;
import com.ronirusmayadi.sahabatqu.InfoSaldoActivity;
import com.ronirusmayadi.sahabatqu.JadwalSholatActivity;
import com.ronirusmayadi.sahabatqu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends DialogFragment {
    private SlideUp slideUp;
    private View dim;
    private View slideView;

    private LinearLayout mDoa;
    private LinearLayout mAsma;
    private LinearLayout mArtikel;
    private LinearLayout mSaldo;
    private LinearLayout mJadwal;
    private LinearLayout mQuran;
    private LinearLayout mAbout;
    private LinearLayout mMore;
    private LinearLayout mSunnah;


    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance(String title) {
        MenuFragment frag = new MenuFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        Animation slide_up = AnimationUtils.loadAnimation(getContext(),
                R.anim.slide_up);

        slideView = view.findViewById(R.id.slideView);
        dim = view.findViewById(R.id.dim);

        mDoa = view.findViewById(R.id.card1);
        mAsma = view.findViewById(R.id.card2);
        mArtikel = view.findViewById(R.id.card3);
        mSaldo = view.findViewById(R.id.card5);
        mJadwal = view.findViewById(R.id.card6);
        mQuran = view.findViewById(R.id.card7);
        mAbout = view.findViewById(R.id.card9);
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

//        mSaldo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), InfoSaldoActivity.class);
//                startActivity(intent);
//            }
//        });

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

        mSunnah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AmalanActivity.class);
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

        slideUp = new SlideUp(slideView);

        view.startAnimation(slide_up);
        slideUp.setSlideListener(new SlideUp.SlideListener() {
            @Override
            public void onSlideDown(float v) {
                dim.setAlpha(1 - (v / 100));
            }

            @Override
            public void onVisibilityChanged(int i) {
                if (i == View.GONE) {
                    getDialog().dismiss();
                }

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        String title = getArguments().getString("title", "Enter Name");
//        getDialog().setTitle(title);
    }


}
