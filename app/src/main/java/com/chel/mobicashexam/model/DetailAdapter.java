package com.chel.mobicashexam.model;

import android.content.Context;
import android.widget.ArrayAdapter;


public class DetailAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mDetails;

    public DetailAdapter(Context context, int resource, String[] details){
        super(context,resource);
        this.mContext=context;
        this.mDetails= details;
    }

    @Override
    public Object getItem(int position) {
        String toDo = mDetails[position];
        return String.format("%s", toDo);
    }

    @Override
    public int getCount() {
        return mDetails.length;
    }

}
