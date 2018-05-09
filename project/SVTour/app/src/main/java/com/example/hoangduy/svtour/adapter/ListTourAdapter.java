package com.example.hoangduy.svtour.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.svtour.R;
import com.example.hoangduy.svtour.model.Tour;

import java.util.List;

/**
 * Created by HoangDuy on 5/8/2018.
 */

public class ListTourAdapter extends BaseAdapter{

    private Context mContext;
    private List<Tour> mTourList;

    public ListTourAdapter(Context mContext, List<Tour> mTourList) {
        this.mContext = mContext;
        this.mTourList = mTourList;
    }


    @Override
    public int getCount() {
        return mTourList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTourList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mTourList.get(position).getTour_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(mContext, R.layout.tour_item_listview, null);

        //Image view processing...
        //ImageView ivTourAvatar = (ImageView) view.findViewById(R.id.ivTourAvatar);
        TextView tvTourName = (TextView) view.findViewById(R.id.tvTourName);
        TextView tvTourDate = (TextView) view.findViewById(R.id.tvTourDate);

        tvTourName.setText(mTourList.get(position).getTour_name());
        tvTourDate.setText(mTourList.get(position).getTour_date());

        return view;
    }
}
