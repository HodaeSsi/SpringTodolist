package com.hodaessi.todolist.controller;

import com.hodaessi.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

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

    //task목록 출력

    //변경

    //삭제
}
