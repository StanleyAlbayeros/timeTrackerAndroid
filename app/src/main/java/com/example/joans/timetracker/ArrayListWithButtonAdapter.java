package com.example.joans.timetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.joans.timetracker.DadesActivitat;
import com.example.joans.timetracker.R;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ArrayListWithButtonAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<DadesActivitat> list = new ArrayList<>();
    private Context context;


    public ArrayListWithButtonAdapter(ArrayList<DadesActivitat> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getID();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_arraylistview_row, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position).toString());

        //Handle buttons and add onClickListeners
        ToggleButton toggleIntervalButton =
                (ToggleButton) view.findViewById(R.id.toggleIntervalButton);
        toggleIntervalButton.setChecked(false);
        toggleIntervalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                notifyDataSetChanged();
            }
        });

        return view;
    }
}