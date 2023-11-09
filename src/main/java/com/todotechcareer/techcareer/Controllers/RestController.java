package com.todotechcareer.techcareer.Controllers;

import com.todotechcareer.techcareer.Models.ToDo;
import com.todotechcareer.techcareer.Repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api")
public class RestController {

    private ToDoRepository toDoRepository;

    @Autowired
    public RestController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @GetMapping("/")
    public String Hello() {
        return "Hello from Spring API!";
    }

    @GetMapping("/todo")
    public List<ToDo> getToDo() {
        return toDoRepository.findAll();
    }

    @PostMapping("/todo")
    public ToDo saveToDo(@RequestBody ToDo toDo) {
        toDoRepository.save(toDo);
        return toDo;
    }

    @PatchMapping("/todo/{id}")
    public ToDo updateToDoStatus(@PathVariable int id, @RequestBody ToDo updatedToDo) {
        ToDo toDoToUpdate = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo not found"));

        if (updatedToDo.isStatus() != toDoToUpdate.isStatus()) {
            toDoToUpdate.setStatus(updatedToDo.isStatus());
        }
        if (updatedToDo.getContent() != null) {
            toDoToUpdate.setContent(updatedToDo.getContent());
        }

        toDoRepository.save(toDoToUpdate);
        return toDoToUpdate;
    }

    @DeleteMapping("/todo/{id}")
    public ToDo deleteToDoById(@PathVariable int id) {
        ToDo deletedTodo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo not found"));
        toDoRepository.delete(deletedTodo);

        return deletedTodo;

    }

    @DeleteMapping("/todo")
    public List<ToDo> deleteAllToDo() {
        List<ToDo> deletedTodo = toDoRepository.findAll();
        toDoRepository.deleteAll();

        return deletedTodo;

    }

    @DeleteMapping("/todo/done")
    public List<ToDo> deleteAllDoneToDo() {
        List<ToDo> deletedTodo = toDoRepository.findAllByStatus(true);
        toDoRepository.deleteAllByStatus(true);

        return deletedTodo;

    }





}
