package com.chocoroll.ourcompay.Home;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.astuetz.PagerSlidingTabStrip;
import com.chocoroll.ourcompay.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ProgressDialog dialog;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        tabs = (PagerSlidingTabStrip)v.findViewById(R.id.tabs);
        tabs.setTextColor(Color.WHITE);
        pager = (ViewPager)v.findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(2);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        tabs.setViewPager(pager);



        // 스피너 설정
        final Spinner spinnerS = (Spinner)v.findViewById(R.id.spinner_small_category);
        final Spinner spinnerB = (Spinner)v.findViewById(R.id.spinner_big_category);

        spinnerB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ArrayAdapter<CharSequence> adapter = null;
                String item = spinnerB.getSelectedItem().toString();

                if (item.equals("서비스·교육·금융·유통")) {
                    adapter= ArrayAdapter.createFromResource(getActivity(), R.array.small_category_arrays_1,
                            android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                }else if(item.equals("미디어·광고·문화·예술")){
                    adapter= ArrayAdapter.createFromResource(getActivity(), R.array.small_category_arrays_2,
                            android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                }else if(item.equals("IT·정보통신")){
                    adapter= ArrayAdapter.createFromResource(getActivity(), R.array.small_category_arrays_3,
                            android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                }else if(item.equals("제조·통신·화학·건설")){
                    adapter= ArrayAdapter.createFromResource(getActivity(), R.array.small_category_arrays_4,
                            android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                }else{
                    // 전체보기인 경우

                    dialog = new ProgressDialog(getActivity());
                    dialog.setMessage("딜 리스트를 받아오는 중입니다...");
                    dialog.setIndeterminate(true);
                    dialog.setCancelable(false);
                    dialog.show();
                    getDealList("전체보기", "전체보기");
                }


                spinnerS.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                String item = spinnerS.getSelectedItem().toString();
                getDealList(spinnerB.getSelectedItem().toString(),item);
                Log.e("sitem", item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return v;
    }


    public class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
           switch (position){
               case 0:
                   return new CompanyListFragment();
               case 1:
                   return new ReportListFragment();
           }

            return null;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "COMPANY";
                case 1:
                    return "REPORT";
                default:
                    return "";
            }

        }
    }




    void getDealList(String bCategory, String sCategory){

        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("회사 및 보고서 리스트를 받아오는 중입니다...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();


        final JsonObject info = new JsonObject();
        info.addProperty("bCategory",bCategory);
        info.addProperty("sCategory",sCategory);

        new Thread(new Runnable() {
            public void run() {
                try {

                    RestAdapter restAdapter = new RestAdapter.Builder()
                            .setEndpoint(Retrofit.ROOT)  //call your base url
                            .build();
                    Retrofit retrofit = restAdapter.create(Retrofit.class); //this is how retrofit create your api
                    retrofit.getDealList(info,new Callback<JsonArray>() {

                        @Override
                        public void success(JsonArray jsonElements, Response response) {

                            dialog.dismiss();

                            for(int i=0; i<jsonElements.size(); i++) {
                                JsonObject deal = (JsonObject) jsonElements.get(i);
                                String num = (deal.get("proNum")).getAsString();
                                String name = (deal.get("proName")).getAsString();
                                String price = (deal.get("proPrice")).getAsString();

                                String bCategory = (deal.get("bigCategory")).getAsString();
                                String sCategory = (deal.get("smallCategory")).getAsString();

                                String dday = (deal.get("limitDate")).getAsString();
                                String maxBook = (deal.get("maxBook")).getAsString();

                                String keep = (deal.get("keepCount")).getAsString();
                                String book = (deal.get("bookCount")).getAsString();

                                String thumbnail = (deal.get("thumbnail")).getAsString();
                                String detailView = (deal.get("detailView")).getAsString();
                                String comment = (deal.get("proComment")).getAsString();

                                String seller = (deal.get("sellerID")).getAsString();
                                String phone = (deal.get("phone")).getAsString();

                                String state = (deal.get("state")).getAsString();


                                pList.add(new Deal(num, name,price, bCategory, sCategory, dday, maxBook, keep,book, thumbnail, detailView,
                                        comment, seller, phone, state));

                            }

                            listView.setAdapter(mAdapter);
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            dialog.dismiss();
                            Log.e("error",retrofitError.getCause().toString());
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("네트워크가 불안정합니다.")        // 제목 설정
                                    .setMessage("네트워크를 확인해주세요")        // 메세지 설정
                                    .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        // 확인 버튼 클릭시 설정
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                        }
                                    });

                            AlertDialog dialog = builder.create();    // 알림창 객체 생성
                            dialog.show();    // 알림창 띄우기

                        }
                    });
                }
                catch (Throwable ex) {

                }
            }
        }).start();


    }

}
