package com.hodaessi.todolist.controller;

import com.hodaessi.todolist.domain.Task;
import com.hodaessi.todolist.service.TaskService;
import com.hodaessi.todolist.exception.LengthOverException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/task/new")
    public String create(String task, RedirectAttributes rttr) {

        String createMsg;

        try {
            taskService.createTask(task);
            createMsg = "create success";
        } catch (LengthOverException e) {
            createMsg = "create fail";
        }

        rttr.addFlashAttribute("createMsg", createMsg);
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

    //2. 내용 변경
    @PostMapping("/task/{taskId}/edit")
    public String updateTask(@PathVariable Long taskId, String task) {
//        System.out.println(taskId);
//        System.out.println(task);
        taskService.updateTask(taskId, task);

        return "redirect:/";
    }

    //3. 출력 순서 변경


    //삭제
    @PostMapping("/task/{taskId}/delete")
    public String deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);

        return "redirect:/";
    }
}
