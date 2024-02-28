package com.example.ToDoApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ToDoApp.model.ToDo;
import com.example.ToDoApp.repo.IToDoRepo;

@Service
public class ToDoService {

    @Autowired
    IToDoRepo repo;

    public List<ToDo> getAllToDoItems() {
        List<ToDo> todoList = new ArrayList<>();
        repo.findAll().forEach(todoList::add);

        return todoList;
    }

    public ToDo getToDoItemById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public boolean updateStatus(Long id) {
        ToDo todo = getToDoItemById(id);
        if (todo != null) {
            todo.setStatus("Completed");
            repo.save(todo);
            return true;
        }
        return false;
    }

    public boolean saveOrUpdateToDoItem(ToDo todo) {
        ToDo updatedTodo = repo.save(todo);
        return updatedTodo != null;
    }

    public boolean deleteToDoItem(Long id) {
        if (getToDoItemById(id) != null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
