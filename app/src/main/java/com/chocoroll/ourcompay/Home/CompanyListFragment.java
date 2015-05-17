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

import com.chocoroll.ourcompay.Company.CompanyActivity;
import com.chocoroll.ourcompay.R;
import com.chocoroll.ourcompay.model.Company;
import com.chocoroll.ourcompay.model.CompanyAdapter;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyListFragment extends Fragment  implements HomeFragment.homeFragemntCompanyListner{

    ArrayList<Company> companyList = new ArrayList<Company>();
    CompanyAdapter mAdapter;
    ListView listView;


    public CompanyListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_company_list, container, false);

        listView =(ListView) v.findViewById(R.id.listViewCompany);
        mAdapter= new CompanyAdapter(getActivity(), R.layout.model_company, companyList);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setDivider(new ColorDrawable(Color.LTGRAY));
        listView.setDividerHeight(3);

        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Company item =(Company)mAdapter.getItem(i);
                Intent intent = new Intent(getActivity(), CompanyActivity.class);
                intent.putExtra("Company",item);
                startActivity(intent);

            }
        }) ;
        return v;
    }


    @Override
    public void setCompanyList(ArrayList<Company> companyList) {
        this.companyList.clear();
        this.companyList = companyList;
        listView.setAdapter(mAdapter);

    }
}
