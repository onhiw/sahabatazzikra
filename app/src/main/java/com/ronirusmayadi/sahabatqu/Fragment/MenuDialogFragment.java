package com.ronirusmayadi.sahabatqu.Fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.ronirusmayadi.sahabatqu.R;

public class MenuDialogFragment extends DialogFragment {

    public static String TAG = "MenuDialogFragment";
    private RelativeLayout coordinatorLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_menu_dialog, container, false);
        coordinatorLayout = view.findViewById(R.id.layout);
        Animation slide_up = AnimationUtils.loadAnimation(getContext(),
                R.anim.slide_up);
        coordinatorLayout.startAnimation(slide_up);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                MenuDialogFragment.this.cancelUpload();

            }
        });
        return view;
    }

    @SuppressLint({"NewApi", "WrongConstant"})
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    private void cancelUpload(){
        Animation slide_down = AnimationUtils.loadAnimation(getContext(),
                R.anim.slide_down);
        coordinatorLayout.startAnimation(slide_down);
        dismiss();
    }

}
