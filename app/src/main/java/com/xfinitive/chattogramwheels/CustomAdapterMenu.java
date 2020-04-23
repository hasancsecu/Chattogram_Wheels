package com.xfinitive.chattogramwheels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapterMenu extends BaseAdapter {
    String[] busNames;
    String[] busTypes;
    Context context;
    private LayoutInflater inflater;
    public  CustomAdapterMenu(){
    }
    CustomAdapterMenu(Context context, String[] busNames, String[] busTypes){
        this.context = context;
        this.busNames = busNames;
        this.busTypes = busTypes;
    }
    @Override
    public int getCount() {
        return busNames.length;
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
            if (view == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.activity_custom_adapter_menu, viewGroup, false);
            }

            ImageView imageView = view.findViewById(R.id.imageViewId);
            TextView textView1 = view.findViewById(R.id.textViewId);
            TextView textView2 = view.findViewById(R.id.textViewTypeId);

            if(busNames[i].contains("BUS")) {
                imageView.setImageResource(R.drawable.bus);
            }else {
                imageView.setImageResource(R.drawable.leguna);
            }
            textView1.setText(busNames[i]);
            textView2.setText(busTypes[i]);

        return view;
    }
}
