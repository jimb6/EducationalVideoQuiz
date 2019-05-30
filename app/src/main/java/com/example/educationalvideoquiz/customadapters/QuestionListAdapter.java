package com.example.educationalvideoquiz.customadapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.educationalvideoquiz.R;
import com.example.educationalvideoquiz.classes.Question;

public class QuestionListAdapter extends ArrayAdapter {

    private final Activity context;
    private final Question [] questions;

    public QuestionListAdapter(Activity context,Question [] questions){
        super(context, R.layout.questions_row,questions);

        this.context = context;
        this.questions = questions;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.questions_row,null,true);

        return rowView;
    }
}
