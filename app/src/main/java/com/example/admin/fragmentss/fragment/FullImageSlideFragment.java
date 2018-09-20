package com.example.admin.fragmentss.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.admin.fragmentss.R;
import com.example.admin.fragmentss.adapter.ImageSliderPagerAdapter;
import com.example.admin.fragmentss.json_parser.MoviesListJson;

import java.util.ArrayList;

public class FullImageSlideFragment extends DialogFragment {
    private ViewPager viewPager;
    private int selected_position=0;
    ArrayList<MoviesListJson> moviesListJsonArrayList;
    static FullImageSlideFragment newInstance(){
        FullImageSlideFragment fullImageSlideFragment =new FullImageSlideFragment();
        return fullImageSlideFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment= inflater.inflate(R.layout.fragment_full_image_slide,container,false);
        viewPager=(ViewPager)fragment.findViewById(R.id.ffis_view_pager);

        moviesListJsonArrayList=(ArrayList<MoviesListJson>) getArguments().getSerializable("IMAGE");
        selected_position=getArguments().getInt("POSITION");

        ImageSliderPagerAdapter sliderPagerAdapter=new ImageSliderPagerAdapter(this.getContext(),moviesListJsonArrayList);
        viewPager.setAdapter(sliderPagerAdapter);
        viewPager.addOnPageChangeListener(viewPageChangeListener);
        setItemPosition(selected_position);
        return fragment;
    }

    ViewPager.OnPageChangeListener viewPageChangeListener  = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            displayDataInfo(position);

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public void displayDataInfo(int position){
        MoviesListJson moviesListJson=moviesListJsonArrayList.get(position);
        //get all data from JSON by using moviesListJson object
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    public void setItemPosition(int position){
        viewPager.setCurrentItem(position,false);
        displayDataInfo(position);

    }
}
