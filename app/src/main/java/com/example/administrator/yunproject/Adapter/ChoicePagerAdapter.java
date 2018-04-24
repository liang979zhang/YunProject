package com.example.administrator.yunproject.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.administrator.yunproject.Fragment.CacheFragment;
import com.example.administrator.yunproject.Fragment.CollectFragment;
import com.example.administrator.yunproject.Fragment.QuestionsFragment;
import com.example.administrator.yunproject.Fragment.StudyFragment;


/**
 * Created by DingWei on 2017/6/15.
 */
public class ChoicePagerAdapter extends FragmentStatePagerAdapter {
    private final String TAG = ChoicePagerAdapter.class.getSimpleName();
    private Context context;
    private FragmentManager fm;
    private String[] titles;


    public ChoicePagerAdapter(Context context, FragmentManager fm, String[] titles) {
        super(fm);
        this.context = context;
        this.fm = fm;
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new StudyFragment().newInstance(position);//choice  精选
                break;
            case 1:
                fragment = new CacheFragment().newInstance(position);//latest  最新
                break;
            case 2:
                fragment = new CollectFragment().newInstance(position);//moments 圈子
                break;
            case 3:
                fragment = new QuestionsFragment().newInstance(position);//followers 关注
                break;

        }
        return fragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

}
