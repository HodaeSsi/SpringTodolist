package com.hodaessi.todolist.domain;

import org.junit.Assert;
import org.junit.Test;


public class TaskTest {

    @Test
    public void 생성메서드_테스트() {
        Task testTask = Task.createTask("testing");

        Assert.assertEquals(testTask.getContents(), "testing");
//        Assert.assertNull(testTask.getId()); ???
        Assert.assertEquals(testTask.getStatus(), TaskStatus.TODO);
    }
}
