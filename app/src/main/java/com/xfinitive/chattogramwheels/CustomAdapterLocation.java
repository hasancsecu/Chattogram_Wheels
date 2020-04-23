package com.xfinitive.chattogramwheels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapterLocation extends BaseAdapter {
    String[] locName;
    Context context;
    private LayoutInflater inflater;

    public CustomAdapterLocation(){
    }
    CustomAdapterLocation(Context context, String[] locName){
        this.context = context;
        this.locName = locName;
    }
    @Override
    public int getCount() {
        return locName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_custom_adapter_location, viewGroup, false);
        }
        ImageView imageView = view.findViewById(R.id.locImageViewId);
        TextView textView = view.findViewById(R.id.locationViewId);

        imageView.setImageResource(R.drawable.radio_fill_24);
        textView.setText(locName[i]);

        return view;
    }
}
