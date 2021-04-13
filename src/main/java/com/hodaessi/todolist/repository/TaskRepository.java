package com.hodaessi.todolist.repository;

import com.hodaessi.todolist.domain.Task;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TaskRepository {

    @PersistenceContext
    EntityManager em;

    //저장
    public Long save(Task task) {
        em.persist(task);
        return task.getId();
    }
    
    //모두 조회
    public List<Task> findAll() {
        return em.createQuery("select m from Task m", Task.class).getResultList();
    }

    public Task findOne(Long id) {
        return em.find(Task.class, id);
    }
}
