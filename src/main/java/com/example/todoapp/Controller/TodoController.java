package com.example.todoapp.Controller;

import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
public class TodoController {

    private final TodoRepository todoRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/")
    public String showTodoList(Model model) {
        model.addAttribute("todos", todoRepository.findAll());
        return "todo-list"; // Thymeleaf template name
    }

    @PostMapping("/addTodo")
    @Transactional
    public String addTodo(@RequestParam String title, @RequestParam(defaultValue = "false") boolean completed) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setCompleted(completed);
        todoRepository.save(todo);
        entityManager.flush();

        return "redirect:/";
    }

    @PostMapping("/updateTodo")
    @Transactional
    public String updateTodo(@RequestParam Long id, @RequestParam String title, @RequestParam boolean completed) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Todo ID"));
        todo.setTitle(title);
        todo.setCompleted(completed);
        todoRepository.save(todo);
        entityManager.flush();

        return "redirect:/";
    }

    @PostMapping("/deleteTodo")
    @Transactional
    public String deleteTodo(@RequestParam Long id) {
        todoRepository.deleteById(id);
        entityManager.flush();

        return "redirect:/";
    }
}
