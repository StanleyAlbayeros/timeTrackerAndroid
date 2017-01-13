package com.example.joans.timetracker;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;


import java.util.ArrayList;

import static android.widget.RelativeLayout.RIGHT_OF;

public class ArrayListWithButtonAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<DadesActivitat> list = new ArrayList<>();
    private Context context;
    private Boolean isExpanded = false;
    private ImageButton expandableArrow;

    private final String tag = this.getClass().getSimpleName();
    private TextView listItemText;


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
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =
                    inflater.inflate(R.layout.custom_arraylistview_row, null);
        }

        expandableArrow = (ImageButton) view.findViewById(R.id.expand_image_button);
        expandableArrow.setImageResource(list.get(position).getCurrentDrawableID());



        //Handle TextView and display string from your list
        listItemText = (TextView) view.findViewById(R.id.list_item_string);

        RelativeLayout myRelLay = (RelativeLayout) view.findViewById(R.id.customArrayListViewRow);
        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(myRelLay.getWidth(), myRelLay.getHeight());
        params.addRule(RIGHT_OF, R.id.expand_image_button);
        //listItemText.setLayoutParams(params);

        listItemText.setText(list.get(position).toString());
        listItemText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (context instanceof LlistaActivitatsActivity) {
                    ((LlistaActivitatsActivity) context).goDeeper(position);
                }
            }
        });
        expandableArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).isExpanded()) {
                    isExpanded = false;
                    Log.d(tag, "closing expanded node");
                    listItemText.setText(list.get(position).toString());
                    list.get(position).setCurrentDrawableID(android.R.drawable.arrow_down_float);

                } else {
                    Log.d(tag, "\nopening node, description: " + list.get(position).getDescripcio() + "\n\n");
                    isExpanded = true;
                    listItemText.setText(list.get(position).toString());
                    list.get(position).setCurrentDrawableID(android.R.drawable.arrow_up_float);
                }
                list.get(position).toggleExpanded();
                notifyDataSetChanged();
            }

        });

        final ToggleButton toggleIntervalButton =
                (ToggleButton) view.findViewById(R.id.ToggleIntervalButton);
        toggleIntervalButton.setVisibility(View.GONE);

        if (list.get(position).isBasicTask()) {
            toggleIntervalButton.setVisibility(View.VISIBLE);

            toggleIntervalButton.setOnCheckedChangeListener(
                    new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton toggleButton,
                                                     boolean isChecked) {
                            Intent toggleIntent;

                            if (isChecked) {
                                if (context instanceof LlistaActivitatsActivity) {
                                    ((LlistaActivitatsActivity) context).toggleInterval(
                                            position,
                                            isChecked,
                                            list.get(position).getID());
                                }
                                isChecked = true;
                            } else {
                                if (context instanceof LlistaActivitatsActivity) {
                                    ((LlistaActivitatsActivity) context).toggleInterval(
                                            position,
                                            isChecked,
                                            list.get(position).getID());
                                }
                                isChecked = false;
                            }
                        }
                    });
        }
        return view;
    }


    public void clear() {
        list.clear();
    }

    public void add(DadesActivitat dadesAct) {
        list.add(dadesAct);
    }
}