package com.hodaessi.todolist.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Task {

    @Id @GeneratedValue
    @Column(name = "task_id")
    private Long id;

    private String contents;

    @Enumerated(EnumType.STRING)
    private TaskStatus status; //[TODO, DONE]

    protected Task() {
    }

    public static Task createTask(String contents) {

        Task task = new Task();
        task.contents = contents;
        task.status = TaskStatus.TODO;
        return task;
    }

    public void changeStatus() {
        if(this.status == TaskStatus.DONE)
            this.status = TaskStatus.TODO;
        else
            this.status = TaskStatus.DONE;
    }
}
