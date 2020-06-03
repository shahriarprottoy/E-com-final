package com.example.zayans_eshop.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AdapterViewPager extends PagerAdapter {

    private Context context;
    private String[] imageUrls;

    public AdapterViewPager(Context context, String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        int count = 0;
        for (int i = 0; i < imageUrls.length; i++) {
            if (!imageUrls[i].equals("")) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (!imageUrls[position].equals("")) {
            ImageView imageView = new ImageView(context);
            Picasso.with(context).load(imageUrls[position]).into(imageView);
            container.addView(imageView);
            return imageView;
        }
        return null;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
