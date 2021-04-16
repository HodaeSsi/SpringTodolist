package com.hodaessi.todolist.service;

import com.hodaessi.todolist.domain.Task;
import com.hodaessi.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    //생성, 저장
    @Transactional
    public Long createTask(String contents) {
        Task task = Task.createTask(contents);
        taskRepository.save(task);
        return task.getId();
    }

    //전부 읽기
    public List<Task> readAll() {
        return taskRepository.findAll();
    }
    
    //수정, 저장(!!!)
    //내용 수정
    
    //상태 수정
    @Transactional
    public void checkTask(Long taskId) {
        Task findTask = taskRepository.findOne(taskId);
        findTask.changeStatus();
        //영속성 컨텍스트(jpa?), 더티체킹(?) 추가 공부좀!!!
    }
    
    //삭제
    @Transactional
    public void deleteTask(Long taskId) {
        taskRepository.delete(taskId);
    }
}
