package com.wjk32.jnews.modules.mainindex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.wjk32.jnews.constants.C;
import com.wjk32.jnews.R;
import com.wjk32.jnews.base.NaviActivity;
import com.wjk32.jnews.modules.second.TabsFragment02;
import com.wjk32.jnews.modules.third.TabsFragment03;

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
                    return new TabsFragment02();
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

}
