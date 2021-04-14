package com.hodaessi.todolist.service;

import com.hodaessi.todolist.domain.Task;
import com.hodaessi.todolist.domain.TaskStatus;
import com.hodaessi.todolist.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TaskServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskService taskService;

    @Test
    public void task생성_저장_테스트() {

        Long createId = taskService.createTask("test");
        Task findTask = taskRepository.findOne(createId);

        assertEquals(createId, findTask.getId());
        assertEquals(findTask.getContents(), "test");
        assertEquals(findTask.getStatus(), TaskStatus.TODO);
    }

    @Test
    public void task전체조회_테스트() {

        Long test1 = taskService.createTask("test1");
        Long test2 = taskService.createTask("test2");

        List<Task> taskList = taskRepository.findAll();

        assertEquals(taskList.size(), 2);
    }

}