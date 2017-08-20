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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import static com.example.user.todolist.R.string.task;


// ACTIVITY TO ADD NEW TASK


public class TaskActivity extends AppCompatActivity {

    EditText descriptionInput;
    EditText titleInput;
    Button backButton;
    Button saveButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);


        titleInput = (EditText) findViewById(R.id.titleInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);
        backButton = (Button) findViewById(R.id.back_to_list_button);
        saveButton = (Button) findViewById(R.id.save_button);
    }



    public void onSaveButtonClicked(View button) {

        String title = titleInput.getText().toString();
        String description = descriptionInput.getText().toString();
        Boolean isDone = false;
        Boolean isDeleted = false;


        // REQUESTS INPUT FIELD TO BE FILLED
        if(titleInput.getText().toString().length() == 0 ) {
            titleInput.setError( "Input Field Is Empty");
        }
        else {
            SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            String tasks = sharedPref.getString(/*if there is something under this->*/"taskList", /*create an empty tasklist, then turn it into a string, if there is nothing*/new TasksList().getList().toString());
            Gson gson = new Gson();
            TypeToken<ArrayList<Task>> taskArrayList = new TypeToken<ArrayList<Task>>(){};
            ArrayList<Task> taskList = gson.fromJson(/*tasks is the string from step above*/tasks, taskArrayList.getType());
            Task newTask = new Task(title, description, isDone, isDeleted);


            //REVERSES ORDER OF DISPLAY
            taskList.add(0, newTask); // .reverse didn't work!?

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("taskList", gson.toJson(taskList));
            editor.apply();
            Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, TasksListActivity.class);
            startActivity(intent);
        }
    }


    public void onBackButtonClicked(View button) {
        Log.d(getClass().toString(), "onBackButtonClicked was called");
        Intent intent = new Intent(this, TasksListActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_save, menu);
        return true;
    }




// Inserted after Project week
    public boolean onOptionsItemSelected(MenuItem item) {

       String title = titleInput.getText().toString();
       String description = descriptionInput.getText().toString();
       Boolean isDone = false;
       Boolean isDeleted = false;

      switch (item.getItemId()) {
        case R.id.back_to_list:
            Intent intent = new Intent(this, TasksListActivity.class);
            intent.putExtra("task", task);
            startActivity(intent);
            return true;
        case R.id.save_task:
            if (titleInput.getText().toString().length() == 0) {
                titleInput.setError("Input Field Is Empty");
            } else {
                SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                String tasks = sharedPref.getString(/*if there is something under this->*/"taskList", /*create an empty tasklist, then turn it into a string, if there is nothing*/new TasksList().getList().toString());
                Gson gson = new Gson();
                TypeToken<ArrayList<Task>> taskArrayList = new TypeToken<ArrayList<Task>>() {
                };
                ArrayList<Task> taskList = gson.fromJson(/*tasks is the string from step above*/tasks, taskArrayList.getType());
                Task newTask = new Task(title, description, isDone, isDeleted);


                //REVERSES ORDER OF DISPLAY
                taskList.add(0, newTask); // .reverse didn't work!?

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("taskList", gson.toJson(taskList));
                editor.apply();
                Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();

                Intent intent2 = new Intent(this, TasksListActivity.class);
                startActivity(intent2);
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
      }
    }
// Inserted after Project week






}
