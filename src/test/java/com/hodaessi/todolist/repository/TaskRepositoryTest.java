package com.hodaessi.todolist.repository;

import com.hodaessi.todolist.domain.Task;
import com.hodaessi.todolist.domain.TaskStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void 저장_단일조회_테스트() {
        Task task = Task.createTask("testMessage");
        Long saveId = taskRepository.save(task);

        Task findTask = taskRepository.findOne(saveId);

        assertEquals(saveId, findTask.getId());
        assertEquals(findTask.getContents(), "testMessage");
        assertEquals(findTask.getStatus(), TaskStatus.TODO);
    }
}