package com.hodaessi.todolist.service;

import com.hodaessi.todolist.domain.Task;
import com.hodaessi.todolist.domain.TaskStatus;
import com.hodaessi.todolist.exception.LengthOverException;
import com.hodaessi.todolist.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TaskServiceTest {
    //각 테스트가 독립적으로 동작하게 하는 방법이 뭐였지 ???

    @Autowired
    EntityManager em;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskService taskService;

    @Test(expected = LengthOverException.class)
    public void task생성_저장_테스트() throws Exception {

        Long createId = taskService.createTask("test");
        Task findTask = taskRepository.findOne(createId);

        assertEquals(createId, findTask.getId());
        assertEquals(findTask.getContents(), "test");
        assertEquals(findTask.getStatus(), TaskStatus.TODO);

        String testString = "";
        for(int i = 0; i < Task.contentsLength +1; i++) {
            testString += '*';
        }
        Long testId = taskService.createTask(testString);

        fail("createTask() : contents 크기 초과 예외가 발생해야 합니다.");
    }

    @Test
    public void task전체조회_테스트() {

        Long test1 = taskService.createTask("test1");
        Long test2 = taskService.createTask("test2");

        List<Task> taskList = taskRepository.findAll();

        assertEquals(taskList.size(), 2);
    }

    @Test
    public void task_상태변경_테스트() {
        Long test = taskService.createTask("test"); //초기 상태 TODO
        Task findOne = taskRepository.findOne(test);
        taskService.checkTask(findOne.getId());

        assertEquals(findOne.getStatus(), TaskStatus.DONE);

        taskService.checkTask(findOne.getId());

        assertEquals(findOne.getStatus(), TaskStatus.TODO);
    }

    @Test
    public void task_삭제_테스트() {
        Long test = taskService.createTask("test");

        taskService.deleteTask(test);
        assertNull(taskRepository.findOne(test));
    }

    @Test(expected = LengthOverException.class)
    public void task_메세지수정_테스트() throws Exception{

        Long test = taskService.createTask("test1");

        taskService.updateTask(test, "test2");

        Task findOne = taskRepository.findOne(test);

        assertEquals(findOne.getContents(), "test2");

        String testStr = "";
        for (int i = 0; i < Task.contentsLength +1; i++) {
            testStr += '*';
        }
//        for (int i = 0; i < Task.contentsLength -1; i++) {
//            testStr += '*';
//        }
        taskService.updateTask(test, testStr);

        fail("updateTask() : contents 크기 초과 예외가 발생해야 합니다.");
    }
}