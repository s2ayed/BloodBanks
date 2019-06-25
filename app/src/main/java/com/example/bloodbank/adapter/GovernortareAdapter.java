package com.example.bloodbank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.CityGovernorateSpinnnerModel;

import java.util.ArrayList;

public class GovernortareAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<CityGovernorateSpinnnerModel> spinnnerArrayList;

    public GovernortareAdapter(Context context, ArrayList<CityGovernorateSpinnnerModel> spinnnerArrayList) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.spinnnerArrayList = spinnnerArrayList;
    }

    @Override
    public int getCount() {
        return spinnnerArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return spinnnerArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{

        TextView cityAndGovernorateAdapter;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
        if (convertView==null) {
            convertView = layoutInflater.inflate(R.layout.set_adapter_spinner_cite, parent, false);

            viewHolder =new ViewHolder();

            viewHolder.cityAndGovernorateAdapter= convertView.findViewById(R.id.cityAndGovernorateAdapter);

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.cityAndGovernorateAdapter.setText(spinnnerArrayList.get(position).getName());
        return convertView;
    }

}
