package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskRepository repository;

    @GetMapping("/{id}")
    public Optional<TaskEntity> getById(@PathVariable("id") UUID id) {
        return repository.getById(id);
    }

    @GetMapping
    public Iterable<TaskEntity> getAll() {
        return repository.getAll();
    }

    @PostMapping
    public TaskEntity create(@Valid @RequestBody @NotNull TaskData data) {
        return repository.create(data);
    }

    @PutMapping("/{id}")
    public Optional<TaskEntity> updateById(@PathVariable("id") UUID id, @Valid @RequestBody @NotNull TaskData data) {
        return repository.updateById(id, data);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") UUID id) throws EntityNotFound {
        if (!repository.deleteById(id))
            throw new EntityNotFound();
    }
}
