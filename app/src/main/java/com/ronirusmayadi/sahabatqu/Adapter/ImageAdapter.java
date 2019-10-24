package com.ronirusmayadi.sahabatqu.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ronirusmayadi.sahabatqu.R;

public class ImageAdapter extends BaseAdapter {

    public static int[] gambar = {R.drawable.images1, R.drawable.images2, R.drawable.images3,
            R.drawable.images4, R.drawable.images5, R.drawable.images6,
            R.drawable.images7, R.drawable.images8, R.drawable.images9,
            R.drawable.images10, R.drawable.images11, R.drawable.images12,
            R.drawable.images13, R.drawable.images14, R.drawable.images15,
            R.drawable.images16, R.drawable.images17, R.drawable.images18,
            R.drawable.images19, R.drawable.images20, R.drawable.images21,
            R.drawable.images22, R.drawable.images23, R.drawable.images24};

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }
    @Override
    public int getCount() {
        return gambar.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        //Jika tidak di daur ulang
        if (convertView == null) {
            //Menginisialisasi beberapa atribut
            imageView = new ImageView(mContext);
            //Mengatur Panjang dan Lebar pada ImageView
            imageView.setLayoutParams(new GridView.LayoutParams(180, 180));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //Mengatur Padding
            imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(gambar[position]);

        return imageView;
    }
}
