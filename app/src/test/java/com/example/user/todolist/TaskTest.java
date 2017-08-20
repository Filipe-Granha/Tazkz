package com.example.user.todolist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 08/07/2017.
 */

public class TaskTest {


        Task task;

        @Before
        public void before() {
            task = new Task("Task A", "Description with no more than 25 words");
        }


        @Test
        public void testTaskSetup() {
            assertEquals("Task A", task.getTitle());
            assertEquals("Description with no more than 25 words", task.getDescription());
        }


    }
