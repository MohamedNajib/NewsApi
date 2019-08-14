package com.example.newsapi.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.example.newsapi.Model.CustomSpinnerItem;
import com.example.newsapi.R;

import java.util.ArrayList;
import java.util.Objects;

public class CustomSpinnerAdapter extends ArrayAdapter<CustomSpinnerItem> {


    public CustomSpinnerAdapter(@NonNull Context context, ArrayList<CustomSpinnerItem> customList) {
        super(context, 0, customList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cusstom_spinner_layout, parent, false);
        }
        CustomSpinnerItem item = getItem(position);

        TextView spinnerTV = convertView.findViewById(R.id.tvSpinnerLayout);
        if (item != null) {
            spinnerTV.setText(item.getSpinnerItemName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dropdown_layout, parent, false);
        }
        CustomSpinnerItem item = getItem(position);

        TextView dropDownTV = convertView.findViewById(R.id.tvDropDownLayout);
        FrameLayout frameLayout = convertView.findViewById(R.id.fram);

        if (item.getSpinnerItemName().equals("technology") || item.getSpinnerItemName().equals("fr") || item.getSpinnerItemName().equals("us")){
            frameLayout.setVisibility(View.GONE);
        }else {
            frameLayout.setVisibility(View.VISIBLE);
        }


        if (item != null) {
            dropDownTV.setText(item.getSpinnerItemName());

            final int sdk = android.os.Build.VERSION.SDK_INT;

//            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                dropDownTV.invalidateDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getContext(), R.drawable.spinner_background)));
//
//            } else {
//                dropDownTV.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.spinner_background));
//            }
        }
        return convertView;
    }
}

