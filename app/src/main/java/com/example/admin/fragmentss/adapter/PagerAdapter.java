package com.example.admin.fragmentss.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {

    private int page_count = 3;
    private final ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    private final ArrayList<String> title_list=new ArrayList<>();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        /*Fragment fragment = null;
        switch (position) {
            case 0: {
                fragment = new GridViewFragment();
                break;
            }
            case 1: {
                fragment = new WabViewFragment();
                break;
            }
            case 2: {
                fragment = new CardViewFragment();
                break;
            }
        }
        return fragment;*/
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void getFragment(Fragment fragment, String title){

        fragmentArrayList.add(fragment);
        title_list.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title_list.get(position);
    }
}
