package com.wjk32.jnews.modules.mainindex;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;
import com.wjk32.jnews.constants.C;
import com.wjk32.jnews.R;
import com.wjk32.jnews.base.NaviActivity;
import com.wjk32.jnews.modules.second.ImageFragment;
import com.wjk32.jnews.modules.third.TabsFragment03;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends NaviActivity{

    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("OnCreate");
        ButterKnife.bind(this);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position==0){
                    NewsFragment newsFragment=NewsFragment.newInstance();
                    newsFragment.setPresenter(new NewsPresenter(newsFragment));
                    return newsFragment;
                }
                else if(position==1){
                    return new ImageFragment();
                }
                else return new TabsFragment03();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return C.HOME_TABS[position];
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }
}
