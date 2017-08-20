package com.example.user.todolist;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 08/07/2017.
 */

public class TasksListTest {


    @Test
    public void getListTest() {
        TasksList tasksList = new TasksList();
        assertEquals(14, tasksList.getList().size());

    }
}
