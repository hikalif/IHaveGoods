package cn.edu.xmu.nongge.ihavegoods.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;


import com.mjj.PagerBottomTabStrip;

import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.R;
import cn.edu.xmu.nongge.ihavegoods.adapter.MainPagerTabAdapter;
import cn.edu.xmu.nongge.ihavegoods.fragment.GoodsFragment;

/**
 * Created by asus1 on 2016/10/26.
 */
public class MainActivity extends FragmentActivity {

    private int[] iconResid = {R.drawable.ic_bookmark_outline_grey600_24dp,
            R.drawable.ic_search_black_24dp,
            R.drawable.ic_notifications_none_grey600_24dp,
            R.drawable.ic_favorite_outline_grey600_24dp};

    private int[] iconResidClick = {R.drawable.ic_bookmark_black_24dp,
            R.drawable.ic_search_black_24dp,
            R.drawable.ic_notifications_black_24dp,
            R.drawable.ic_favorite_grey600_24dp};

    private Toolbar toolbar;
    private ViewPager viewPager;
    private PagerBottomTabStrip pagerBottomTabStrip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initAdapter();

        pagerBottomTabStrip = (PagerBottomTabStrip) findViewById(R.id.tab);
        pagerBottomTabStrip.builder(viewPager)
                .ColorMode()
                .TabClickIconColor(R.color.colorPrimary)
                .TabClickTextColor(R.color.white)
                .TabIcon(iconResid)
                .TabBackground(R.drawable.background_tab)
                .TabClickIcon(iconResidClick)
                .TabPadding(5)
                .build();
    }

    private void initAdapter()
    {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        for(int i = 0; i < 4; i++)
        {
            fragmentList.add(new GoodsFragment());
        }
        MainPagerTabAdapter mainPagerTabAdapter = new MainPagerTabAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(mainPagerTabAdapter);
    }

    public void setSupportActionBar(Toolbar supportActionBar) {
        this.toolbar = supportActionBar;
    }
}
