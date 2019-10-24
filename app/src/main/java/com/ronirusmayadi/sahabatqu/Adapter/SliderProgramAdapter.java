package com.ronirusmayadi.sahabatqu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ronirusmayadi.sahabatqu.InfoProgramActivity;
import com.ronirusmayadi.sahabatqu.Model.SliderProgramModel;
import com.ronirusmayadi.sahabatqu.R;

import java.util.List;

public class SliderProgramAdapter extends PagerAdapter {

    private List<SliderProgramModel> sliderModels;
    private LayoutInflater layoutInflater;
    private Context context;

    public SliderProgramAdapter(List<SliderProgramModel> sliderModels, Context context) {
        this.sliderModels = sliderModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliderModels.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item2, container, false);

        ImageView imageView;
        TextView title;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.lokasi);

        imageView.setImageResource(sliderModels.get(position).getmImage());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoProgramActivity.class);
                intent.putExtra("param", sliderModels.get(position).getmImage());
                intent.putExtra("title", sliderModels.get(position).getmTitle());
                context.startActivity(intent);
                // finish();
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}

