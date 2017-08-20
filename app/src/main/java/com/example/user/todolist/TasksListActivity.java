package com.example.user.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;



// STARTING ACTIVITY OF THE APP


public class TasksListActivity extends AppCompatActivity {

    Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);
        Log.d(getClass().toString(), "onCreate called");


        // SHARED PREFERENCES
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String tasks = sharedPref.getString(/*if there is something under this->*/"taskList", /*create an empty tasklist, then turn it into a string, if there is nothing*/new TasksList().getList().toString());
        Log.d("Just a literal string of tasks we get back from sharedpref", tasks);

        Gson gson = new Gson();
        TypeToken<ArrayList<Task>> taskArrayList = new TypeToken<ArrayList<Task>>() {
        };
        ArrayList<Task> taskList = gson.fromJson(/*tasks is the string from step above*/tasks, taskArrayList.getType());


        // DISPLAYS ONLY TASKS WHICH HAVEN'T BEEN 'DELETED'
        ArrayList<Task> filteredTaskList = new ArrayList<Task>();
        for (Task task : taskList) {
            if (!task.getDeletedStatus()) {
                filteredTaskList.add(task);
            }
        }
        Log.d("This is an ArrayList of Task objects", taskList.toString());


        // ADAPTER
        TasksListAdapter taskAdapter = new TasksListAdapter(this, filteredTaskList);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(taskAdapter);


        // TASK COUNTER
        TextView list = (TextView) findViewById(R.id.tasksCounter);
        String counterString = "" + "";
        if (filteredTaskList != null) {
            counterString += "Tazkz in your list: " + filteredTaskList.size();
        }
        list.setText(counterString);
    }



    // ADD TASK BUTTON
    public void onAddButtonClicked(View button) {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);
        Log.d(getClass().toString(), "onAddButtonClicked was called");
    }



    // CLICKING ON TASK TAKES US TO INDIVIDUAL TASK (ShowTaskActivity)
    public void getTask(View listItem) {
        Task task = (Task) listItem.getTag();
        Intent intent = new Intent(this, ShowTaskActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);
        Log.d("Task Title: ", task.getTitle());
    }



    //DELETE COMPLETED TASKS
    public void deleteCompletedTasks() {

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String arrayListAsString = sharedPref.getString("taskList", new ArrayList<Task>().toString());
        Gson gson = new Gson();
        TypeToken<ArrayList<Task>> typeToken = new TypeToken<ArrayList<Task>>(){};
        ArrayList<Task> allTasks =  gson.fromJson(arrayListAsString, typeToken.getType());

        for(Task tmpTask: allTasks) {
            if(tmpTask.getCompletedStatus()) {
                tmpTask.setDeleted();
            }

        }

        String backToJson = gson.toJson(allTasks);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("taskList", backToJson);
        editor.apply();
        Toast.makeText(this, "Completed Tazkz Deleted!", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(this, TasksListActivity.class);
        intent.putExtra("taskCompleted", task);
        startActivity(intent);
    }



    // MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//
        switch (item.getItemId()) {
            case R.id.add_new_task:
                Intent intent = new Intent(this, TaskActivity.class);
                intent.putExtra("task", task);
                startActivity(intent);
                return true;
            case R.id.delete_completed_tasks:
                deleteCompletedTasks();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

}