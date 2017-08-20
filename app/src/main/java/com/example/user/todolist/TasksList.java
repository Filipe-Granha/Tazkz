package com.example.user.todolist;


import java.util.ArrayList;

/**
 * Created by user on 08/07/2017.
 */

public class TasksList {


    public ArrayList<Task> list;




    public TasksList(){
        list = new ArrayList<Task>();
    }


    public ArrayList<Task> getList() {
        return new ArrayList<Task>(list);
    }







}
