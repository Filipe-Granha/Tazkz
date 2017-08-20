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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

import static com.example.user.todolist.R.string.task;


// DISPLAYS DETAILS OF EACH INDIVIDUAL TASK


public class ShowTaskActivity extends AppCompatActivity {

    Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);


        // DISPLAYS STRING WITH TASK'S DETAILS
        task = (Task) getIntent().getSerializableExtra("task");
        TextView list = (TextView) findViewById(R.id.individual_task);
        String taskString = "" + "" + "" + "";
        if (task != null) {
            taskString +="TAzK:" + "\n" + task.getTitle() + "\n" + "\n" + "DETAILS:" + "\n" + task.getDescription();
        }
        list.setText(taskString);
   }


    //method for the button method and the menu method
    public void onCompletedButton() {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String arrayListAsString = sharedPref.getString("taskList", new ArrayList<Task>().toString());
        Gson gson = new Gson();
        TypeToken<ArrayList<Task>> typeToken = new TypeToken<ArrayList<Task>>(){};
        ArrayList<Task> allTasks =  gson.fromJson(arrayListAsString, typeToken.getType());


          // TASK BECOMES COMPLETED
        for(Task tmpTask: allTasks) {
            if(tmpTask.getTitle().equals(task.getTitle())) {
                tmpTask.setCompleted();
            }
        }

        String backToJson = gson.toJson(allTasks);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("taskList", backToJson);
        editor.apply();Toast.makeText(this, "Marked as Completed!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, TasksListActivity.class);
        intent.putExtra("taskCompleted", task);
        startActivity(intent);
        Log.d(getClass().toString(), "onCompletedButton was called");
    }

//method for the button
    public void onButtonCompletedButton(View button) {
        onCompletedButton();
    }









    //method for the button method and the menu method
    public void onDeletedButton() {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String arrayListAsString =  sharedPref.getString("taskList", new ArrayList<Task>().toString());
        Gson gson = new Gson();
        TypeToken<ArrayList<Task>> typeToken = new TypeToken<ArrayList<Task>>(){};
        ArrayList<Task> allTasks =  gson.fromJson(arrayListAsString, typeToken.getType());

        // TASK BECOMES DELETED
        for(Task tmpTask: allTasks) {
            if(tmpTask.getTitle().equals(task.getTitle())) {
                tmpTask.setDeleted();
            }
        }

        String backToJson = gson.toJson(allTasks);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("taskList", backToJson);
        editor.apply();
        Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, TasksListActivity.class);
        intent.putExtra("taskDeleted", task);
        startActivity(intent);
        Log.d(getClass().toString(), "onDeletedButton was called");
    }

    //method for the button
    public void onButtonDeletedButton(View button) {
        onDeletedButton();
    }



    //Inserted after Project week
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_task, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.task_back_to_list:
                Intent intent = new Intent(this, TasksListActivity.class);
                intent.putExtra("task", task);
                startActivity(intent);
                return true;
            case R.id.mark_completed:
                onCompletedButton(); // what should go inside () ??
                return true;
            case R.id.delete_task:
                 onDeletedButton(); // what should go inside () ??
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

















