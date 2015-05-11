package com.chocoroll.ourcompay.Company;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.chocoroll.ourcompay.Home.CompanyListFragment;
import com.chocoroll.ourcompay.Home.ReportListFragment;
import com.chocoroll.ourcompay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyFragment extends Fragment {


    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;


    public CompanyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =inflater.inflate(R.layout.fragment_company, container, false);

        tabs = (PagerSlidingTabStrip)v.findViewById(R.id.tabs);
        pager = (ViewPager)v.findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(4);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        tabs.setViewPager(pager);

        return v;
    }


    public class MyPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider{

        private int[] ICONS = { R.drawable.left_menu, R.drawable.left_menu, R.drawable.left_menu, R.drawable.left_menu };

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
        @Override
        public int getCount() {
            return ICONS.length;
        }

        @Override
        public int getPageIconResId(int position) {
            return ICONS[position];
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new CompanyListFragment();
                case 1:
                    return new ReportListFragment();
                case 2:
                    return new ReportListFragment();
                case 3:
                    return new ReportListFragment();
            }

            return null;
        }


    }


}
