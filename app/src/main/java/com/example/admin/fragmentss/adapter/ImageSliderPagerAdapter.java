package com.example.admin.fragmentss.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.admin.fragmentss.R;
import com.example.admin.fragmentss.json_parser.MoviesListJson;

import java.util.ArrayList;

public class ImageSliderPagerAdapter extends PagerAdapter {
    Context cnt;
    ArrayList<MoviesListJson> img_list;
    LayoutInflater layoutInflater;

    public ImageSliderPagerAdapter(Context context, ArrayList<MoviesListJson> images) {
        img_list=images;
        cnt=context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(cnt);
        ViewGroup layout= (ViewGroup) layoutInflater.inflate(R.layout.fragment_full_image,container,false);
        ImageView imageView=(ImageView)layout.findViewById(R.id.ffi_full_img);
        MoviesListJson moviesListJson=img_list.get(position);
        Glide.with(cnt).load(moviesListJson.getImg_url())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        container.addView(layout);
        return layout;
        /*View view =layoutInflater.inflate(R.layout.fragment_full_image,container,false);
        ImageView imageView=(ImageView)view.findViewById(R.id.ffi_full_img);
        MoviesListJson moviesListJson=img_list.get(position);
        Glide.with(cnt).load(moviesListJson.getImg_url())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        container.addView(view);
        return view;*/
    }



    @Override
    public int getCount() {
        return img_list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==((View)o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
