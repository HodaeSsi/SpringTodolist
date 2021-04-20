package com.hodaessi.todolist.domain;

import com.hodaessi.todolist.exception.LengthOverException;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Task {

    static public final int contentsLength = 255;

    @Id @GeneratedValue
    @Column(name = "task_id")
    private Long id;

    @Column(length = contentsLength)
    private String contents;

    @Enumerated(EnumType.STRING)
    private TaskStatus status; //[TODO, DONE]

    protected Task() {
    }

    public static Task createTask(String contents) {
        Task task = new Task();

        if(contents.length() > contentsLength) {
            throw new LengthOverException(String.format("contentsLength is over max(" + contentsLength + ")"));
        }
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

    public void changeMessage(String contents) {
        this.contents = contents;
    }
}
