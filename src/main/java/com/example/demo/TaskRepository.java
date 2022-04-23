package com.example.demo;

import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TaskRepository {

    private final Map<UUID, TaskEntity> tasks = new HashMap<>();

    public Optional<TaskEntity> getById(UUID id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public TaskEntity create(TaskData data) {
        var id = UUID.randomUUID();
        var entity = fromData(id, data);
        tasks.put(id, entity);
        return entity;
    }

    public Optional<TaskEntity> updateById(UUID id, @NotNull TaskData data) {
        var entity = getById(id);
        entity.ifPresent(taskEntity -> updateFromData(taskEntity, data));
        return entity;
    }

    public boolean deleteById(UUID id) {
        return tasks.remove(id) != null;
    }

    private void updateFromData(@NotNull TaskEntity entity, @NotNull TaskData data) {
        entity.setName(data.name);
    }

    public static TaskEntity fromData(UUID id, TaskData data) {
        return new TaskEntity(id, data.name);
    }

    public Iterable<TaskEntity> getAll() {
        return tasks.values();
    }
}
