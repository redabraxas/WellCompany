package com.chocoroll.ourcompay.Home;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chocoroll.ourcompay.R;
import com.chocoroll.ourcompay.model.Report;
import com.chocoroll.ourcompay.model.ReportAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportListFragment extends Fragment implements HomeFragment.homeFragemntReportListner{

    ArrayList<Report> reportList = new ArrayList<Report>();
    ReportAdapter mAdapter;
    ListView listView;

    public ReportListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_report_list, container, false);

        listView =(ListView) v.findViewById(R.id.listViewReport);
        mAdapter= new ReportAdapter(getActivity(), R.layout.model_report, reportList);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setDivider(new ColorDrawable(Color.LTGRAY));
        listView.setDividerHeight(3);

        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Report item =(Report)mAdapter.getItem(i);
//                Intent intent = new Intent(getActivity(), D.class);
//                intent.putExtra("Report",item);
//                startActivity(intent);


            }
        }) ;
        return v;
    }


    @Override
    public void setReportList(ArrayList<Report> reportList) {
        this.reportList.clear();
        this.reportList = reportList;
        listView.setAdapter(mAdapter);
    }
}
