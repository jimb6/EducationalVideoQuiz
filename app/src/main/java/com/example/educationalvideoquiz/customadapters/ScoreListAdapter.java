package com.example.educationalvideoquiz.customadapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.educationalvideoquiz.R;

public class ScoreListAdapter extends ArrayAdapter {

    private final Activity context;
    private final int[] scores;
    private final String[] names;

    public ScoreListAdapter(Activity context, int[] scores, String[] names){

        super(context, R.layout.listview_row , names);

        this.context = context;
        this.scores = scores;
        this.names = names;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView textName = rowView.findViewById(R.id.textPlayerName);
        TextView textScore = rowView.findViewById(R.id.textPlayerScore);
        TextView textRank = rowView.findViewById(R.id.textRank);

        //this code sets the values of the objects to values from the arrays
       textScore.setText(scores[position]+"");
        textName.setText(names[position]);
        textRank.setText((position+1)+".");
        return rowView;

    };
}
