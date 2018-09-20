package com.example.admin.fragmentss;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.fragmentss.adapter.PagerAdapter;
import android.support.design.widget.TabLayout;

import com.example.admin.fragmentss.fragment.CardViewFragment;
import com.example.admin.fragmentss.fragment.GridViewFragment;
import com.example.admin.fragmentss.fragment.WabViewFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isInitView();
        if (viewPager!=null){
            PagerAdapter pagerAdapter=new PagerAdapter(getSupportFragmentManager());
            pagerAdapter.getFragment(new GridViewFragment(), "Grid View");
            pagerAdapter.getFragment(new CardViewFragment(), "Card View");
            pagerAdapter.getFragment(new WabViewFragment(), "Web View");
            viewPager.setAdapter(pagerAdapter);

        }
    }
    private void isInitView(){
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        tabLayout=(TabLayout) findViewById(R.id.pger_header);
        tabLayout.setupWithViewPager(viewPager);

    }
}
