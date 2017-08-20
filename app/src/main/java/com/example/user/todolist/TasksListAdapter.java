package com.example.user.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 09/07/2017.
 */

public class TasksListAdapter extends ArrayAdapter<Task> {


    public TasksListAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }


    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_tasks_item, parent, false);
        }

        Task currentTask = getItem(position);

        // we have to do the following 2 lines for each
        // of the instance variables that we want to show in the Activity/view
        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentTask.getTitle().toString());

        // SETS BACKGROUND COLOUR FOR COMPLETED TASKS
        if(currentTask.getCompletedStatus()) {
            listItemView.setBackgroundColor(Color.LTGRAY);
        } else {
            listItemView.setBackgroundColor(Color.WHITE);
        }

        // setTag() allows us to attach an object to a view.
        listItemView.setTag(currentTask);

        return listItemView;

    }
}
