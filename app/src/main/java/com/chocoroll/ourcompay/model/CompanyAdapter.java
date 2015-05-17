package com.chocoroll.ourcompay.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chocoroll.ourcompay.R;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by RA on 2015-05-17.
 */
public class CompanyAdapter extends ArrayAdapter<Company> {
    private ArrayList<Company> items;
    private Context context;

    public CompanyAdapter(Context context, int textViewResourceId, ArrayList<Company> items) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.context = context;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.model_company, null);
        }
        Company p = items.get(position);
        if (p != null) {
//
//            new DownloadImageTask((ImageView) v.findViewById(R.id.thumbnailDeal))
//                    .execute(p.getThumbnail());
//
//            String str = "["+p.getbCategory()+"/"+p.getsCategory()+"]  "+p.getName();
//            ((TextView)  v.findViewById(R.id.txt_name)).setText(str);
//            ((TextView) v.findViewById(R.id.txt_dday)).setText(p.getDday());
//            ((TextView)  v.findViewById(R.id.txt_people)).setText(String.valueOf(p.getBook()+"/"+p.getMaxBook()));
        }
        return v;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}