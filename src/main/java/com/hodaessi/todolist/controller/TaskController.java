package com.hodaessi.todolist.controller;

import com.hodaessi.todolist.domain.Task;
import com.hodaessi.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    //task목록 출력
    @GetMapping("/")
    public String index(Model model) {
        List<Task> tasks = taskService.readAll();
        model.addAttribute("tasks", tasks);
        //리스트가 비었을 때도 동작하긴 하는데, null처리(?)는 어떻게 해주는거 ???
        return "index";
    }
    //루트신호('/'), 인덱스 페이지랑 task목록 출력 신호랑 분리가 안되네 ???

    //web입력 > task생성
//    @PostMapping("/task/new")
//    public String create(String task, Model model) {
//        System.out.println(task);
//        taskService.createTask(task);
//        model.addAttribute("resultMessage", String.format("입력 성공."));
//
//        return "index";
//        //입력하고 나서도 url이 /task/new에 머무는데...
//    }
    //>> 성공 메세지를 출력하고싶은데...
    @PostMapping("/task/new")
    public String create(String task) {
        taskService.createTask(task);

        return "redirect:/";
    }

    //변경
    //1. 완료 처리
    @PostMapping("/task/{taskId}/check")
    public String checkTask(@PathVariable Long taskId) {
//        System.out.println(taskId);

        taskService.checkTask(taskId);

        return "redirect:/";
    }
    
    //2. 출력 순서 변경

    //삭제
}
