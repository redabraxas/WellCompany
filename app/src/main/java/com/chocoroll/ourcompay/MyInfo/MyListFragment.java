package com.chocoroll.ourcompay.MyInfo;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.astuetz.PagerSlidingTabStrip;
import com.chocoroll.ourcompay.Home.CompanyListFragment;
import com.chocoroll.ourcompay.Home.ReportListFragment;
import com.chocoroll.ourcompay.MainActivity;
import com.chocoroll.ourcompay.R;

/**
 * Created by RA on 2015-05-18.
 */
public class MyListFragment extends Fragment {

    public MyListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View v = inflater.inflate(R.layout.fragment_mylist, container, false);
        // 유저아이디는
        String user_id = ((MainActivity)MainActivity.mContext).getUserId();

        return v;
    }



}
