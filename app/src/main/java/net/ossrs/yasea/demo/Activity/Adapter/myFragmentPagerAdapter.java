package net.ossrs.yasea.demo.Activity.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by kang on 2018/3/18.
 */

public class myFragmentPagerAdapter extends FragmentPagerAdapter
{

    private  List<Fragment> list;

    public myFragmentPagerAdapter(FragmentManager fm , List<Fragment> list)
    {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
