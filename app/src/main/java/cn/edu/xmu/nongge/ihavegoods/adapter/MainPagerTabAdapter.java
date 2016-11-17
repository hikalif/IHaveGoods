package cn.edu.xmu.nongge.ihavegoods.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by asus1 on 2016/10/26.
 */
public class MainPagerTabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private String[] titles = {"first", "second", "third", "fourth"};

    public MainPagerTabAdapter(FragmentManager fm, List<Fragment> fragmentList)
    {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titles[position];
    }
}
